package br.com.equipe7.desafio_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PurchaseRequestDTO {
    private List<ProductPurchaseDTO> articlesPurchaseRequest;
}
