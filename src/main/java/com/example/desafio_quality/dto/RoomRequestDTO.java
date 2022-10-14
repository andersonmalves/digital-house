package com.example.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {
    private double roomWidth, roomLength;
    private String roomName;
}
