package br.com.equipe7.desafio_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedDTO {
    private String name, category, brand, prestige;
    private BigDecimal price;
    private boolean freeShipping;
    private int quantity;
}
