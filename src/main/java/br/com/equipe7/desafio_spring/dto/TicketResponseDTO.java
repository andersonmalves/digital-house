package br.com.equipe7.desafio_spring.dto;

import br.com.equipe7.desafio_spring.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {
    private Ticket ticket;
}
