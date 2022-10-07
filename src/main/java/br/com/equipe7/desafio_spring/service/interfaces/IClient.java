package br.com.equipe7.desafio_spring.service.interfaces;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.util.Tuple;

import java.util.*;

public interface IClient {
    Tuple<Long, ClientDTO> save(Client client);
    List<ClientDTO> getAll(Optional<String> state);
    void deleteClients();

}
