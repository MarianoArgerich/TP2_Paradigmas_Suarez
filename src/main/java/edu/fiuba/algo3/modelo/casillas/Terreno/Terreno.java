package edu.fiuba.algo3.modelo.casillas.Terreno;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Terreno {
    //TODO: Chequear
    public abstract boolean esTransitable();
    //TODO: Chequear
    public abstract void reaccionarAlIngresoDe(Unidad unidad);
    public abstract boolean puedeIngresar();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }
}
