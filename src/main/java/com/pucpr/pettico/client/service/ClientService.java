package com.pucpr.pettico.client.service;

import com.pucpr.pettico.client.model.Client;
import com.pucpr.pettico.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> find() {
        return clientRepository.findAll();
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id).get();
    }

    public void delete(Integer id) {
        clientRepository.delete(findById(id));
    }

}
