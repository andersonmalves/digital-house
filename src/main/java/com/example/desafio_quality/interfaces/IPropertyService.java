package com.example.desafio_quality.interfaces;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;

import java.math.BigDecimal;
import java.util.List;

public interface IPropertyService {
    PropertyAreaDTO getArea(int id);
    BigDecimal getValue(int propId);
    List<Room> getRooms();
    Room getBiggestRoom();

}
