package com.example.desafio_quality.repository;

import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.entity.Property;
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
