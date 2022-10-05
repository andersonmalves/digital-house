package br.com.equipe7.desafio_spring.exception;

public class PurchaseWithInvalidQuantityException extends RuntimeException{
    public PurchaseWithInvalidQuantityException(String message) {
        super(message);
    }
}
