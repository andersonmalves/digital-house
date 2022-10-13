package com.example.desafio_quality.repository;

import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.util.ManipulateFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PropertyRepo {
    public Optional<Property> getPropertyById(int id) {
        List<Property> properties = ManipulateFile.loadProperties();

        return properties.stream()
                .filter(p -> p.getPropId() == id)
                .findFirst();
    }
}
