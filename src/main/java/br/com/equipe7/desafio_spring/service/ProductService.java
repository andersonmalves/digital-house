package br.com.equipe7.desafio_spring.service;


import br.com.equipe7.desafio_spring.exception.NotFoundException;
import br.com.equipe7.desafio_spring.model.Product;
import br.com.equipe7.desafio_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ProductService implements IProduct{
    @Autowired
    private ProductRepo repo;
    private ProductRepo sortListAsc;

    @Override
    public List<Product> getAll() {
        return repo.getProductList();
    }

    @Override
    public Product getProductById(int id) {
        Optional<Product> product = this.repo.getProductById(id);

        if(product.isEmpty()) {
            throw new NotFoundException("Produto com id: " + id + " não encontrado");
        }

        return product.get();
    }
}
