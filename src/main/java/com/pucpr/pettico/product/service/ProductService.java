package com.pucpr.pettico.product.service;
import com.pucpr.pettico.product.model.Product;
import com.pucpr.pettico.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> find() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        try {
            return productRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Integer id) {
        productRepository.delete(findById(id));
    }

    public boolean exists(Integer productId) {
        return productRepository.existsById(productId);
    }



}
