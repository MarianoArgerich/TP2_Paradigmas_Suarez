package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.transitable.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Unidad {


    protected Posicion posicion;
    private final int vidaMaxima;
    private int movimiento;
    private int vidaActual;

    public Unidad(int vidaMaxima, int movimiento) {
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = this.vidaMaxima;
        this.movimiento = movimiento;
        posicion = null;
    }
    public void verificarRangoDeMovimiento(int distancia){
        if (distancia < 0 || distancia > movimiento) {
            throw new RuntimeException("Fuera de rango");
        }
    }
    public void moverA(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }

    public boolean estaEn(Posicion otraPosicion) {
        return this.posicion != null && this.posicion.equals(otraPosicion);
    }

    public void mover(Direccion direccion, int distancia, Tablero tablero) {
        tablero.moverUnidad(this, this.posicion, direccion, distancia);
    }

    public int obtenerVidaActual() {
        return vidaActual;
    }

    public void recibirDanio(int dano, Posicion posicion, Direccion direccion, Tablero tablero, Casilla casilla) {
        recibirDanio(dano, casilla);

        if (this.vidaActual > 0) {
            tablero.empujarDesde(posicion, direccion);
        }
    }

    public void recibirDanio(int dano, Casilla casilla ) {
        this.vidaActual -= dano;
        if (this.vidaActual <= 0) {
            casilla.desocupar();
        }
    }

}
