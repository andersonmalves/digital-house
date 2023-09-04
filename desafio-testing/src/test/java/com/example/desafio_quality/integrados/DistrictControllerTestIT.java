package com.example.desafio_quality.integrados;

import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.repository.DistrictRepo;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class DistrictControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DistrictRepo districtRepo;

    @BeforeEach
    void setup() {
        districtRepo.deleteAllDistricts();
    }

    @Test
    @DisplayName("Verifica se um distrito é cadastrado com sucesso")
    void novoDistrito_ReturnNovoDistrito_quandoCriadoComSucesso() throws Exception {

        String districtName = "Interlagos";
        BigDecimal valueDistrictM2 = new BigDecimal("24.00");

        final ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> body = new HashMap<>();

        body.put("districtName", districtName);
        body.put("valueDistrictM2", valueDistrictM2);

        ResultActions resposta;
        resposta = mockMvc.perform(
                post("/api/v1/districts", districtName, valueDistrictM2)
                        .content(mapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON)
        );

        Optional<District> district = districtRepo.getDistrictById(1);

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.districtName", CoreMatchers.is(district.get().getDistrictName())));
    }
}
