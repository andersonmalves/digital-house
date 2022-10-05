package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ProductCreatedDTO;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import br.com.equipe7.desafio_spring.util.ProductIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProduct{

    @Autowired
    private ProductRepo repo;

    @Override
    public void save(ProductCreatedDTO newProduct) {
        int idProduct = ProductIdGenerator.getIdGenerator().getNext();
        Product product = new Product(idProduct, newProduct.getName(), newProduct.getCategory(),
                newProduct.getBrand(), newProduct.getPrestige(), newProduct.getPrice(),
                newProduct.getFreeShipping(), newProduct.getQuantity());
        repo.saveProduct(product);
    }
}
