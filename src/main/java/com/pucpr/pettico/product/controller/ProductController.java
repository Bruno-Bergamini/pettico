package com.pucpr.pettico.product.controller;

import com.pucpr.pettico.product.model.Product;
import com.pucpr.pettico.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public List<Product> find() {
        return productService.find();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
}
