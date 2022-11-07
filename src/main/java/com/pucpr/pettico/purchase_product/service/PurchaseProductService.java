package com.pucpr.pettico.purchase_product.service;

import com.pucpr.pettico.purchase.model.Purchase;
import com.pucpr.pettico.purchase.repository.PurchaseRepository;
import com.pucpr.pettico.purchase_product.model.PurchaseProduct;
import com.pucpr.pettico.purchase_product.repository.PurchaseProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseProductService {

    @Autowired
    final PurchaseProductRepository purchaseProductRepository;
    final PurchaseRepository purchaseRepository;

    public PurchaseProductService(PurchaseProductRepository purchaseProductRepository, PurchaseRepository purchaseRepository) {
        this.purchaseProductRepository = purchaseProductRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseProduct save(PurchaseProduct purchase) {
        return purchaseProductRepository.save(purchase);
    }

    public List<PurchaseProduct> find() {
        return purchaseProductRepository.findAll();
    }

    public PurchaseProduct findById(Integer id) {
        return purchaseProductRepository.findById(id).get();
    }

    public List<PurchaseProduct> findByPurchaseId(Integer purchaseId) {
        return find().stream().filter(purchaseProduct -> purchaseId.equals(purchaseProduct.getPurchaseId())).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        purchaseProductRepository.delete(findById(id));
    }

}
