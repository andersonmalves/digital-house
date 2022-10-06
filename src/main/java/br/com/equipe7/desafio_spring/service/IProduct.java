package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ProductCreatedDTO;
import br.com.equipe7.desafio_spring.dto.ProductResponseDTO;
import br.com.equipe7.desafio_spring.model.Product;

public interface IProduct {
    ProductResponseDTO save(ProductCreatedDTO newProduct);
}