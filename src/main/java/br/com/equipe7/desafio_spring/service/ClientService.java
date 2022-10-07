package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.dto.ProductResponseDTO;
import br.com.equipe7.desafio_spring.exception.ClientEmptyException;
import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.repository.ClientRepo;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import br.com.equipe7.desafio_spring.util.ClientIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService implements IClient {
    @Autowired
    private ClientRepo repo;

    @Override
    public ClientDTO save(Client client) {
        if (client == null) {
            throw new ClientEmptyException("Não pode enviar 'payload' vazio");
        }

        int idClient = ClientIdGenerator.getIdGenerator().getNext();
        Client c = new Client(idClient, client.getName(), client.getState(), client.getEmail());
        this.repo.saveClient(c);
        return new ClientDTO(c);
    }
}
