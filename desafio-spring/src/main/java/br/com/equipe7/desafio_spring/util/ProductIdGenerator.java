package br.com.equipe7.desafio_spring.util;

public class ProductIdGenerator {
    private int next = ManipulateFile.loadProducts().size();
    private final static ProductIdGenerator idGenerator = new ProductIdGenerator();
    private ProductIdGenerator() {}

    /**
     * Gerador de ID
     * @author Gabriel e Giovanna
     * @return Retorna o id gerado
     */
    public static ProductIdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void resetId() {
        this.next = 0;
    }

    public int getNext() {
        return ++next;
    }
}
