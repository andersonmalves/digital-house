package com.example.desafio_quality.repository;

import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.util.ManipulateFile;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public class PropertyRepo {
    /**
     * Realiza a busca da propriedade pela ID.
     * @author Ma, Theus
     * @param id ID da propriedade.
     * @return Retorna a propriedade por ID caso haja correspondência.
     */
    public Optional<Property> getPropertyById(int id) {
        List<Property> properties = ManipulateFile.loadProperties();

        return properties.stream()
                .filter(p -> p.getPropId() == id)
                .findFirst();
    }

    /**
     * Realiza a adição de cômodos na propriedade
     * @author Felipe e Gabriel
     * @param room objeto para adição
     * @param id Id da propriedade para adição
     * @return retorna a room adicionada
     */
    public Room createRooms(Room room,int id) {
        List<Property> properties = ManipulateFile.loadProperties();
        Optional<Property> property = properties.stream().filter(p -> p.getPropId() == id).findFirst();

        if(property.isEmpty()) {
            return null;
        }
        property.get().getRooms().add(room);

        ManipulateFile.saveProperties(properties);
        return room;
    }
}
