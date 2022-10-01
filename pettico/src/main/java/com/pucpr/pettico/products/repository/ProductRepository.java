package com.pucpr.pettico.products.repository;

import com.pucpr.pettico.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {}
