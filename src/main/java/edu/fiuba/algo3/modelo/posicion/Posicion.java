package edu.fiuba.algo3.modelo.posicion;

import edu.fiuba.algo3.modelo.direccion.*;


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

    public boolean estaEnLineaRectaCon(Posicion otra) {
        return (this.x == otra.x && this.y != otra.y) || (this.x != otra.x && this.y == otra.y);
    }

    public boolean estaEnArea(Posicion otra) {
        int deltaX = Math.abs(this.x - otra.x);
        int deltaY = Math.abs(this.y - otra.y);

        return deltaX <= 1 && deltaY <= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return x == posicion.x && y == posicion.y;
    }

    public Direccion calcularDireccionHacia(Posicion objetivo) {
        int deltaX = objetivo.x - this.x;
        int deltaY = objetivo.y - this.y;

        if (deltaY < 0){
            return new Arriba();
        }
        if (deltaY > 0){
            return new Abajo();
        }
        if (deltaX > 0){
            return new Derecha();
        }
        return new Izquierda();
    }
}
