package com.pucpr.pettico.products.carts.repository;

import com.pucpr.pettico.products.carts.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {}