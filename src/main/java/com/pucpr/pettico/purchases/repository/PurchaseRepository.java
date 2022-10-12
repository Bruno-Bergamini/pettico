package com.pucpr.pettico.purchases.repository;

import com.pucpr.pettico.purchases.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {}
