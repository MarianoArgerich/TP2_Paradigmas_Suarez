package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Obstaculo extends Ocupante {

    @Override
    public boolean puedeIngresar() {
        return false;
    }


    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {

    }

}
