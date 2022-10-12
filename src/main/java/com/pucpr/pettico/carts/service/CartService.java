package com.pucpr.pettico.carts.service;
import com.pucpr.pettico.carts.model.Cart;
import com.pucpr.pettico.carts.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart save(Cart veiculo) {
        return cartRepository.save(veiculo);
    }

    public List<Cart> find() {
        return cartRepository.findAll();
    }

    public Cart findById(Integer id) {
        return cartRepository.findById(id).get();
    }

    public void delete(Integer id) {
        cartRepository.delete(findById(id));
    }
}
