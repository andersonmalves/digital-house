package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.interfaces.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.desafio_quality.dto.PropertyAreaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    @Autowired
    private IPropertyService service;

    /**
     * Controller para todos os quartos
     * @author Felipe, Gabriel
     * @param propId ‘ID’ da propriedade
     * @return HTTP status e todos quartos
     */
    @GetMapping("/rooms/{propId}")
    public ResponseEntity<List<Room>> getAllRooms(@PathVariable int propId) {
        List<Room> rooms = service.getRooms(propId);
        return ResponseEntity.ok(rooms);
    }

    /**
     * Controller para o maior quarto
     * @author Felipe, Gabriel
     * @param propId ‘ID’ da propriedade
     * @return HTTP status e maior quarto e sua area quadrada
     */
    @GetMapping("biggest-room/{propId}")
    public ResponseEntity<RoomDTO> getBiggestRoom(@PathVariable int propId) {
        Room room = service.getBiggestRoom();
        return ResponseEntity.ok(new RoomDTO(room));
    }

    @GetMapping("/area/{propId}")
    public ResponseEntity<PropertyAreaDTO> getArea(@PathVariable int propId) {
        PropertyAreaDTO propertyArea = service.getArea(propId);
        return new ResponseEntity<>(propertyArea, HttpStatus.OK);
    }
}
