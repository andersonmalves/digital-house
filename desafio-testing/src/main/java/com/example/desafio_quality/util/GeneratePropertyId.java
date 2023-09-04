package com.example.desafio_quality.util;

public class GeneratePropertyId {
    private int next = ManipulateFile.loadProperties().size();
    private final static GeneratePropertyId idGenerator = new GeneratePropertyId();
    private GeneratePropertyId() {}

    public static GeneratePropertyId getIdGenerator() {
        return idGenerator;
    }

    public void resetId() {
        this.next = 0;
    }

    public int getNext() {
        return ++next;
    }
}
