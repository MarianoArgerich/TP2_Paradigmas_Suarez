package edu.fiuba.algo3.modelo.ocupantes;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Montania extends Casilla implements Ocupante {

    @Override
    public void recibirDanio() {
    }

    @Override
    public void empujar() {
    }

    @Override
    public void ocupar(Unidad unidad){
        throw new RuntimeException("casilla invalida");
    }
}
