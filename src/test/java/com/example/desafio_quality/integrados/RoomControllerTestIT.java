package com.example.desafio_quality.integrados;

import com.example.desafio_quality.dto.RoomRequestDTO;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.interfaces.IPropertyService;
import com.example.desafio_quality.repository.PropertyRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private IPropertyService service;

    private Property property;

    @BeforeEach
    public void setup(){
        this.propertyRepo.deleteAllProperties();

        Room room1 = new Room(12.0, 12.0, "Quarto");
        Room room2 = new Room(24.0, 24.0, "Sala");
        List<Room> rooms = Arrays.asList(room1, room2);
        this.property = new Property("teste",1,1, rooms);

        this.propertyRepo.createProperty(this.property);
    }

    @Test
    @DisplayName("Valida se um cômodo é criado com sucesso")
    public void createRooms_returnRoom_whenPropertyExist() throws Exception {
        RoomRequestDTO requestBody = new RoomRequestDTO(10,10,"Teste");

        ResultActions response = mockMvc.perform(
                post("/api/v1/rooms/property/{propId}", this.property.getPropId())
                        .content(objectMapper.writeValueAsString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.roomName", CoreMatchers.is(requestBody.getRoomName())))
                .andExpect(jsonPath("$.roomLength", CoreMatchers.is(requestBody.getRoomLength())))
                .andExpect(jsonPath("$.roomWidth", CoreMatchers.is(requestBody.getRoomWidth())));


        Property propertyFromRepo = this.propertyRepo.getPropertyById(1).get();
        assertThat(propertyFromRepo.getRooms().size()).isEqualTo(3);
    }



}
