package bll.crud;

import dal.entities.Cart;
import dal.entities.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.repositories.CartRepository;

import java.util.List;

@Service
public class CartBll {
    @Autowired
    CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(int id) {
        if (cartRepository.findById(id).isPresent())
            return cartRepository.findById(id).get();
        else
            return null;
    }

    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }

    public String updateCart(Cart cart) {
        Cart updatedCart;
        if (cartRepository.findById(cart.getId()).isPresent()) {
            updatedCart = cartRepository.findById(cart.getId()).get();
            updatedCart.setUser(cart.getUser());
            updatedCart.setPasses(cart.getPasses());
            cartRepository.save(updatedCart);
            return "CART UPDATE SUCCESSFUL";
        } else
            return "CART UPDATE FAILED";
    }

    public void deleteCart(int cartId) {
        cartRepository.deleteById(cartId);
    }

    // the cart parameter should only contain the ID of the cart and one single element in the list of the passes, which should be added
    public String addItemToCart(Cart cart) {
        Cart dbCart = getCartById(cart.getId());
        PassBll passBll = new PassBll();
        Pass pass = passBll.getPassById(cart.getPasses().get(0).getId());
        dbCart.getPasses().add(pass);
        return updateCart(dbCart);
    }

    // the cart parameter should only contain the ID of the cart
    // and one single element in the list of the passes, which should be added
    public String removeItemFromCart(Cart cart) {
        Cart dbCart = getCartById(cart.getId());
        dbCart.getPasses().add(cart.getPasses().get(0));
        return updateCart(dbCart);
    }
}
