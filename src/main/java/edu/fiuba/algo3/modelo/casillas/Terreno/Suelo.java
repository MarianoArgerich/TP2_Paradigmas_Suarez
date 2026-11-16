package edu.fiuba.algo3.modelo.casillas.Terreno;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Suelo extends Terreno {

    @Override
    public void reaccionarAlIngresoDe(Unidad unidad) {
    }

    @Override
    public void reaccionarAlIngresoDe(Unidad unidad, Casilla casilla) {
    }

    @Override
    public boolean puedeIngresar() {
        return true;
    }
}
