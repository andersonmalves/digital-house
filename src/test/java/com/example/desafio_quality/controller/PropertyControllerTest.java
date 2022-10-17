package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.dto.PropertyValueDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.service.PropertyService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyController.class)
public class PropertyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService service;

    private PropertyAreaDTO propertyArea;

    private Property property;

    @BeforeEach
    void setup() {
        Room room1 = new Room(12, 12, "Quarto");
        Room room2 = new Room(24.0, 24, "Sala");
        List<Room> rooms = Arrays.asList(room1, room2);
        property = new Property("teste", 1, 1, rooms);
        propertyArea = new PropertyAreaDTO(property, 720.0);
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
}
