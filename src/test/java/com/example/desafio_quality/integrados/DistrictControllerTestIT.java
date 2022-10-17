package com.example.desafio_quality.integrados;

import com.example.desafio_quality.repository.DistrictRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
    void novoDistrito_ReturnNovoDistrito_quandoCriadoComSucesso() throws Exception {
        String districtName = "Interlagos";
        BigDecimal valueDistrictM2 = new BigDecimal("24.00");

        ResultActions resposta;
        resposta = mockMvc.perform(
                post("/api/v1/districts", districtName, valueDistrictM2)
                .contentType(MediaType.APPLICATION_JSON)
        );

        resposta.andExpect(status().isCreated());
    }
}
