package br.com.equipe7.desafio_spring.service.interfaces;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import java.util.List;

public interface IClient {
    List<ClientDTO> getAll();

}
