package br.com.equipe7.desafio_spring.controller;

import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import br.com.equipe7.desafio_spring.service.interfaces.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private IClient service;
}
