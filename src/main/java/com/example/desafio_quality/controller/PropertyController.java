package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.interfaces.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired
    private IPropertyService service;

    @GetMapping("/area/{propId}")
    public ResponseEntity<PropertyAreaDTO> getArea(@PathVariable int propId) {
        PropertyAreaDTO propertyArea = service.getArea(propId);
        return new ResponseEntity<>(propertyArea, HttpStatus.OK);
    }
    @GetMapping("/value/{propId}")
    public ResponseEntity<BigDecimal> getPropertyValue(@PathVariable int propId) {
        BigDecimal property = service.getValue(propId);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

}
