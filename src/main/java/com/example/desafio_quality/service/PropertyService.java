package com.example.desafio_quality.service;

import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.interfaces.IPropertyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PropertyService implements IPropertyService {

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public BigDecimal getValue() {
        return null;
    }

    @Override
    public List<Room> getRooms() {
        return ;
    }

    @Override
    public Room getBiggestRoom() {
        return null;
    }
}
