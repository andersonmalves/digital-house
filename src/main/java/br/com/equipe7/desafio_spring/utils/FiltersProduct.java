package br.com.equipe7.desafio_spring.utils;

import br.com.equipe7.desafio_spring.model.Product;
import java.util.List;
import java.util.stream.Collectors;

public class FiltersProduct {
    public static List<Product> filterCategory(List<Product> productList, String category) {
         return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public static List<Product> filterShipping (List<Product> productList, Boolean freeShipping) {
        return productList.stream()
                .filter(product -> product.isFreeShipping() == freeShipping)
                .collect(Collectors.toList());
    }

    public static List<Product> filterPrestige (List<Product> productList, String prestige) {
        return productList.stream()
                .filter(product -> product.getPrestige().equals(prestige))
                .collect(Collectors.toList());
    }

}

