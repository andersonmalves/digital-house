package br.com.equipe7.desafio_spring.controller;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private IClient service;

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

}
