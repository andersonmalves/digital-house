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
import java.util.stream.Collectors;
import java.util.Optional;

@Repository
@Data
public class ProductRepo {

    private final String linkFile = "src/main/resources/products.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final List<Product> productList = new ArrayList<>();

    public ProductRepo() {
        try {
            // https://stackoverflow.com/questions/21384820/is-there-a-jackson-datatype-module-for-jdk8-java-time
            mapper.findAndRegisterModules();
            productList.addAll(Arrays.asList(mapper.readValue(new File(linkFile), Product[].class)));
        }catch (Exception ex) {
            System.out.println("Error reading file");
        }
    }
    /**
     * Cadastro de Produto
     * @author Giovanna e Gabriel
     * @param newProduct Novo produto
     * @return Retorna um produto cadastrado e o status HTTP
     */
    public Product saveProduct(Product newProduct) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        productList.add(newProduct);
        try {
            writer.writeValue(new File(linkFile), productList);
        } catch (Exception ex) {
            System.out.println("Error creating product");
        }
        return newProduct;
    }

    /**
     * Procurar o produto pelo ID
     * @author Gabriel
     * @param id Identificação do produto a ser buscado
     * @return Retorna opcionalmente um Produto a partir do id
     */
    public Optional<Product> getProductById(int id) {
        List<Product> products = loadProducts();

        Optional<Product> product = productList.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst();

        return product;
    }

    /**
     * Alimentação do Array Produtos
     * @author Ma, Theus, Anderson e Felipe
     * @return Retorna um array com os produtos
     */
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

    public List<Product> getByCategory(String category) {
        return productList.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}

