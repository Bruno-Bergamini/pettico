package com.pucpr.pettico.purchases.controller;

import com.pucpr.pettico.purchases.service.PurchaseService;
import com.pucpr.pettico.purchases.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public Purchase save(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

    @GetMapping
    public List<Purchase> find() {
        return purchaseService.find();
    }

    @GetMapping("/{id}")
    public Purchase findById(@PathVariable("id") Integer id) {
        return purchaseService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        purchaseService.delete(id);
    }
}
