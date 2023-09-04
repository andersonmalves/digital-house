package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.exception.ClientEmptyException;
import br.com.equipe7.desafio_spring.exception.EmptyRequestException;
import br.com.equipe7.desafio_spring.exception.FieldEmptyException;
import br.com.equipe7.desafio_spring.exception.NotFoundException;
import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.model.Product;
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
     * Lista todos os clientes
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
     * Busca o cliente pelo ID
     * @author Theus
     * @param id ID do produto
     * @return ClienteDTO
     */
    @Override
    public ClientDTO getClientById(int id) {
        Optional<Client> client = this.repo.getClientById(id);

        if(client.isEmpty()) {
            throw new NotFoundException("Client com id: " + id + " não encontrado");
        }

        return new ClientDTO(client.get());
    }

    /**
     * Lista de clientes filtrado por estado
     * @author Giovanna, Matheus Alves e Matheus Ferreira
     * @return retorna uma lista de clientes filtrados por estado
     */
    private List<Client> filterState(List<Client> clientsList, String state) {
        return clientsList.stream()
                .filter(client -> client.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    /**
     * Cadastro de cliente
     * @author Theus, Gabriel, Anderson e Felipe
     * @param client dados do novo cliente.
     * @return DTO do cliente cadastrado.
     */
    @Override
    public Tuple<Long, ClientDTO> save(Client client) {
        if (client == null) {
            throw new EmptyRequestException("Não pode enviar 'payload' vazio");
        }
        if (client.getName() == null) {
            throw new FieldEmptyException("Não pode enviar 'payload' sem o nome");
        }
        if (client.getState() == null) {
            throw new FieldEmptyException("Não pode enviar 'payload' sem o estado");
        }
        if (client.getEmail() == null) {
            throw new FieldEmptyException("Não pode enviar 'payload' sem o email");
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
