package com.example.desafio_quality.repository;

import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.util.ManipulateFile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PropertyRepo {

    /**
     * Repository para todas os cômodos
     * @author Felipe, Gabriel
      * @param propId propId da propriedade
     * @return lista de todos os cômodos
     */
    public List<Room> getRooms(int propId) {
        Optional<Property> property = ManipulateFile.loadProperties().stream()
                .filter(r -> r.getPropId() == propId)
                .findFirst();

        if (property.isPresent()) {
            return property.get().getRooms();
        }
        return new ArrayList<>();
    }
}
