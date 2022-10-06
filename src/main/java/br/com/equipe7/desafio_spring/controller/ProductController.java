package br.com.equipe7.desafio_spring.controller;


import br.com.equipe7.desafio_spring.dto.ProductCreatedDTO;
import br.com.equipe7.desafio_spring.dto.ProductResponseDTO;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.equipe7.desafio_spring.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private IProduct service;

    @GetMapping("/articles")
    public ResponseEntity<List<Product>> getAll() {
        ProductRepo productList = new ProductRepo();
        return new ResponseEntity<>(productList.getProductList(), HttpStatus.OK);
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<ProductResponseDTO> save(@RequestBody ProductCreatedDTO newProduct) {
        ProductResponseDTO data = service.save(newProduct);
        return new ResponseEntity<ProductResponseDTO>(data, HttpStatus.CREATED);
    }
}
