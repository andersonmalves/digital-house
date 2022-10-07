package br.com.equipe7.desafio_spring.util;

public class ClientIdGenerator {
    private int next = ManipulateFile.loadClients().size();
    private final static ClientIdGenerator idGenerator = new ClientIdGenerator();

    private ClientIdGenerator() {}

    public static ClientIdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void resetId() {
        this.next = 0;
    }

    public int getNext() {
        return ++next;
    }
}
