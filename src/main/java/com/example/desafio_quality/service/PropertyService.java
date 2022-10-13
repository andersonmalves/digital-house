package com.example.desafio_quality.service;

import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.exception.NotFoundException;
import com.example.desafio_quality.interfaces.IPropertyService;
import com.example.desafio_quality.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PropertyService implements IPropertyService {

    @Autowired
    private PropertyRepo repo;

    @Override
    public double getArea(int id) {
        Property property = getPropertyById(id);

        double propertyArea = 0;

        for (int i = 0; i <= property.getRooms().size(); i++) {
            Room room = property.getRooms().get(i);
            double roomArea = room.getRoomLength() * room.getRoomWidth();
            propertyArea += roomArea;
        }

        return propertyArea;
    }

    private Property getPropertyById(int id) {
        Optional<Property> property = repo.getPropertyById(id);

        if(property.isEmpty()) {
            throw new NotFoundException("Propriedade com id: " + id + " não encontrado");
        }

        return property.get();
    }

    @Override
    public BigDecimal getValue() {
        return null;
    }

    @Override
    public List<Room> getRooms() {
        return null;
    }

    @Override
    public Room getBiggestRoom() {
        return null;
    }
}
