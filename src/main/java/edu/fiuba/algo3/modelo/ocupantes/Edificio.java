package edu.fiuba.algo3.modelo.ocupantes;

import edu.fiuba.algo3.modelo.casillas.Casilla;

public class Edificio extends Casilla implements Ocupante{

    private int vida = 1;

    @Override
    public void recibirDanio() {
        this.vida -= 1;
    }

    @Override
    public boolean esEmpujable() {
        return false;
    }
}
