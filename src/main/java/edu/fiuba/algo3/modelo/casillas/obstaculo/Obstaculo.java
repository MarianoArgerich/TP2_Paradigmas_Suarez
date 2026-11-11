package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Obstaculo extends Ocupante {

    @Override
    public boolean puedeIngresar() {
        return false;
    }

    @Override
    public boolean esUnidad(Unidad unidad) {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }

}
