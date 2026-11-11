package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Ocupante {
    public abstract boolean puedeIngresar();
    public abstract boolean esUnidad(Unidad unidad);
    public abstract boolean detieneDisparo();

    public abstract void recibirGolpe(int danio, Direccion direccion, Tablero tablero, Casilla casillaActual);
    public abstract void recibirDisparo(int danio, Casilla casillaActual);
    public abstract void recibirDanioPorColision(Casilla casillaActual);

    abstract public void empujarA(Casilla origen, Casilla destino);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }
}
