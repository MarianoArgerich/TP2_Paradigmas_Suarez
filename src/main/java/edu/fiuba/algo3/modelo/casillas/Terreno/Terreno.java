package edu.fiuba.algo3.modelo.casillas.Terreno;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Terreno {

    public abstract void reaccionarAlIngresoDe(Unidad unidad, Casilla casilla);
    public abstract boolean puedeIngresar();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }

    public void verificarAtaqueDe(Unidad unidad) {}
}
