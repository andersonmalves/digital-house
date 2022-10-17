package com.example.desafio_quality.controller;


import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.dto.PropertyRequestDTO;
import com.example.desafio_quality.dto.PropertyValueDTO;
import com.example.desafio_quality.dto.RoomRequestDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.service.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private PropertyRequestDTO propertyRequest;

    @BeforeEach
    void setup() {
        Room room1 = new Room(12, 12, "Quarto");
        Room room2 = new Room(24.0, 24, "Sala");
        this.rooms = Arrays.asList(room1, room2);
        this.property = new Property("teste", 1, 1, rooms);
        this.propertyArea = new PropertyAreaDTO(property, 720.0);

        RoomRequestDTO room1RequestDTO = new RoomRequestDTO(12.0, 12.0, "Quarto");
        RoomRequestDTO room2RequestDTO = new RoomRequestDTO(24.0, 24, "Sala");
        this.propertyRequest = new PropertyRequestDTO("Teste", 1, List.of(room1RequestDTO, room2RequestDTO));
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

    @Test
    @DisplayName("Valida se retorna um PropertyValueDTO com o valor correto da propriedade e status correto")
    void getValue_returnsValue_withCorrectValueOfProperty() throws Exception {
        PropertyValueDTO propertyValue = new PropertyValueDTO(new BigDecimal("17280.0000"));
        Mockito.when(service.getValue(ArgumentMatchers.anyInt()))
                .thenReturn(propertyValue);

        ResultActions response = mockMvc.perform(
                get("/api/v1/properties/value/{propId}", property.getPropId())
                        .contentType(MediaType.APPLICATION_JSON));
        System.out.printf(response.toString());
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.valorTotal", CoreMatchers.is(propertyValue.getValue().doubleValue())));
    }

    @Test
    @DisplayName("Valida se cria uma Property e o status correto")
    void createProperty_returnsProperty_WithCorrectParams() throws Exception {
        Mockito.when(service.createProperty(ArgumentMatchers.any()))
                .thenReturn(property);

        ResultActions response = mockMvc.perform(
                post("/api/v1/properties")
                        .content(objectMapper.writeValueAsString(propertyRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.propId", CoreMatchers.is(property.getPropId())))
                .andExpect(jsonPath("$.districtId", CoreMatchers.is(property.getDistrictId())))
                .andExpect(jsonPath("$.propName", CoreMatchers.is(property.getPropName())))
                .andExpect(jsonPath("$.rooms[0]", CoreMatchers.notNullValue()))
                .andExpect(jsonPath("$.rooms.size()", CoreMatchers.is(property.getRooms().size())))
                .andExpect(jsonPath("$.rooms[0].roomName", CoreMatchers.is(property.getRooms().get(0).getRoomName())))
                .andExpect(jsonPath("$.rooms[0].roomLength", CoreMatchers.is(property.getRooms().get(0).getRoomLength())))
                .andExpect(jsonPath("$.rooms[0].roomWidth", CoreMatchers.is(property.getRooms().get(0).getRoomWidth())));
    }
}
