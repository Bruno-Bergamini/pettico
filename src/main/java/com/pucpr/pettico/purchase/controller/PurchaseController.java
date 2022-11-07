package com.pucpr.pettico.purchase.controller;

import com.pucpr.pettico.purchase.service.PurchaseService;
import com.pucpr.pettico.purchase.model.Purchase;
import com.pucpr.pettico.purchase_product.model.PurchaseProduct;
import com.pucpr.pettico.purchase_product.service.PurchaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    final PurchaseService purchaseService;
    PurchaseProductService purchaseProductService;

    public PurchaseController(PurchaseService purchaseService, PurchaseProductService purchaseProductService) {
        this.purchaseService = purchaseService;
        this.purchaseProductService = purchaseProductService;
    }

    @PostMapping
    public Purchase save(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

    @GetMapping
    public List<Purchase> find() {
        return purchaseService.find();
    }

    @GetMapping("/{id}/purchasedProducts")
    public List<PurchaseProduct> getAllPurchasedProducts(@PathVariable("id") Integer id){
        return purchaseProductService.findByPurchaseId(id);
    }

    @GetMapping("/{id}")
    public Purchase findById(@PathVariable("id") Integer id) {
        return purchaseService.findById(id);
    }

}
