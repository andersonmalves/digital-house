package br.com.equipe7.desafio_spring.dto;

import br.com.equipe7.desafio_spring.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductResponseDTO implements Serializable {
    private long productId;
    private String name;
    private int quantity;
    public ProductResponseDTO(Product response) {
    this.productId = response.getProductId();
    this.name = response.getName();
    this.quantity = response.getQuantity();
    }
}