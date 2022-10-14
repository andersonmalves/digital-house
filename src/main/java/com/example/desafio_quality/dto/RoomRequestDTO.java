package com.example.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {
    @NotEmpty(message = "A largura do cômodo não pode estar vazia")
    @Max(value = 25, message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double roomWidth;

    @NotEmpty(message = "A comprimento do cômodo não pode estar vazia")
    @Max(value = 33, message = "A comprimento máxima permitida por cômodo é de 25 metros.")
    private double roomLength;

    @NotBlank(message = "O  campo não pode estar vazio.")
    @Pattern(regexp = "\\b[A-Z]\\w*\\b", message = "O nome do cômodo deve começar com a letra maiúscula.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    private String roomName;
}
