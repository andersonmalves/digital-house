package br.com.equipe7.desafio_spring.controller;

import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.service.ClientService;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import br.com.equipe7.desafio_spring.service.interfaces.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private IClient service;

    @PostMapping("/clients")
    public ResponseEntity<Client> save(@RequestBody(required = false)Client newClient) {
        Client data = service.save(newClient);
        return  new ResponseEntity<>(data, HttpStatus.CREATED);
    }
}
