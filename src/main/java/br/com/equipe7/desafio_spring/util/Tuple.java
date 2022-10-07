package br.com.equipe7.desafio_spring.util;

import lombok.Getter;

@Getter
/**
 * Classe utilitária que simula uma Tupla
 * @author Theus e Gabriel
 * @param T Tipo do primeiro parâmetro da tupla
 * @param A Tipo do segundo parâmetro da tupla
 * @return Retorna uma tupla
 */
public class Tuple<T, A> {
    private T id;
    private A client;

    public Tuple(T id, A client) {
        this.id = id;
        this.client = client;
    }
}
