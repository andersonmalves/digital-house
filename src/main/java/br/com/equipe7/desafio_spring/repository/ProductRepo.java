package br.com.equipe7.desafio_spring.repository;

import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.util.ManipulateFile;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Repository
@NoArgsConstructor
@Data
public class ProductRepo {

    private final String linkFile = "src/main/resources/products.json";
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Realiza a criação de um novo produto na base de dados
     * @author Giovanna
     * @param newProduct um novo produto a ser salvo na base de dados
     * @return o produto que foi salvo
     */
    public Product saveProduct(Product newProduct) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> productList = ManipulateFile.loadProducts();
        productList.add(newProduct);
        ManipulateFile.saveProduct(productList);
        return newProduct;
    }

    /**
     * Procurar o produto pelo ID
     * @author Gabriel
     * @param id Identificação do produto a ser buscado
     * @return Retorna opcionalmente um Produto a partir do id
     */
    public Optional<Product> getProductById(int id) {
        List<Product> products = ManipulateFile.loadProducts();

        return products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst();
    }

    /**
     * Obtém uma lista de produtos
     * @return uma lista com todos os produtos
     */
    public List<Product> getAllProducts() {
        return ManipulateFile.loadProducts();
    }

    /**
     * Atualiza a base de dados de produtos a partir de uma lista
     * @author Gabriel
     * @param products Lista de produtos que irão compor a nova base de dados
     */
    public void updateProducts(List<Product> products ) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(linkFile), products);
        } catch (Exception ex) {
            System.out.println("Error updating products");
        }
    }

    /**
     * Deleta todos os produtos da base de dados
     * @author Gabriel
     */
    public void deleteProducts() {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> productList = new ArrayList<>();
        ManipulateFile.saveProduct(productList);
    }


}

