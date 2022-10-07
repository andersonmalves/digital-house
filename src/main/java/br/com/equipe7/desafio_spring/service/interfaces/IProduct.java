package br.com.equipe7.desafio_spring.service.interfaces;

import br.com.equipe7.desafio_spring.dto.ProductCreatedDTO;
import br.com.equipe7.desafio_spring.dto.ProductResponseDTO;
import br.com.equipe7.desafio_spring.model.Product;

import java.util.List;
import java.util.Optional;


public interface IProduct {
    List<ProductResponseDTO> save(List<ProductCreatedDTO> productList);
    List<ProductResponseDTO> getAll(
            Optional<String> category,
            Optional<Boolean> freeShipping,
            Optional<String> prestige,
            Optional<Integer> order);
    List<Product> getByProduct(String category);
    Product getProductById(int id);
    void deleteProducts();
}

