package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Vek extends Unidad {
    protected Vek(int vidaMax, int rangoDeMovimiento) {
        super(vidaMax, rangoDeMovimiento);
    }
}
