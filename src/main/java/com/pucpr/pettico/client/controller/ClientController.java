package com.pucpr.pettico.client.controller;

import com.pucpr.pettico.client.model.Client;
import com.pucpr.pettico.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping
    public List<Client> find() {
        return clientService.find();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable("id") Integer id) {
        return clientService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        clientService.delete(id);
    }
}
