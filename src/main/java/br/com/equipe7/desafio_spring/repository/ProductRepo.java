package br.com.equipe7.desafio_spring.repository;

import br.com.equipe7.desafio_spring.model.Product;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Product saveProduct(Product newProduct) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        productList.add(newProduct);
        try {
            writer.writeValue(new File(linkFile), productList);
        }catch (Exception ex) {
            System.out.println("Error creating product");
        }
        return newProduct;

    }
}
