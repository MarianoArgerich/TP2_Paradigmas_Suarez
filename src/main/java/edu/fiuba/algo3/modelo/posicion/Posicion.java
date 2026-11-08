package edu.fiuba.algo3.modelo.posicion;

import edu.fiuba.algo3.modelo.direccion.Direccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Posicion {
    private final int x;
    private final int y;

    public Posicion(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Posicion desplazar(int deltaX, int deltaY){
        return new Posicion(x + deltaX, y + deltaY);
    }

    public Posicion siguienteEn(Direccion direccion){
        return direccion.calcularSiguientePosicion(this);
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return x == posicion.x && y == posicion.y;
    }
}
