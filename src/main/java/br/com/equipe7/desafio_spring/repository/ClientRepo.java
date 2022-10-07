package br.com.equipe7.desafio_spring.repository;

import br.com.equipe7.desafio_spring.model.Client;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.util.ManipulateFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NoArgsConstructor
@Data
public class ClientRepo {
    public List<Client> getAll() {
        return ManipulateFile.loadClients();
    }

}
