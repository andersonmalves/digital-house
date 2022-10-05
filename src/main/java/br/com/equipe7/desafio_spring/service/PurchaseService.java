package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ProductPurchaseDTO;
import br.com.equipe7.desafio_spring.dto.PurchaseRequestDTO;
import br.com.equipe7.desafio_spring.dto.TicketResponseDTO;
import br.com.equipe7.desafio_spring.exception.EmptyPurchaseRequestException;
import br.com.equipe7.desafio_spring.exception.NotFoundException;
import br.com.equipe7.desafio_spring.exception.PurchaseWithInvalidQuantityException;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.model.Ticket;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import br.com.equipe7.desafio_spring.repository.PurchaseRepo;
import br.com.equipe7.desafio_spring.util.TicketNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService implements IPurchase{

    @Autowired
    private PurchaseRepo purchaseRepo;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public TicketResponseDTO purchase(PurchaseRequestDTO request) {
        if(request == null) {
            throw new EmptyPurchaseRequestException("Request com payload vazio");
        }

        List<ProductPurchaseDTO> articlesPurchaseRequest = request.getArticlesPurchaseRequest();
        List<Product> selectedProducts = new ArrayList<>();
        BigDecimal totalTicket = BigDecimal.ZERO;

        for(ProductPurchaseDTO productPurchase : articlesPurchaseRequest){
            Optional<Product> product =  this.productRepo.getProductById(productPurchase.getProductId());

            if(product.isEmpty()){
                throw new NotFoundException("Produto com id: "
                        + productPurchase.getProductId()
                        + " não encontrado");
            }

            if(productPurchase.getQuantity() <= 0) {
                throw new PurchaseWithInvalidQuantityException("O produto com id: "
                        + productPurchase.getProductId()
                        + " deve possuir uma quantidade válida" );
            }

            BigDecimal totalProductPrice = getProductPrice(product.get(), productPurchase.getQuantity());
            totalTicket = totalTicket.add(totalProductPrice);

            Product selectedProduct = product.get();
            selectedProduct.setQuantity(productPurchase.getQuantity());
            selectedProducts.add(selectedProduct);
        }

        int ticketId = TicketNumberGenerator.getInstance().getNext();
        return new TicketResponseDTO(new Ticket(ticketId, selectedProducts, totalTicket));
    }

    private BigDecimal getProductPrice(Product product, int quantity) {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }


}
