package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Efecto.Efecto;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Ocupante {
    public abstract boolean puedeIngresar();
    public abstract boolean esUnidad(Unidad unidad);
    public abstract boolean detieneDisparo();


    public abstract void aceptar(Efecto efecto, Terreno terreno, Casilla casillaActual, Mapa mapa);


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }
}
