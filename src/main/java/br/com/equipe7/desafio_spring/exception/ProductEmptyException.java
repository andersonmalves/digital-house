package br.com.equipe7.desafio_spring.exception;

public class ProductEmptyException extends RuntimeException {
    public ProductEmptyException(String message) {
        super(message);
    }
}
