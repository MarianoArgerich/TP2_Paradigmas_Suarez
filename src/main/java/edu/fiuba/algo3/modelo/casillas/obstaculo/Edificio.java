package edu.fiuba.algo3.modelo.casillas.obstaculo;

public class Edificio extends Obstaculo {

    public Edificio() {
        super(1);
    }

    @Override
    public boolean detieneDisparo() {
        return false;
    }
}
