package edu.fiuba.algo3.modelo.ocupantes;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Montania extends Casilla{


    public Montania(Posicion posicion) {
        super(posicion);
    }

    @Override
    public void ocupar(Unidad unidad){
        throw new RuntimeException("casilla invalida");
    }
}
