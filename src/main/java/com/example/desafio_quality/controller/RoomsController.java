package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.RoomRequestDTO;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.interfaces.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    @Autowired
    private IPropertyService service;

    @PostMapping("/property/{propId}")
    public ResponseEntity<Room> createRooms(@Valid @RequestBody RoomRequestDTO room, @PathVariable int propId) {
        Room newRoom = service.createRooms(room, propId);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }
}
