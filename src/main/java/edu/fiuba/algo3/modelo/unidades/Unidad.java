package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Unidad {

    private Posicion posicion;

    public Posicion obtenerPosicionActual(){
        return posicion;
    }

    public void setPosicion(Posicion posicion){
        this.posicion = posicion;
    }

}
