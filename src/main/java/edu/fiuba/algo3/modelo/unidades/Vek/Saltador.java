package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;

public class Saltador extends Vek {



    public Saltador() {
        super(2, 3, 2);
    }

    @Override
    public void deshacerMovimientoDesde(Casilla casilla, Mapa mapa) {

    }

    @Override
    public void deshacerMovimientoDesde(Casilla casilla, Mapa mapa) {
    }

    @Override
    public void caerAlAguaEn(Casilla casilla) {
        super.caerAlAguaEn(casilla);
        this.vidaActual = 0;

    }
}
