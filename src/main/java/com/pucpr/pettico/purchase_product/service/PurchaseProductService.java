package com.pucpr.pettico.purchase_product.service;

import com.pucpr.pettico.purchase_product.model.PurchaseProduct;
import com.pucpr.pettico.purchase_product.repository.PurchaseProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseProductService {

    @Autowired
    final PurchaseProductRepository purchaseRepository;

    public PurchaseProductService(PurchaseProductRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseProduct save(PurchaseProduct purchase) {
        return purchaseRepository.save(purchase);
    }

    public List<PurchaseProduct> find() {
        return purchaseRepository.findAll();
    }

    public PurchaseProduct findById(Integer id) {
        return purchaseRepository.findById(id).get();
    }

    public void delete(Integer id) {
        purchaseRepository.delete(findById(id));
    }

}
