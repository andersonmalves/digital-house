package br.com.equipe7.desafio_spring.controller;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private IClient service;

    /**
     * Cadastro um novo cliente
     * @author Felipe, Anderson, Theus e Gabriel
     * @param newClient Dados do novo cliente
     * @return Cadastra um novo cliente e retorna um HTTP status
     */
    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> save(@RequestBody(required = false)Client newClient) {
        ClientDTO data = service.save(newClient);
        return new ResponseEntity<>( data, HttpStatus.CREATED);
    }

    /**
     * @author Giovanna, Matheus Alves e Matheus Ferreira
     * @param state Estado do cliente
     * @return retorna o status, todos os clientes ou os clientes filtrados por estado
     */
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAll(@RequestParam Optional<String> state) {
        List<ClientDTO> clients = service.getAll(state);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Remove Clientes
     * @author Gabriel
     * @return HTTP Status
     */
    @DeleteMapping("/clients")
    public ResponseEntity<Void> deleteClients() {
        service.deleteClients();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
