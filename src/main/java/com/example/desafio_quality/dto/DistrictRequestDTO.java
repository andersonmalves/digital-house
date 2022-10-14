package com.example.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictRequestDTO {
    private String districtName;

    @NotEmpty(message = "Valor do metro quadrado no bairro não pode estar vazio.")
    @Digits(integer = 13, fraction = 2, message = "Valor do metro quadrado do bairro não pode estar vazio.")
    private BigDecimal valueDistrictM2;
}
