package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.model.Product;

import java.util.List;

public interface IProduct {
    List<Product> getAll();
    Product getProductById(int id);
}
