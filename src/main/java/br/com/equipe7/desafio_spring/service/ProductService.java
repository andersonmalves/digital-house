package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProduct{

    @Autowired
    private ProductRepo repo;
}
