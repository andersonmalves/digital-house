package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.repository.ClientRepo;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
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


}
