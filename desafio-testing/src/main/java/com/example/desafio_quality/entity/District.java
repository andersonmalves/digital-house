package com.example.desafio_quality.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class District {
    private int districtId;
    private BigDecimal valueDistrictM2;
    private String districtName;
}