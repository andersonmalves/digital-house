package com.example.desafio_quality.dto;

//import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PropertyValueDTO {
    @JsonProperty("valor total")
    private BigDecimal value;

    public PropertyValueDTO(BigDecimal value) {
        this.value = value;
    }

}
