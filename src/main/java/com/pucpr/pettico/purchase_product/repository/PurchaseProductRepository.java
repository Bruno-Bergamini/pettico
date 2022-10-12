package com.pucpr.pettico.purchase_product.repository;

import com.pucpr.pettico.purchase_product.model.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct,Integer> {}
