package br.com.equipe7.desafio_spring.repository;

import br.com.equipe7.desafio_spring.model.Product;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Repository
@NoArgsConstructor
@Data
public class ProductRepo {

    private final String linkFile = "src/main/resources/products.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public Product saveProduct(Product newProduct) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> productList = loadProducts();
        productList.add(newProduct);
        try {
            writer.writeValue(new File(linkFile), productList);
        } catch (Exception ex) {
            System.out.println("Error creating product");
        }
        return newProduct;
    }

    /**
     * @author Gabriel
     * @param id Identificação do produto a ser buscado
     * @return Retorna opcionalmente um Produto a partir do id
     */
    public Optional<Product> getProductById(int id) {
        List<Product> products = loadProducts();

        Optional<Product> product = products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst();

        return product;
    }

    public List<Product> loadProducts() {
        try {
           List<Product> products = new ArrayList<>();
            mapper.findAndRegisterModules();
            products.addAll(Arrays.asList(mapper.readValue(new File(linkFile), Product[].class)));
            return products;
        } catch (Exception ex) {
            System.out.println("Error reading file");
            return new ArrayList<>();
        }
    }

    public List<Product> getByCategory(String category) {
        List<Product> productList = loadProducts();
        return productList.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public void deleteProducts() {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> productList = new ArrayList<>();
        try {
            writer.writeValue(new File(linkFile), productList);
        } catch (Exception ex) {
            System.out.println("Error deleting products");
        }
    }
}

