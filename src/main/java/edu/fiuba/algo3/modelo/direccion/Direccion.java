package edu.fiuba.algo3.modelo.direccion;

import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.List;

public interface Direccion {
    public Posicion calcularSiguientePosicion(Posicion posicion);
}
