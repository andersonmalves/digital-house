package br.com.equipe7.desafio_spring.service.interfaces;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import java.util.List;
import java.util.Optional;

public interface IClient {
    List<ClientDTO> getAll(Optional<String> state);

}
