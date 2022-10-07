package br.com.equipe7.desafio_spring.repository;

import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.util.ManipulateFile;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
@NoArgsConstructor
@Data
public class ClientRepo {
    private final String linkFile = "src/main/resources/clients.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public Client saveClient(Client newClient) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Client> clientList = ManipulateFile.loadClients();
        clientList.add(newClient);
        try {
            writer.writeValue(new File(linkFile), clientList);
        } catch (Exception ex) {
            System.out.println("Error creating product");
        }
        return newClient;
    }
}
