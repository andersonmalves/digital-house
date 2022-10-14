package com.example.desafio_quality.dto;

import com.example.desafio_quality.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequestDTO {
    @NotBlank(message = "O nome da propriedade não pode estar vazio")

    private String propName;
    private int districtId;
    private List<Room> rooms;
}
