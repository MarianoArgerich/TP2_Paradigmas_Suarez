package edu.fiuba.algo3.modelo.casillas.Terreno;


import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Agua extends Terreno {

    @Override
    public void reaccionarAlIngresoDe(Unidad unidad, Casilla casilla) {
        unidad.caerAlAguaEn(casilla);
    }

    @Override
    public boolean puedeIngresar() {
        return true;
    }

    @Override
    public void verificarAtaqueDe(Unidad unidad) {
        unidad.verificarAtaqueEnAgua();
    }
}
