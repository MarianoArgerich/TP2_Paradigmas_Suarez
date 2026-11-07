package edu.fiuba.algo3.modelo.ocupantes;

import edu.fiuba.algo3.modelo.casillas.Casilla;

public class Montania extends Casilla implements Ocupante {

    @Override
    public void recibirDanio() {
        return;
    }

    @Override
    public boolean esEmpujable() {
        return false;
    }
}
