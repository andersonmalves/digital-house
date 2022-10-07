package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.repository.ClientRepo;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClient {
    @Autowired
    private ClientRepo repo;

    /**
     * @author Giovanna, Matheus Alves e Matheus Ferreira
     * @return retorna a lista de clientes de acordo com o ClientsDTO
     */
    public List<ClientDTO> getAll() {

        return repo.getAll().stream()
                .map(ClientDTO:: new )
                .collect(Collectors.toList());
    }


}
