package br.com.equipe7.desafio_spring.dto;

import br.com.equipe7.desafio_spring.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private String name, state, email;

    public ClientDTO(Client client) {
        this.name = client.getName();
        this.state = client.getState();
        this.email = client.getEmail();
    }
}
