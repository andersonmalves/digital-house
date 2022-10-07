package br.com.equipe7.desafio_spring.repository;

import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.util.ManipulateFile;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NoArgsConstructor
@Data
public class ClientRepo {
    public Client saveClient(Client newClient) {
        List<Client> clientList = ManipulateFile.loadClients();
        clientList.add(newClient);
        ManipulateFile.saveClient(clientList);
        return newClient;
    }
}
