package com.pucpr.pettico.users.repository;

import com.pucpr.pettico.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {}
