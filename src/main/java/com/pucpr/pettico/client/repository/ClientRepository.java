package com.pucpr.pettico.client.repository;

import com.pucpr.pettico.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {}
