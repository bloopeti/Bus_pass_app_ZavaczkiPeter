package bll.controllers;

import bll.crud.CartBll;
import bll.dtos.CartDTO;
import bll.wrappers.IdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    CartBll cartBll;

    @GetMapping(value = "/getAll")
    public List<CartDTO> getAllCarts() {
        return cartBll.getAllCarts();
    }

    @GetMapping(value = "/get/{id}")
    public CartDTO getCartById(@PathVariable("id") int id) {
        return cartBll.getCartById(id);
    }

    @PostMapping(value = "/add")
    public String addCart(@RequestBody CartDTO cart) {
        return cartBll.addCart(cart);
    }

    @PostMapping(value = "/update")
    public String updateCart(@RequestBody CartDTO cart) {
        return cartBll.updateCart(cart);
    }

    @PostMapping(value = "/delete")
    public String deleteCart(@RequestBody IdWrapper idWrapper) {
        return cartBll.deleteCart(idWrapper.getId());
    }

    @PostMapping(value = "/addToCart")
    private String addToCart(@RequestBody CartDTO cart) {
        return cartBll.addItemToCart(cart);
    }
}
