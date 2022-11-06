package com.pucpr.pettico.product.repository;

import com.pucpr.pettico.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {}
