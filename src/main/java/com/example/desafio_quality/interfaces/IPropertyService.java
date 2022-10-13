package com.example.desafio_quality.interfaces;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.entity.Room;

import java.math.BigDecimal;
import java.util.List;

public interface IPropertyService {
    PropertyAreaDTO getArea(int id);
    BigDecimal getValue();
    List<Room> getRooms(int propId);
    Room getBiggestRoom(int propId);
}
