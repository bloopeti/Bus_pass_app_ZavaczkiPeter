package bll.controllers;

import dal.entities.Cart;
import bll.wrappers.IdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bll.crud.CartBll;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    CartBll cartBll;

    @GetMapping(value = "/getAll")
    public List<Cart> getAllCarts() {
        return cartBll.getAllCarts();
    }

    @GetMapping(value = "/get/{id}")
    public Cart getCartById(@PathVariable("id") int id) {
        return cartBll.getCartById(id);
    }

    @PostMapping(value = "/add")
    public void addCart(@RequestBody Cart cart) {
        cartBll.addCart(cart);
    }

    @PostMapping(value = "/update")
    public String updateCart(@RequestBody Cart cart) {
        return cartBll.updateCart(cart);
    }

    @PostMapping(value = "/delete")
    public void deleteCart(@RequestBody IdWrapper idWrapper) {
        cartBll.deleteCart(idWrapper.getId());
    }

    @PostMapping(value = "/addToCart")
    private void addToCart(@RequestBody Cart cart) {
        cartBll.addItemToCart(cart);
    }
}
