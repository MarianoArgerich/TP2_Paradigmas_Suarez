package edu.fiuba.algo3.modelo.excepciones;

public class NoSePuedeDeshacerMovimiento extends RuntimeException {
    public NoSePuedeDeshacerMovimiento(String message) {
        super(message);
    }
}
