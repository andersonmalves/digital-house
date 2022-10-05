package br.com.equipe7.desafio_spring.controller;

import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/api/v1/articles")
    public ResponseEntity<List<Product>> getAll() {
        ProductRepo productList = new ProductRepo();
        return new ResponseEntity<>(productList.getProductList(), HttpStatus.OK);
    }
}
