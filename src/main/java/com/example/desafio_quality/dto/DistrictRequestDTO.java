package com.example.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictRequestDTO {

    @NotBlank(message = "O nome do bairro não pode estar vazio")
    private String districtName;

    @NotNull(message = "Valor do metro quadrado no bairro não pode estar vazio.")
    @Digits(integer = 13, fraction = 2, message = "Valor do metro quadrado inválido.")
    private BigDecimal valueDistrictM2;
}
