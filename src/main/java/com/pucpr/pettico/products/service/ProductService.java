package com.pucpr.pettico.products.service;
import com.pucpr.pettico.products.model.Product;
import com.pucpr.pettico.products.repository.ProductRepository;
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

    public Product save(Product veiculo) {
        return productRepository.save(veiculo);
    }

    public List<Product> find() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }


}
