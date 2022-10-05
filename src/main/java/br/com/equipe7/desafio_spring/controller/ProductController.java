package br.com.equipe7.desafio_spring.controller;


import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import br.com.equipe7.desafio_spring.service.ProductService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.equipe7.desafio_spring.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private IProduct service;

    @GetMapping("/articles")
    public ResponseEntity<List<Product>> getAll() {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
//    /api/v1/articles?category=categoryName&freeShipping=true&order=0
    @GetMapping("/articles/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product =  this.service.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
