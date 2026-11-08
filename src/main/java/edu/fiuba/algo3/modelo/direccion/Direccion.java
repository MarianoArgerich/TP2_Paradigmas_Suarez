package edu.fiuba.algo3.modelo.direccion;

import edu.fiuba.algo3.modelo.posicion.Posicion;


public interface Direccion {
    Posicion calcularSiguientePosicion(Posicion posicion);
}
