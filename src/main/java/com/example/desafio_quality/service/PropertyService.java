package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.dto.PropertyValueDTO;
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
    private PropertyRepo repo;
    @Autowired
    private DistrictRepo repoDistrict;

    @Override
    public PropertyAreaDTO getArea(int id) {
        Property property = getPropertyById(id);

        double propertyArea = 0;

        for (int i = 0; i < property.getRooms().size(); i++) {
            Room room = property.getRooms().get(i);
            double roomArea = room.getRoomLength() * room.getRoomWidth();
            propertyArea += roomArea;
        }

        return new PropertyAreaDTO(property, propertyArea);
    }

    private Property getPropertyById(int id) {
        Optional<Property> property = repo.getPropertyById(id);

        if(property.isEmpty()) {
            throw new NotFoundException("Propriedade com id: " + id + " não encontrado");
        }

        return property.get();
    }

//    private Property getPropertyValue(int propId) {
//        Optional<Property> property = repo.getPropertyById(propId);
//        if(property.isEmpty()) {
//            throw new NotFoundException("Propriedade com id: " + id + " não encontrado");
//        }
//        return property.get();
//    }

    @Override
    public PropertyValueDTO getValue(int propId) {
        Optional<Property> property = repo.getPropertyById(propId);
        if(property.isEmpty()) {
            throw new NotFoundException("Propriedade com id: " + propId + " não encontrado");
        }
        District district = getDistrict(property.get().getDistrictId());
        PropertyAreaDTO area = getArea(propId);

        return new PropertyValueDTO(getCalcValue(area.getArea(), district.getValueDistrictM2()));
    }


    public District getDistrict(int id) {
        Optional<District> district = repoDistrict.getDistrictById(id);
        if(district.isEmpty()) {
            throw new NotFoundException("Distrito com id" + id + "não encontrado");
        }
        return district.get();
    }


    public BigDecimal getCalcValue(double area, BigDecimal value) {
        return BigDecimal.valueOf(area).multiply(value);
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
