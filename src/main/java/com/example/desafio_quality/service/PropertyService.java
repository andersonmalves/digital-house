package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.dto.PropertyValueDTO;
import com.example.desafio_quality.dto.RoomRequestDTO;
import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.exception.NotFoundException;
import com.example.desafio_quality.interfaces.IPropertyService;
import com.example.desafio_quality.repository.DistrictRepo;
import com.example.desafio_quality.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService implements IPropertyService {

    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private DistrictRepo districtRepo;

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
        Optional<Property> property = propertyRepo.getPropertyById(id);

        if (property.isEmpty()) {
            throw new NotFoundException("Propriedade com id: " + id + " não encontrado");
        }

        return property.get();
    }

    @Override
    public PropertyValueDTO getValue(int propId) {
        Property property = this.getPropertyById(propId);

        District district = getDistrict(property.getDistrictId());
        PropertyAreaDTO area = getArea(propId);

        return new PropertyValueDTO(getCalcValue(area.getArea(), district.getValueDistrictM2()));
    }


    /**
     * Realiza a busca do bairro pelo ID
     * @author Anderson, Giovanna
     * @param id identificação do bairro
     * @return Retorna o bairro ou erro caso não seja encontrado.
     */
    private District getDistrict(int id) {
        Optional<District> district = districtRepo.getDistrictById(id);

        if(district.isEmpty()) {
            throw new NotFoundException("Distrito com id" + id + "não encontrado");
        }

        return district.get();
    }


    /**
     * Realiza o calculo de uma area em relação ao bairro.
     * @author Anderson, Giovanna
     * @param area da propriedade.
     * @param value do metro quadrado.
     * @return Retorna o valor da area.
     */
    private BigDecimal getCalcValue(double area, BigDecimal value) {
        return BigDecimal.valueOf(area).multiply(value);
    }

    /**
     * Obtem todas os cômodos da propriedade
     * @author Felipe e Gabriel
     * @param propId ID da propriedade
     * @return retorna todos os cômodos da propriedade
     */
    public List<Room> getRooms(int propId) {
        Property property = this.getPropertyById(propId);

        return property.getRooms();
    }

    /**
     * Busca cômodo com maior área da propriedade
     * @author Felipe e Gabriel
     * @param propId ID da propriedade
     * @return Retorna o cômodo com maior área da propriedade
     */
    @Override
    public Room getBiggestRoom(int propId) {
        Property property = this.getPropertyById(propId);

        List<Room> rooms = property.getRooms();

        if(rooms == null) {
            throw new NotFoundException("Cômodos não encontrados para a propriedade de id: " + propId);
        }

        double maxArea = calculateRoomArea(rooms.get(0));
        Room biggestRoom = rooms.get(0);

        for(Room room : rooms) {
            if(this.calculateRoomArea(room) > maxArea){
                maxArea = this.calculateRoomArea(room);
                biggestRoom = room;
            }
        }

        return biggestRoom;
    }

    /**
     * Cálculo de área do quarto.
     * @author Felipe e Gabriel
     * @param room um comodo válido com o comprimento e largura
     * @return Retorna a área do comprimento e largura
     */
    private double calculateRoomArea(Room room) {
        return room.getRoomLength() * room.getRoomWidth();
    }

    /**
     * Método para criação de cômodo.
     * @author Felipe e Gabriel
     * @param room Quarto para ser criado.
     * @param id Id da propriedade.
     * @return novo cômodo incluido.
     */
    @Override
    public Room createRooms(RoomRequestDTO room, int id) {
        Property property = getPropertyById(id);
        Room newRoom = this.propertyRepo.createRooms(
                new Room(room.getRoomWidth(), room.getRoomLength(), room.getRoomName()), property.getPropId());

        return newRoom;
    }
}
