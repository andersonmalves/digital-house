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
     * Remove todas as propriedades da base de dados
     * @author Anderson e Giovanna
     */
    public void deleteAllProperties() {
        List<Property> properties = new ArrayList<Property>();
        ManipulateFile.saveProperties(properties);
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

    /**
     * Salva uma nova propriedade na base de dados
     * @author Gabriel
     * @param newProperty a nova propriedade a ser salva
     * @return Retorna a nova propriedade a ser salva na base de dados
     */
    public Property createProperty(Property newProperty) {
        List<Property> properties = ManipulateFile.loadProperties();
        properties.add(newProperty);
        ManipulateFile.saveProperties(properties);
        return newProperty;
    }
}
