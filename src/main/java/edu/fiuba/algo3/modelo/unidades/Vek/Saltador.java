package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.casillas.Casilla;

public class Saltador extends Vek {



    public Saltador() {
        super(2, 3, 2);
    }


    @Override
    public void caerAlAguaEn(Casilla casilla) {
        this.vidaActual = 0;
        super.caerAlAguaEn(casilla);
    }
}
