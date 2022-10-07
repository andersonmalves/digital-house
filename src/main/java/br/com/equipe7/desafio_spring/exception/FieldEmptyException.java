package br.com.equipe7.desafio_spring.exception;

public class FieldEmptyException extends RuntimeException {
    public FieldEmptyException(String message) {
        super(message);
    }
}
