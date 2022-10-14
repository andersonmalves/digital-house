package com.example.desafio_quality.controller;


import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.service.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.service.PropertyService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PropertyController.class)
public class PropertyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PropertyService service;

    private PropertyAreaDTO propertyArea;

    private Property property;
    private List<Room> rooms;

    @BeforeEach
    void setup() {
        Room room1 = new Room(12, 12, "Quarto");
        Room room2 = new Room(24.0, 24, "Sala");
        this.rooms = Arrays.asList(room1, room2);
        this.property = new Property("teste", 1, 1, rooms);
        this.propertyArea = new PropertyAreaDTO(property, 720.0);
    }

    @Test
    @DisplayName("Valida o retorno de todos os cômodos")
    void getAllRooms_returnRoomsList_whenPropertyExists() throws Exception {
        BDDMockito.when(service.getRooms(anyInt()))
                .thenReturn(this.rooms);

        ResultActions response = mockMvc.perform(
                get("/api/v1/properties/rooms/{propId}",
                        this.property.getPropId())
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].roomName",
                        CoreMatchers.is(this.rooms.get(0).getRoomName())))
                .andExpect(jsonPath("$[0].roomLength",
                        CoreMatchers.is(this.rooms.get(0).getRoomLength())))
                .andExpect(jsonPath("$[0].roomWidth",
                        CoreMatchers.is(this.rooms.get(0).getRoomWidth())));
    }

    @Test
    @DisplayName("Teste de retorno de maior cômodo")
    void getBiggestRoom_returnBiggestRoom_whenPropertyExists() throws Exception {
        Room biggestRoom = new Room(24, 24, "Sala");

        BDDMockito.when(service.getBiggestRoom(anyInt()))
                .thenReturn(biggestRoom);

        ResultActions response = mockMvc.perform(
                get("/api/v1/properties/biggest-room/{propId}", property.getPropId())
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.roomWidth", CoreMatchers.is(biggestRoom.getRoomWidth())))
                .andExpect(jsonPath("$.roomLength", CoreMatchers.is(biggestRoom.getRoomLength())))
                .andExpect(jsonPath("$.roomName", CoreMatchers.is(biggestRoom.getRoomName())))
                .andExpect(jsonPath("$.area", CoreMatchers.is(
                        biggestRoom.getRoomLength() * biggestRoom.getRoomWidth()
                )));
    }

    @Test
    @DisplayName("Valida se retorna um PropertyDTO com a area correta e status correto")
    void getArea_returnsArea_withCorrectPropertyId() throws Exception {
        Mockito.when(service.getArea(ArgumentMatchers.anyInt()))
                .thenReturn(propertyArea);

        ResultActions response = mockMvc.perform(
                get("/api/v1/properties/area/{propId}", property.getPropId())
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.propName", CoreMatchers.is(propertyArea.getPropName())))
                .andExpect(jsonPath("$.districtId", CoreMatchers.is(propertyArea.getDistrictId())))
                .andExpect(jsonPath("$.roomsQnt", CoreMatchers.is(propertyArea.getRoomsQnt())))
                .andExpect(jsonPath("$.area", CoreMatchers.is(propertyArea.getArea())));
    }
}
