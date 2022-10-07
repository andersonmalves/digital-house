package br.com.equipe7.desafio_spring.service.interfaces;

import br.com.equipe7.desafio_spring.dto.PurchaseRequestDTO;
import br.com.equipe7.desafio_spring.dto.TicketResponseDTO;

public interface IPurchase {
    TicketResponseDTO purchase(PurchaseRequestDTO request);
}
