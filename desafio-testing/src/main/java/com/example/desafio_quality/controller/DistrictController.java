package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.DistrictRequestDTO;
import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.interfaces.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/districts")
public class DistrictController {

    @Autowired
    private IDistrictService service;

    /**
     * Adiciona um novo distrito na base de dados
     * @author Gabriel
     * @param district um distrito novo
     * @return Http Status e um novo distrito criado
     */
    @PostMapping
    public ResponseEntity<District> createDistrict(@Valid @RequestBody DistrictRequestDTO district){
        District newDistrict = service.createDistrict(district);
        return new ResponseEntity<District>(newDistrict, HttpStatus.CREATED);
    }

}