package br.com.equipe7.desafio_spring.service;


import br.com.equipe7.desafio_spring.exception.NotFoundException;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {
    @Autowired
    private ProductRepo repo;

    @Override
    public List<Product> getAll(Optional<String> category, Optional<Boolean> freeShipping) {
        List<Product> productList = repo.getProductList();

        if (category.isPresent()) {
            productList = productList.stream()
                    .filter(product -> product.getCategory().equalsIgnoreCase(category.get()))
                    .collect(Collectors.toList());
        }

        if (freeShipping.isPresent()) {
            productList = productList.stream()
                    .filter(product -> product.isFreeShipping() == freeShipping.get())
                    .collect(Collectors.toList());
        }

        return productList;
    }

    /**
     * Pega o produto pelo id
     * @param id
     * @return Product
     */
    @Override
    public Product getProductById(int id) {
        Optional<Product> product = this.repo.getProductById(id);

        if(product.isEmpty()) {
            throw new NotFoundException("Produto com id: " + id + " não encontrado");
        }

        return product.get();
    }

    @Override
    public List<Product> getByProduct(String category) {
        return repo.getByCategory(category);
    }
}
