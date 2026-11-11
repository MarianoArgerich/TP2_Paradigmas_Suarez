package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Ocupante {
    abstract public boolean puedeIngresar();
    abstract public boolean esUnidad(Unidad unidad);
    abstract public boolean detieneDisparo();

    abstract public void recibirGolpe(int danio, Direccion direccion, Tablero tablero, Casilla casillaActual);
    abstract public void recibirDisparo(int danio, Casilla casillaActual);
    abstract public void recibirDanioPorColision(Casilla casillaActual);

    abstract public void empujarA(Casilla origen, Casilla destino);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }
}
