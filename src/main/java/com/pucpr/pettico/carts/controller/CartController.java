package com.pucpr.pettico.carts.controller;

import com.pucpr.pettico.carts.model.Cart;
import com.pucpr.pettico.carts.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public Cart save(@RequestBody Cart product) {
        return cartService.save(product);
    }

    @GetMapping
    public List<Cart> find() {
        return cartService.find();
    }

    @GetMapping("/{id}")
    public Cart findById(@PathVariable("id") Integer id) {
        return cartService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        cartService.delete(id);
    }
}