package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ProductPurchaseDTO;
import br.com.equipe7.desafio_spring.dto.PurchaseRequestDTO;
import br.com.equipe7.desafio_spring.dto.TicketResponseDTO;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import br.com.equipe7.desafio_spring.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService implements IPurchase{

    @Autowired
    private PurchaseRepo purchaseRepo;
    private ProductRepo productRepo;

    @Override
    public TicketResponseDTO purchase(PurchaseRequestDTO request) {
        List<ProductPurchaseDTO> articlesPurchaseRequest = request.getArticlesPurchaseRequest();
        List<Product> products = new ArrayList<>();
        BigDecimal total;

//        for(ProductPurchaseDTO product : articlesPurchaseRequest){
//            this.productRepo
//        }


    }
}
