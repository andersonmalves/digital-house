package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.exception.NotFoundException;
import com.example.desafio_quality.interfaces.IPropertyService;
import com.example.desafio_quality.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService implements IPropertyService {

    @Autowired
    private PropertyRepo repo;

    @Override
    public PropertyAreaDTO getArea(int id) {
        Property property = getPropertyById(id);

        double propertyArea = 0;

        for (int i = 0; i < property.getRooms().size(); i++) {
            Room room = property.getRooms().get(i);
            double roomArea = this.calculateRoomArea(room);
            propertyArea += roomArea;
        }

        return new PropertyAreaDTO(property, propertyArea);
    }

    private Property getPropertyById(int id) {
        Optional<Property> property = repo.getPropertyById(id);

        if (property.isEmpty()) {
            throw new NotFoundException("Propriedade com id: " + id + " não encontrado");
        }

        return property.get();
    }

    @Override
    public BigDecimal getValue() {
        return null;
    }

    public List<Room> getRooms(int propId) {
        return repo.getRooms(propId);
    }

    /**
     * Busca cômodo com maior área da propriedade
     * @author Felipe e Gabriel
     * @param propId ID da propriedade
     * @return Retorna o cômodo com maior área da propriedade
     */
    @Override
    public Room getBiggestRoom(int propId) {
        List<Room> rooms = repo.getRooms(propId);

        double maxArea = 0;
        Room biggestRoom = new Room();

        for(Room room : rooms){
            if(this.calculateRoomArea(room) > maxArea){
                maxArea = this.calculateRoomArea(room);
                biggestRoom = room;
            }
        }

        return biggestRoom;
    }

    private double calculateRoomArea(Room room) {
        return room.getRoomLength() * room.getRoomWidth();
    }
}
