package br.com.equipe7.desafio_spring.util;

import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.model.Product;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ManipulateFile {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String productLinkFile = "src/main/resources/products.json";
    private static final String clientLinkFile = "src/main/resources/clients.json";

    public static void saveProduct(List<Product> productList) {
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(productLinkFile), productList);
        } catch (Exception ex) {
            System.out.println("Error writing product");
        }
    }

    public static void saveClient(List<Client> clientList) {
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(clientLinkFile), clientList);
        } catch (Exception ex) {
            System.out.println("Error writing client");
        }
    }

    /**
     * Lê o arquivo json de produtos
     * @author Ma, Theus, Anderson e Felipe
     * @return Retorna um array com os produtos
     */
    public static List<Product> loadProducts() {
        try {
            mapper.findAndRegisterModules();
            return new ArrayList<>(Arrays.asList(mapper.readValue(new File(productLinkFile), Product[].class)));
        } catch (Exception ex) {
            System.out.println("Error reading file");
            return new ArrayList<>();
        }
    }

    /**
     * Lê o arquivo json de clientes
     * @author Ma, Theus, Anderson, Felipe e GIovanna
     * @return Retorna um array com os clientes
     */
    public static List<Client> loadClients() {
        try {
            mapper.findAndRegisterModules();
            return new ArrayList<>(Arrays.asList(mapper.readValue(new File(clientLinkFile), Client[].class)));
        } catch (Exception ex) {
            System.out.println("Error reading file");
            return new ArrayList<>();
        }
    }
}
