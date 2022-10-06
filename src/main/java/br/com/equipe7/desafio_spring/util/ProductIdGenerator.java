package br.com.equipe7.desafio_spring.util;

public class ProductIdGenerator {
    private int next = 0;
    private final static ProductIdGenerator idGenerator = new ProductIdGenerator();
    private ProductIdGenerator(){

    }

    public static ProductIdGenerator getIdGenerator() {
        return idGenerator;
    }

    public int getNext() {
        return ++next;
    }
}
