package edu.fiuba.algo3.modelo.direccion;

import edu.fiuba.algo3.modelo.posicion.Posicion;

public class SinDireccion implements Direccion{
    @Override
    public Posicion calcularSiguientePosicion(Posicion posicion) {
        return posicion;
    }
}
