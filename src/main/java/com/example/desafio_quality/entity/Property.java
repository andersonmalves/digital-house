package com.example.desafio_quality.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private String propName;
    private int propId, districtId;
    private List<Room> rooms;
}
