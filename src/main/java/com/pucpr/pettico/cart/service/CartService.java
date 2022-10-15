package com.pucpr.pettico.cart.service;

import com.pucpr.pettico.product_cart.model.ProductCart;
import com.pucpr.pettico.product_cart.repository.ProductCartRepository;
import com.pucpr.pettico.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class CartService {

    @Autowired
    final ProductRepository productRepository;
    final ProductCartRepository productCartRepository;

    public CartService(ProductRepository productRepository, ProductCartRepository productCartRepository) {
        this.productRepository = productRepository;
        this.productCartRepository = productCartRepository;
    }

    public void addProductToCart(Integer productId, Integer quantity, Integer userId) {
        ProductCart productCart = new ProductCart();
        productCart.setProductId(productId);
        productCart.setQuantity(quantity);
        productCart.setUserId(userId);
        productCartRepository.save(productCart);
    }

    public void removeProductFromCart() {

    }

    public List<ProductCart> getAllProductsInCart(Integer userId) {
        return productCartRepository.findByUserId(userId);
    }

    public void purchaseAllProducts() {

    }


}
