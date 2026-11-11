package edu.fiuba.algo3.modelo.casillas;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class CasillaNula extends Casilla {
    public CasillaNula() {
        super(new Posicion(-1, -1));
    }

    @Override
    public boolean admitirOcupante(Ocupante ocupante){
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }
}
