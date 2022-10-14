package com.example.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictRequestDTO {
    private BigDecimal valueDistrictM2;
    private String districtName;
}
