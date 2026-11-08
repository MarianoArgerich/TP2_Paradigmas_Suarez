package edu.fiuba.algo3.modelo.ocupantes;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Edificio extends Casilla implements Ocupante{

    private int vida = 1;

    @Override
    public void recibirDanio() {
        this.vida -= 1;
    }

    @Override
    public void empujar() {
        return;
    }

    @Override
    public void ocupar(Unidad unidad){
        throw new RuntimeException("casilla invalida");
    }
}
