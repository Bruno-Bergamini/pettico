package com.pucpr.pettico.products.repository;

import com.pucpr.pettico.products.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {}
