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

    /**
     * Realiza a busca da propriedade pela ID e calcula a sua área.
     * @author Ma, Theus
     * @param id ID da propriedade.
     * @return Retorna a PropertyAreaDTO e sua respectiva área.
     */
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

    /**
     * Realiza a busca da propriedade pela ID.
     * @author Ma, Theus
     * @param id ID da propriedade.
     * @return Retorna a propriedade ou erro caso não seja encontrada.
     */
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

    /**
     * Obtem todas os cômodos da propriedade
     * @author Felipe e Gabriel
     * @param propId ID da propriedade
     * @return retorna todos os cômodos da propriedade
     */
    public List<Room> getRooms(int propId) {
        Optional<Property> property = this.repo.getPropertyById(propId);

        if(property.isEmpty()){
            throw new NotFoundException("Propriedade com id: " + propId + " não encontrado");
        }

        return property.get().getRooms();
    }

    /**
     * Busca cômodo com maior área da propriedade
     * @author Felipe e Gabriel
     * @param propId ID da propriedade
     * @return Retorna o cômodo com maior área da propriedade
     */
    @Override
    public Room getBiggestRoom(int propId) {
        Optional<Property> property = this.repo.getPropertyById(propId);

        if(property.isEmpty()){
            throw new NotFoundException("Propriedade com id: " + propId + " não encontrado");
        }

        List<Room> rooms = property.get().getRooms();
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

    /**
     * @author Felipe e Gabriel
     * @param room um comodo válido com o comprimento e largura
     * @return Retorna a área do comprimento e largura
     */
    private double calculateRoomArea(Room room) {
        return room.getRoomLength() * room.getRoomWidth();
    }
}
