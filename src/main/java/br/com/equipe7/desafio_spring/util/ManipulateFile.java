package br.com.equipe7.desafio_spring.util;

import br.com.equipe7.desafio_spring.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManipulateFile {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String linkFile = "src/main/resources/products.json";

    /**
     * Lê o arquivo json de produtos
     * @author Ma, Theus, Anderson e Felipe
     * @return Retorna um array com os produtos
     */
    public static List<Product> loadProducts() {
        try {
            mapper.findAndRegisterModules();
            return new ArrayList<>(Arrays.asList(mapper.readValue(new File(linkFile), Product[].class)));
        } catch (Exception ex) {
            System.out.println("Error reading file");
            return new ArrayList<>();
        }
    }
}
