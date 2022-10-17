package com.example.desafio_quality.integrados;

import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PropertyRepo propertyRepo;

    private Property property;

    @BeforeEach
    void setup() {
        propertyRepo.deleteAllProperties();
    }

    @Test
    @DisplayName("Valida se uma propriedade é criada com sucesso")
    void novaPropriedade_quandoCriadaComSucesso() throws Exception {

        String propName = "Felipe";
        int districtId = 1;
        Room room1 = new Room(12.0, 12.0, "Quarto");
        Room room2 = new Room(24.0, 24.0, "Sala");
        List<Room> rooms = Arrays.asList(room1, room2);

        final ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> body = new HashMap<>();

        body.put("propName", propName);
        body.put("districtId",districtId);
        body.put("rooms", rooms);

        ResultActions resposta;
        resposta = mockMvc.perform(
                post("/api/v1/properties")
                        .content(mapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON));

        Optional<Property> property = propertyRepo.getPropertyById(1);

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.propName", CoreMatchers.is(property.get().getPropName())))
                .andExpect(jsonPath("$.districtId", CoreMatchers.is(property.get().getDistrictId())));

        assertThat(property.get().getRooms().size()).isEqualTo(2);
    }
}
