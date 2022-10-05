package br.com.equipe7.desafio_spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Comparable<Product> {
    private long productId;
    private String name, category, brand, prestige;
    private BigDecimal price;
    private boolean freeShipping;
    private int quantity;

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.name);
    }
}
