package br.com.equipe7.desafio_spring.repository;

import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.util.ManipulateFile;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@NoArgsConstructor
@Data
public class ClientRepo {
    /**
     * Salva um novo cliente
     * @author Felipe e Anderson e Theus e Gabriel
     * @param newClient dados do novo cliente
     * @return Novo cliente adicionado com sucesso ao banco de dados
     */
    public Client saveClient(Client newClient) {
        List<Client> clientList = ManipulateFile.loadClients();
        clientList.add(newClient);
        ManipulateFile.saveClient(clientList);
        return newClient;
    }
    /**
     * Lista de todos os clientes
     * @author Giovanna, Matheus Alves e Matheus Ferreira
     * @return retorna a lista dos clientes
     */
    public List<Client> getAll() {
        return ManipulateFile.loadClients();
    }


    /**
     * Deleta todos os clientes da base de dados
     * @author Gabriel
     */
    public void deleteClients() {
        List<Client> clientList = new ArrayList<>();
        ManipulateFile.saveClient(clientList);
    }

    /**
     * Procurar o cliente pelo ID
     * @author Theus
     * @param id Identificação do cliente a ser buscado
     * @return Retorna opcionalmente um Cliente a partir do id
     */
    public Optional<Client> getClientById(int id) {
        List<Client> clients = ManipulateFile.loadClients();

        return clients.stream()
                .filter(p -> p.getClientId() == id)
                .findFirst();
    }

}
