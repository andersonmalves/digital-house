package br.com.equipe7.desafio_spring.controller;


import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.equipe7.desafio_spring.service.IProduct;
import br.com.equipe7.desafio_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
}
