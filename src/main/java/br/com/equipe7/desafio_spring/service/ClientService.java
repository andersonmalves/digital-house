package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.repository.ClientRepo;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements IClient {
    @Autowired
    private ClientRepo repo;
}
