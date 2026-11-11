package edu.fiuba.algo3.modelo.casillas.obstaculo;

public class Montania extends Obstaculo {

    public Montania() {
        super(-1);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && this.getClass().equals(obj.getClass());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
