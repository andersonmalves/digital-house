package br.com.equipe7.desafio_spring.repository;

import br.com.equipe7.desafio_spring.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@Data
public class ProductRepo {

    private final String linkFile = "src/main/resources/products.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final List<Product> productList = new ArrayList<>();

    public ProductRepo() {
        try {
            mapper.findAndRegisterModules();
            productList.addAll(Arrays.asList(mapper.readValue(new File(linkFile), Product[].class)));
        }catch (Exception ex) {
            System.out.println("Error reading file");
        }
    }

    public Optional<Product> getProductById(int id) {
        List<Product> products = loadProducts();

        Optional<Product> product = products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst();

        return product;
    }

    private List<Product> loadProducts() {
        try {
           List<Product> products = new ArrayList<>();
            mapper.findAndRegisterModules();
            products.addAll(Arrays.asList(mapper.readValue(new File(linkFile), Product[].class)));
            return products;
        } catch (Exception ex) {
            System.out.println("Error reading file");
            return null;
        }
    }
}
