package com.example.desafio_quality.interfaces;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.dto.PropertyValueDTO;
import com.example.desafio_quality.entity.Room;
import java.util.List;

public interface IPropertyService {
    PropertyAreaDTO getArea(int id);
    PropertyValueDTO getValue(int propId);
    List<Room> getRooms(int propId);
    Room getBiggestRoom(int propId);
}
