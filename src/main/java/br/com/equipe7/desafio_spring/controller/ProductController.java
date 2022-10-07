package br.com.equipe7.desafio_spring.controller;

import br.com.equipe7.desafio_spring.dto.ProductCreatedDTO;
import br.com.equipe7.desafio_spring.dto.ProductResponseDTO;
import br.com.equipe7.desafio_spring.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.equipe7.desafio_spring.service.interfaces.IProduct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private IProduct service;

    /**
     * Método para pegar todos os parâmetros existentes no Query Params
     * @author Ma, Theus, Anderson, Felipe
     * @param category Categoria de Produtos (String)
     * @param freeShipping Frete gratis (boolean)
     * @param prestige Nível de satisfação do produto (String)
     * @param order Ordenação do array (Alfabético ou preço)
     * @return Retorna o status e o produto conforme o parâmetro estipulado.
     */
    @GetMapping("/articles")
    public ResponseEntity<List<ProductResponseDTO>> getAll(@RequestParam Optional<String> category,
                                                @RequestParam Optional<Boolean> freeShipping,
                                                @RequestParam Optional<String> prestige,
                                                @RequestParam Optional<Integer> order) {
            List<ProductResponseDTO> data = service.getAll(category, freeShipping, prestige, order);
            return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * Realiza a busca do produto por ID.
     * @param productId ID do produto
     * @return Resultado da busca do produto pelo ID.
     */
    @GetMapping("/articles/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product =  this.service.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Criação de Produto
     * @param newProduct Novo produto para ser cadastrado.
     * @return Status HTTP e o Retorno pelo DTO do produto cadastrado.
     */
    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductResponseDTO>> save(@RequestBody(required = false) List<ProductCreatedDTO> newProduct) {
        List<ProductResponseDTO> data = service.save(newProduct);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    /**
     * Remove Produtos
     * @author Gabriel
     * @return Status HTTP
     */
    @DeleteMapping("/articles")
    public ResponseEntity<Void> deleteProducts() {
        this.service.deleteProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
