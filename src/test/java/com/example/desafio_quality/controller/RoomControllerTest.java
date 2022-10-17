package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.RoomRequestDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.service.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomControllerTest.class)
public class RoomControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PropertyService service;
    private Property property;
    private RoomRequestDTO createdRoom;
    private List<Room> rooms;

    @BeforeEach
    void setup() {
        this.createdRoom = new RoomRequestDTO(12, 12, "Quarto"); // Comparador

        Room room1 = new Room(12, 12, "Quarto");
        Room room2 = new Room(24.0, 24, "Sala");
        this.rooms = Arrays.asList(room1, room2);
        this.property = new Property("teste", 1, 1, rooms);
    }

    @Test
    void createRooms_returnRoomCreated_whenPropertyExists() throws Exception {
        Room newRoom = new Room(12, 12, "Quarto"); // Retorno

        BDDMockito.when(service.createRooms(createdRoom, property.getPropId()))
                .thenReturn(newRoom);

        ResultActions response = mockMvc.perform(
                post("/api/v1/rooms/property/{propId}", property.getPropId())
                        .content(objectMapper.writeValueAsString(newRoom))
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated());

    }
}
