package br.com.equipe7.desafio_spring.service.interfaces;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.model.Client;

public interface IClient {
    ClientDTO save(Client client);
}
