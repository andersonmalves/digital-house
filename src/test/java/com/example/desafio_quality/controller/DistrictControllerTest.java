package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.DistrictRequestDTO;
import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.service.DistrictService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DistrictController.class)
public class DistrictControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    DistrictService districtService;

    DistrictRequestDTO districtRequestDTO;

    @BeforeEach
    void setup() {
        final BigDecimal VALUE_DISTRICT_M2 = new BigDecimal("240.00");
        final String DISTRICT_NAME = "Interlagos";
        districtRequestDTO = new DistrictRequestDTO(DISTRICT_NAME, VALUE_DISTRICT_M2);
    }

    @Test
    @DisplayName("Valida se cria um District o status correto")
    void createDistrict_returnsDistrict_WithCorrectParams() throws Exception {
        final int DISTRICT_ID = 1;
        District district = new District(DISTRICT_ID, districtRequestDTO.getValueDistrictM2(), districtRequestDTO.getDistrictName());
        Mockito.when(districtService.createDistrict(ArgumentMatchers.any()))
                .thenReturn(district);

        ResultActions response = mockMvc.perform(
                post("/api/v1/districts")
                        .content(objectMapper.writeValueAsString(districtRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.districtId", CoreMatchers.is(district.getDistrictId())))
                .andExpect(jsonPath("$.valueDistrictM2", CoreMatchers.is(district.getValueDistrictM2().doubleValue())))
                .andExpect(jsonPath("$.districtName", CoreMatchers.is(district.getDistrictName())));
    }
}
