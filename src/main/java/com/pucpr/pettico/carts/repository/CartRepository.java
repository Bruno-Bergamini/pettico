package com.pucpr.pettico.carts.repository;

import com.pucpr.pettico.carts.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {}