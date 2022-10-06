package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ProductCreatedDTO;
import br.com.equipe7.desafio_spring.dto.ProductResponseDTO;
import br.com.equipe7.desafio_spring.exception.NotFoundException;
import br.com.equipe7.desafio_spring.exception.ProductEmptyException;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import br.com.equipe7.desafio_spring.util.ProductIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {
    @Autowired
    private ProductRepo repo;

    /**
     * @author Giovanna
     * @param newProduct
     * @return response -> um DTO que exibe productId, name e quantity
     */
    @Override
    public ProductResponseDTO save(ProductCreatedDTO newProduct) {
        if (newProduct == null) {
            throw new ProductEmptyException("Não pode enviar 'payload' vazio");
        }
        int idProduct = ProductIdGenerator.getIdGenerator().getNext();
        Product product = new Product(idProduct, newProduct.getName(), newProduct.getCategory(),
                newProduct.getBrand(), newProduct.getPrestige(), newProduct.getPrice(),
                newProduct.getFreeShipping(), newProduct.getQuantity());
        Product response = repo.saveProduct(product);
        return new ProductResponseDTO(response);
    }

    public List<ProductResponseDTO> getAll(
            Optional<String> category,
            Optional<Boolean> freeShipping,
            Optional<String> prestige,
            Optional<Integer> order) {
        List<Product> productList = repo.getProductList();

        if (category.isPresent()) productList = filterCategory(productList, category.get());

        if (freeShipping.isPresent()) productList = filterShipping(productList, freeShipping.get());

        if (prestige.isPresent()) productList = filterPrestige(productList, prestige.get());

        if(order.isPresent()) productList = orderProductsList(order.get(), productList);

        return productList.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    private List<Product> orderProductsList(int order, List<Product> productList) {
        switch (order) {
            case 0: return productList.stream()
                    .sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
            case 1: return productList.stream()
                    .sorted((v1,v2)-> v2.getName().compareTo(v1.getName())).collect(Collectors.toList());
            case 2: return productList.stream()
                    .sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
            default: return productList.stream()
                    .sorted((v1,v2)-> v2.getPrice().compareTo(v1.getPrice())).collect(Collectors.toList());
        }
    }

    private List<Product> filterCategory(List<Product> productList, String category) {
        return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    private List<Product> filterShipping (List<Product> productList, Boolean freeShipping) {
        return productList.stream()
                .filter(product -> product.isFreeShipping() == freeShipping)
                .collect(Collectors.toList());
    }

    private List<Product> filterPrestige (List<Product> productList, String prestige) {
        return productList.stream()
                .filter(product -> product.getPrestige().equals(prestige))
                .collect(Collectors.toList());
    }

    /**
     * Pega o produto pelo id
     * @param id
     * @return Product
     */
    @Override
    public Product getProductById(int id) {
        Optional<Product> product = this.repo.getProductById(id);

        if(product.isEmpty()) {
            throw new NotFoundException("Produto com id: " + id + " não encontrado");
        }

        return product.get();
    }

    @Override
    public List<Product> getByProduct(String category) {
        return repo.getByCategory(category);
    }
}
