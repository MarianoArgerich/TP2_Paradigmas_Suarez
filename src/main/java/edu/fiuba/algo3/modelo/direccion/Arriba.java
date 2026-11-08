package edu.fiuba.algo3.modelo.direccion;

import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.List;

public class Arriba implements Direccion {

    @Override
    public Posicion calcularSiguientePosicion(Posicion posicion) {
        return posicion.desplazar(0, 1);
    }
}
