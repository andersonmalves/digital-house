package br.com.equipe7.desafio_spring.dto;

import java.math.BigDecimal;

public class ProductCreatedDTO {
    final String name, category, brand, prestige;
    final BigDecimal price;
    final boolean freeShipping;
    final int quantity;

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


    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public String getBrand() {
        return brand;
    }
    public String getPrestige() {
        return prestige;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Boolean getFreeShipping() {
        return freeShipping;
    }
    public int getQuantity() {
        return quantity;
    }
}
