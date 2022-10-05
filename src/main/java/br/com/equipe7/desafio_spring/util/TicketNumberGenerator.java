package br.com.equipe7.desafio_spring.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TicketNumberGenerator {
        private int next;
        private final static TicketNumberGenerator instance = new TicketNumberGenerator();

        public static TicketNumberGenerator getInstance() {
            return instance;
        }

        public int getNext() {
            return ++next;
        }

}
