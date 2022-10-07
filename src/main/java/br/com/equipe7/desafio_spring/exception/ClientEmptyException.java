package br.com.equipe7.desafio_spring.exception;

public class ClientEmptyException extends RuntimeException {
    public ClientEmptyException(String message) {
        super(message);
    }
}
