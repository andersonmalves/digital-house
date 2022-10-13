package com.example.desafio_quality.util;

public class GenerateDistrictId {
    private int next = ManipulateFile.loadDistricts().size();
    private final static GenerateDistrictId idGenerator = new GenerateDistrictId();
    private GenerateDistrictId() {}

    public static GenerateDistrictId getIdGenerator() {
        return idGenerator;
    }

    public void resetId() {
        this.next = 0;
    }

    public int getNext() {
        return ++next;
    }
}
