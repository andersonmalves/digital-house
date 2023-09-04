package com.example.desafio_quality.controller;


import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.dto.PropertyRequestDTO;
import com.example.desafio_quality.dto.PropertyValueDTO;
import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.interfaces.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    @Autowired
    private IPropertyService service;

    /**
     * Controller para todos os cômodos
     * @author Felipe, Gabriel
     * @param propId ‘ID’ da propriedade
     * @return HTTP status e todos cômodos
     */
    @GetMapping("/rooms/{propId}")
    public ResponseEntity<List<Room>> getAllRooms(@PathVariable int propId) {
        List<Room> rooms = service.getRooms(propId);
        return ResponseEntity.ok(rooms);
    }

    /**
     * Controller para o maior cômodos
     * @author Felipe, Gabriel
     * @param propId ‘ID’ da propriedade
     * @return HTTP status e maior comôdo e sua area quadrada
     */
    @GetMapping("biggest-room/{propId}")
    public ResponseEntity<RoomDTO> getBiggestRoom(@PathVariable int propId) {
        Room room = service.getBiggestRoom(propId);
        return ResponseEntity.ok(new RoomDTO(room));
    }

    /**
     * Realiza a busca da propriedade pela ID.
     * @author Ma, Theus
     * @param propId ID da propriedade.
     * @return Retorna a área da propriedade pesquisada.
     */
    @GetMapping("/area/{propId}")
    public ResponseEntity<PropertyAreaDTO> getArea(@PathVariable int propId) {
        PropertyAreaDTO propertyArea = service.getArea(propId);
        return new ResponseEntity<>(propertyArea, HttpStatus.OK);
    }

    /**
     * Realiza a busca do valor da propriedade pelo ID.
     * @author Anderson, Giovanna
     * @param propId ID da propriedade.
     * @return Retorna o valor da propriedade pesquisada.
     */
    @GetMapping("/value/{propId}")
    public ResponseEntity<PropertyValueDTO> getPropertyValue(@PathVariable int propId) {
        PropertyValueDTO property = service.getValue(propId);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    /**
     * Cria uma nova propriedade
     * @author Gabriel
     * @param property uma nova propriedade a ser criada
     * @return Http Status e a nova propriedade criada
     */
    @PostMapping()
    public ResponseEntity<Property> createProperty(@Valid @RequestBody PropertyRequestDTO property) {
        Property newProperty = this.service.createProperty(property);
        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

}
