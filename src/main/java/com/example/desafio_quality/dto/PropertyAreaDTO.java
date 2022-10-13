package com.example.desafio_quality.dto;

import com.example.desafio_quality.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAreaDTO {
    private String propName;
    private int districtId, roomsQnt;
    private double area;

    public PropertyAreaDTO(Property property, double area) {
        this.propName = property.getPropName();
        this.area = area;
        this.districtId = property.getDistrictId();
        this.roomsQnt = property.getRooms().size();
    }
}
