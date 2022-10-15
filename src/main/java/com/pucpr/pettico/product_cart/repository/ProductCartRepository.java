package com.pucpr.pettico.product_cart.repository;

import com.pucpr.pettico.product_cart.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCartRepository  extends JpaRepository<ProductCart,Integer> {
    List<ProductCart> findByUserId(Integer userId);
}