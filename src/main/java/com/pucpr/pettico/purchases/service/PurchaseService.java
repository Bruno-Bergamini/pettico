package com.pucpr.pettico.purchases.service;

import com.pucpr.pettico.purchases.repository.PurchaseRepository;
import com.pucpr.pettico.purchases.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> find() {
        return purchaseRepository.findAll();
    }

    public Purchase findById(Integer id) {
        return purchaseRepository.findById(id).get();
    }

    public void delete(Integer id) {
        purchaseRepository.delete(findById(id));
    }

}
