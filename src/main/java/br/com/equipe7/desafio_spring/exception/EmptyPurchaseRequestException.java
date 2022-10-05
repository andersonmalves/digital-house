package br.com.equipe7.desafio_spring.exception;

public class EmptyPurchaseRequestException extends RuntimeException {
    public EmptyPurchaseRequestException(String message) {
        super(message);
    }
}
