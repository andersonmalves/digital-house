package br.com.equipe7.desafio_spring.controller;

import br.com.equipe7.desafio_spring.dto.ClientDTO;
import br.com.equipe7.desafio_spring.service.interfaces.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private IClient service;

    /**
     * @author Giovanna, Matheus Alves e Matheus Ferreira
     * @return retorna o status e todos os clientes
     */
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
