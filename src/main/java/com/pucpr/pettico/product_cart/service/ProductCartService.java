package com.pucpr.pettico.product_cart.service;

import com.pucpr.pettico.product_cart.model.ProductCart;
import com.pucpr.pettico.product_cart.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCartService {
    @Autowired
    final ProductCartRepository productCartRepository;

    public ProductCartService(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    public ProductCart save(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    public List<ProductCart> find() {
        return productCartRepository.findAll();
    }

    public ProductCart findById(Integer id) {
        return productCartRepository.findById(id).get();
    }

    public void delete(Integer id) {
        productCartRepository.delete(findById(id));
    }
}
