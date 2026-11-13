package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Ocupante {

    //TODO: Chequear
    public abstract boolean puedeIngresar();

    //TODO: Chequear
    public abstract boolean esUnidad(Unidad unidad);

    //TODO: Chequear
    public abstract boolean detieneDisparo();

    public abstract void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Mapa mapa);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }
}
