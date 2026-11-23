package edu.fiuba.algo3.modelo.posicion;

import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.ObjetivoDesalineadoException;

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

    private boolean estaEnLineaRectaCon(Posicion otra) {
        return (this.x == otra.x) || (this.y == otra.y);
    }

    private boolean estaEnArea(Posicion otra) {
        int deltaX = Math.abs(this.x - otra.x);
        int deltaY = Math.abs(this.y - otra.y);

        return deltaX <= 1 && deltaY <= 1;
    }

    public void validarAdyacenciaCon(Posicion otra) {
        if (this.distanciaA(otra) != 1) {
            throw new DistanciaInvalidaException("El objetivo debe estar en una casilla adyacente.");
        }
    }

    public void validarAlineacionCon(Posicion otra) {
        if (!estaEnLineaRectaCon(otra)) {
            throw new ObjetivoDesalineadoException("El objetivo no esta alineado para atacar");
        }
    }

    public void validarFueraDeAreaDe(Posicion otra) {
        if (estaEnArea(otra)) {
            throw new DistanciaInvalidaException("No se puede atacar a una casilla dentro del rango prohibido 3x3");
        }
    }

    public Direccion calcularDireccionHacia(Posicion objetivo) {
        validarAlineacionCon(objetivo);
        int deltaX = objetivo.x - this.x;
        int deltaY = objetivo.y - this.y;

        if (deltaY < 0){
            return new Abajo();
        }
        if (deltaY > 0){
            return new Arriba();
        }
        if (deltaX > 0){
            return new Derecha();
        }
        return new Izquierda();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return x == posicion.x && y == posicion.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean esMenorQue(Posicion otra) {
        if (this.y != otra.y) {
            return this.y < otra.y;
        }
        return this.x < otra.x;
    }


    public int distanciaA(Posicion otra) {
        return Math.abs(this.x - otra.x) + Math.abs(this.y - otra.y);
    }
}
