package br.com.equipe7.desafio_spring.service.interfaces;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.model.Client;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import java.util.List;
import java.util.Optional;

public interface IClient {
    ClientDTO save(Client client);
    List<ClientDTO> getAll(Optional<String> state);

}
