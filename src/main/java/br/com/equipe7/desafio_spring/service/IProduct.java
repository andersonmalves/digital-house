package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ProductCreatedDTO;
import br.com.equipe7.desafio_spring.model.Product;

public interface IProduct {
    void save(ProductCreatedDTO newProduct);
}
