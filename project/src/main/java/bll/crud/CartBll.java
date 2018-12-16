package bll.crud;

import dal.entities.Cart;
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
}
