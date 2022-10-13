package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.interfaces.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired
    private IPropertyService service;

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
}
