package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Edificio extends Obstaculo {

    public Edificio(Posicion posicion) {
        super(posicion, 1);
    }

    @Override
    public boolean detieneDisparo() {
        return false;
    }
}
