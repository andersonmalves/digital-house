package com.example.desafio_quality.interfaces;

import com.example.desafio_quality.entity.Room;

import java.math.BigDecimal;
import java.util.List;

public interface IPropertyService {
    double getArea();
    BigDecimal getValue();
    List<Room> getRooms(int propId);
    Room getBiggestRoom();
}
