package bll.crud;

import bll.dtos.CartDTO;
import bll.dtos.PassDTO;
import bll.dtos.UserDTO;
import bll.dtos.converters.CartConverter;
import bll.dtos.converters.PassConverter;
import bll.dtos.converters.UserConverter;
import dal.entities.Cart;
import dal.entities.Pass;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.repositories.CartRepository;

import java.util.List;

@Service
public class CartBll {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserBll userBll;
    @Autowired
    PassBll passBll;
    @Autowired
    private CartConverter converter;// = new CartConverter();
    @Autowired
    private PassConverter passConverter;// = new PassConverter();
    @Autowired
    private UserConverter userConverter;// = new UserConverter();

    public List<CartDTO> getAllCarts() {
        return converter.entityListToDtoList(cartRepository.findAll());
    }

    public CartDTO getCartById(int id) {
        if (cartRepository.findById(id).isPresent())
            return converter.entityToDto(cartRepository.findById(id).get());
        return null;
    }

    @SuppressWarnings("Duplicates")
    public String addCart(CartDTO cart) {
        String reason = "User";
        UserDTO dbUser = userBll.getUserById(cart.getUserId());
        if (dbUser != null) {
            List<PassDTO> passList = cart.getPasses();
            if (!(passList.isEmpty())) {
                reason = "Pass";
                int passListSize = passList.size();
                int validPasses = 0;
                for (PassDTO dto : passList) {
                    if (passBll.getPassById(dto.getId()) != null) {
                        validPasses++;
                    } else {
                        break;
                    }
                }
                if (validPasses == passListSize) {
                    cartRepository.save(converter.dtoToEntity(cart));
                    return "CART ADD SUCCESSFUL";
                }
            } else {
                cartRepository.save(converter.dtoToEntity(cart));
                return "CART(empty) ADD SUCCESSFUL";
            }
        }
        return "CART ADD FAILED: " + reason + " with this ID doesn't exist";
    }

    @SuppressWarnings("Duplicates")
    public String updateCart(CartDTO cart) {
        Cart updatedCart;
        String reason = "Bus";
        if (cartRepository.findById(cart.getId()).isPresent()) {
            updatedCart = cartRepository.findById(cart.getId()).get();
            reason = "User";
            UserDTO dbUser = userBll.getUserById(cart.getUserId());
            if (dbUser != null) {
                updatedCart.setUser(userConverter.dtoToEntity(dbUser));
                List<PassDTO> passList = cart.getPasses();
                if (!(passList.isEmpty())) {
                    reason = "Pass";
                    int passListSize = passList.size();
                    int validPasses = 0;
                    for (PassDTO dto : passList) {
                        if (passBll.getPassById(dto.getId()) != null) {
                            validPasses++;
                        } else {
                            break;
                        }
                    }
                    if (validPasses == passListSize) {
                        updatedCart.setPasses(passConverter.dtoListToEntityList(cart.getPasses()));
                        cartRepository.save(converter.dtoToEntity(cart));
                        return "CART UPDATE SUCCESSFUL";
                    }
                } else {
                    updatedCart.setPasses(passConverter.dtoListToEntityList(cart.getPasses()));
                    cartRepository.save(converter.dtoToEntity(cart));
                    return "CART(empty) UPDATE SUCCESSFUL";
                }
            }
        }
        return "CART UPDATE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String deleteCart(int cartId) {
        String reason = "Cart";
        if (cartRepository.findById(cartId).isPresent()) {
            cartRepository.deleteById(cartId);
            return "CART DELETE SUCCESSFUL";
        }
        return "CART DELETE FAILED: " + reason + " with this ID doesn't exist";
    }

    // the cart parameter should only contain the ID of the cart and one single element in the list of the passes, which should be added
    public String addItemToCart(CartDTO cart) {
        CartDTO dbCart = getCartById(cart.getId());
        String reason = "Invalid Cart ID";
        if (dbCart != null) {
            PassDTO pass;
            reason = "No item to insert (Empty list passed)";
            if (!(cart.getPasses().isEmpty())) {
                pass = passBll.getPassById(cart.getPasses().get(0).getId());
                reason = "Pass with this ID doesn't exist";
                if (pass != null) {
                    dbCart.getPasses().add(pass);
                    reason = updateCart(dbCart);
                    if (reason.contains("SUCCESSFUL")) {
                        return "ITEM INSERTION INTO CART SUCCESSFUL";
                    }
                }
            }
        }
        return "ITEM INSERTION INTO CART FAILED: " + reason;
    }

    // the cart parameter should only contain the ID of the cart
    // and one single element in the list of the passes, which should be added
    public String removeItemFromCart(CartDTO cart) {
        CartDTO dbCart = getCartById(cart.getId());
        String reason = "Invalid Cart ID";
        if (dbCart != null) {
            PassDTO pass;
            reason = "No item to remove (Empty list passed)";
            if (!(cart.getPasses().isEmpty())) {
                pass = passBll.getPassById(cart.getPasses().get(0).getId());
                reason = "Pass with this ID doesn't exist";
                if (pass != null) {
                    dbCart.getPasses().remove(pass);
                    reason = updateCart(dbCart);
                    if (reason.contains("SUCCESSFUL")) {
                        return "ITEM REMOVAL FROM CART SUCCESSFUL";
                    }
                }
            }
        }
        return "ITEM REMOVAL FROM CART FAILED: " + reason;
    }
}
