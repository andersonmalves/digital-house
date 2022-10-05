package br.com.equipe7.desafio_spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long productId;
    private String name, category, brand, prestige;
    private BigDecimal price;
    private boolean freeShipping;
    private int quantity;

//    public Product(long productId, String name, String category, String brand, String prestige,
//                   BigDecimal price, boolean freeShipping, int quantity) {
//        this.productId = productId;
//        this.name = name;
//        this.category = category;
//        this.brand = brand;
//        this.prestige = prestige;
//        this.price = price;
//        this.freeShipping = freeShipping;
//        this.quantity = quantity;
//
//    }
}
