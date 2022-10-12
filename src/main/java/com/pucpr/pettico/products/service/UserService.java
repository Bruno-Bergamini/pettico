package com.pucpr.pettico.products.service;

import com.pucpr.pettico.products.model.User;
import com.pucpr.pettico.products.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> find() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

}