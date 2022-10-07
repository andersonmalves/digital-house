package br.com.equipe7.desafio_spring.service;

import br.com.equipe7.desafio_spring.dto.ProductCreatedDTO;
import br.com.equipe7.desafio_spring.dto.ProductResponseDTO;
import br.com.equipe7.desafio_spring.exception.NotFoundException;
import br.com.equipe7.desafio_spring.exception.EmptyRequestException;
import br.com.equipe7.desafio_spring.service.interfaces.IProduct;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import br.com.equipe7.desafio_spring.util.ProductIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {
    @Autowired
    private ProductRepo repo;

    /**
     * Cadastro de Produtos
     * @author Giovanna
     * @param productList Novos produtos para cadastro
     * @return Retorna um DTO que exibe productId, name e quantity
     */
    @Override
    public List<ProductResponseDTO> save(List<ProductCreatedDTO> productList) {
        if (productList == null) {
            throw new EmptyRequestException("Não pode enviar 'payload' vazio");
        }

        List<ProductResponseDTO> response = new ArrayList<>();

        for(ProductCreatedDTO product : productList){
            int idProduct = ProductIdGenerator.getIdGenerator().getNext();
            Product p = new Product(idProduct, product.getName(), product.getCategory(),
                    product.getBrand(), product.getPrestige(), product.getPrice(),
                    product.isFreeShipping(), product.getQuantity());

            this.repo.saveProduct(p);
            response.add(new ProductResponseDTO(p));
        }

        return response;
    }

    /**
     * Implementa os filtros do Query Params
     * @author Ma, Theus, Felipe e Anderson
     * @param category Categoria do produto
     * @param freeShipping Frete gratis (boolean)
     * @param prestige Nível de satisfação do produto
     * @param order Ordenação do produto (alfabético ou preço)
     * @return Retorna uma lista de produtos de forma ordenada
     */
    public List<ProductResponseDTO> getAll(
            Optional<String> category,
            Optional<Boolean> freeShipping,
            Optional<String> prestige,
            Optional<Integer> order) {
        List<Product> productList = repo.getAllProducts();

        if (category.isPresent()) productList = filterCategory(productList, category.get());

        if (freeShipping.isPresent()) productList = filterShipping(productList, freeShipping.get());

        if (prestige.isPresent()) productList = filterPrestige(productList, prestige.get());

        if(order.isPresent()) productList = orderProductsList(order.get(), productList);

        return productList.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * Responsável por ordenar o produto pelo nome e preço
     * @author Felipe e Anderson
     * @param productList lista de produtos a ser filtrado
     * @param order altera ordenação dos produtos (alfabética ou preço)
     * @return Lista de produtos ordenada por nome ou preço
     */
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

    /**
     * Responsável pelo filtro por categoria
     * @author Ma & Theus
     * @param productList Lista de produtos a ser filtrado
     * @param category Nome da categoria usada como parâmetro de filtro
     * @return Uma lista de produtos usando a categoria como filtro
     */
    private List<Product> filterCategory(List<Product> productList, String category) {
        return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Responsável pelo filtro de frete grátis
     * @author Ma & Theus
     * @param productList Lista de produtos a ser filtrado
     * @param freeShipping Valor de shipping a ser filtrado
     * @return Uma lista de produtos usando o valor de shipping como filtro
     */
    private List<Product> filterShipping(List<Product> productList, Boolean freeShipping) {
        return productList.stream()
                .filter(product -> product.isFreeShipping() == freeShipping)
                .collect(Collectors.toList());
    }

    /**
     * Responsável pelo filtro pelo nível de satisfação
     * @author Ma & Theus
     * @param productList Lista de produtos a ser filtrado
     * @param prestige quantidade de estrelas do produto
     * @return Uma lista de produtos usando a quantidade de estrelas como filtro
     */
    private List<Product> filterPrestige(List<Product> productList, String prestige) {
        return productList.stream()
                .filter(product -> product.getPrestige().equals(prestige))
                .collect(Collectors.toList());
    }

    /**
     * Busca o produto pelo ID
     * @author Gabriel
     * @param id ID do produto
     * @return Produto pelo ID
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
    public void deleteProducts() {
        this.repo.deleteProducts();
        ProductIdGenerator.getIdGenerator().resetId();
    }

    @Override
    public List<Product> getByProduct(String category) {
        return repo.getByCategory(category);
    }
}
