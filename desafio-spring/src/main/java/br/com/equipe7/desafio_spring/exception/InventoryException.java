package br.com.equipe7.desafio_spring.exception;

public class InventoryException extends RuntimeException{
    public InventoryException(String message) {
        super(message);
    }
}
