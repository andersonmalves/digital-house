package br.com.equipe7.desafio_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductCreatedDTO {
    private String name, category, brand, prestige;
    private BigDecimal price;
    private boolean freeShipping;
    private int quantity;

    /**
     * @author Giovanna
     * @param name
     * @param category
     * @param brand
     * @param prestige
     * @param price
     * @param freeShipping
     * @param quantity
     */
    public ProductCreatedDTO(String name, String category, String brand, String prestige,
                             BigDecimal price, boolean freeShipping, int quantity) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.prestige = prestige;
        this.price = price;
        this.freeShipping = freeShipping;
        this.quantity = quantity;
    }
}
