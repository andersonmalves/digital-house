package com.example.desafio_quality.service;

import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.interfaces.IPropertyService;
import com.example.desafio_quality.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PropertyService implements IPropertyService {
    @Autowired
    private PropertyRepo repo;

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public BigDecimal getValue() {
        return null;
    }

    @Override
    public List<Room> getRooms(int propId) {
        return repo.getRooms(propId);
    }

    @Override
    public Room getBiggestRoom() {
        return null;
    }
}
