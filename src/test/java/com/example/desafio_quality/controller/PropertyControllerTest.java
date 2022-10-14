package com.example.desafio_quality.controller;

import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.service.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyController.class)
public class PropertyControllerTest {
    //setup
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PropertyService service;
    private Property property;

    @BeforeEach
    void setup() {
        property = new Property("Felipe", 1, 1, new ArrayList<>(){{
                add(new Room(10, 10, "Cozinha"));
                add(new Room(20, 20, "Sala"));
            }}
        );
    }

    @Test
    @DisplayName("Teste de retorno de maior cômodo")
    void getBiggestRoom_returnBiggestRoom_whenPropertyExists() throws Exception {
        Room biggestRoom = new Room(20, 20, "Sala");

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
}
