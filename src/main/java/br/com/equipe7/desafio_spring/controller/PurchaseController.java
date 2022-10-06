package br.com.equipe7.desafio_spring.controller;

import br.com.equipe7.desafio_spring.dto.PurchaseRequestDTO;
import br.com.equipe7.desafio_spring.dto.TicketResponseDTO;
import br.com.equipe7.desafio_spring.exception.NotFoundException;
import br.com.equipe7.desafio_spring.service.IPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {

    @Autowired
    private IPurchase service;

    /**
     * Gerador de “ticket” a partir da ordem de cadastro.
     * @param request lista de produtos para cadastro.
     * @return Status HTTP, lista de cadastro e o preço total.
     */
    @PostMapping("/purchase-request")
    public ResponseEntity<TicketResponseDTO> purchase(@RequestBody(required = false) PurchaseRequestDTO request){
        TicketResponseDTO response = this.service.purchase(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
