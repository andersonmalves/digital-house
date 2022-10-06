package br.com.equipe7.desafio_spring.dto;

import br.com.equipe7.desafio_spring.model.Product;
import lombok.*;

import java.io.Serializable;

/**
 * @author Giovanna, Theus
 */
@Data
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {
    private long productId;
    private String name;
    private int quantity;

    /**
     * @author Giovanna
     * @param response -> obtém o productId, name e quantity
     */
    public ProductResponseDTO(Product response) {
        this.productId = response.getProductId();
        this.name = response.getName();
        this.quantity = response.getQuantity();
    }
}