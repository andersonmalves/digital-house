package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.exception.ClientEmptyException;
import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.repository.ClientRepo;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import br.com.equipe7.desafio_spring.util.ClientIdGenerator;
import br.com.equipe7.desafio_spring.util.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClientService implements IClient {
    @Autowired
    private ClientRepo repo;

    /**
     * @author Giovanna, Matheus Alves e Matheus Ferreira
     * @return retorna a lista de clientes de acordo com o ClientsDTO
     */
    public List<ClientDTO> getAll(Optional<String> state) {

        List<Client> clientsList = repo.getAll();

        if (state.isPresent()) clientsList = filterState(clientsList, state.get());

        return clientsList.stream()
                .map(ClientDTO:: new )
                .collect(Collectors.toList());
    }

    /**
     * @author Giovanna, Matheus Alves e Matheus Ferreira
     * @return retorna uma lista de clientes filtrados por estado
     */
    private List<Client> filterState(List<Client> clientsList, String state) {
        return clientsList.stream()
                .filter(client -> client.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    /**
     * Salva um novo cliente
     * @author Theus, Gabriel, Anderson e Felipe
     * @param client dados do novo cliente
     * @return Uma Tuple com o id do usuário criado e o ClientDTO
     */
    @Override
    public Tuple<Long, ClientDTO> save(Client client) {
        if (client == null) {
            throw new ClientEmptyException("Não pode enviar 'payload' vazio");
        }

        int idClient = ClientIdGenerator.getIdGenerator().getNext();
        Client newClient = new Client(idClient, client.getName(), client.getState(), client.getEmail());
        this.repo.saveClient(newClient);

        return new Tuple<>(newClient.getClientId(), new ClientDTO(client));
    }

    /**
     * Deleta todos os clientes da base de dados e reinicia o gerador de Id de cliente.
     * @author Gabriel
     */
    @Override
    public void deleteClients() {
        this.repo.deleteClients();
        ClientIdGenerator.getIdGenerator().resetId();
    }
}
