package com.pucpr.pettico.cart.controller;

import com.pucpr.pettico.product_cart.model.ProductCart;
import com.pucpr.pettico.products.model.Product;
import com.pucpr.pettico.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public void add(@RequestBody Product product) {
        cartService.addProductToCart(product.getId(), 1, 1);
    }

    @GetMapping
    public List<Product> find() {
        List<ProductCart> productCartStream = cartService.getAllProductsInCart(1);
        return null;
    }

//    @GetMapping("/{id}")
//    public Product findById(@PathVariable("id") Integer id) {
//        return cartService.findById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") Integer id) {
//        cartService.delete(id);
//    }
}
