package com.pucpr.pettico.purchase.repository;

import com.pucpr.pettico.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {}
