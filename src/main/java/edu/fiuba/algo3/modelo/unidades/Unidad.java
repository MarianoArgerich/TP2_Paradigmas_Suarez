package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public abstract class Unidad {


    private final int vidaMax;
    private int rangoDeMovimiento;
    private int vidaActual;

    public Unidad(int vidaMax, int rangoDeMovimiento) {
        this.vidaMax = vidaMax;
        this.vidaActual = this.vidaMax;
        this.rangoDeMovimiento = rangoDeMovimiento;
    }

    public void verificarRangoDeMovimiento(int distancia){
        if (distancia > rangoDeMovimiento) {
            throw new RuntimeException("Fuera de rango");
        }
    }

    public boolean estaEn(Posicion otraPosicion, Tablero tablero) {
        return tablero.unidadEstaEn(this, otraPosicion);
    }

    public void mover(Direccion direccion, int distancia, Tablero tablero) {
        tablero.moverUnidad(this, direccion, distancia);
    }

    public boolean suVidaEsIgualA(int comparacion) {
        return vidaActual == comparacion;
    }

    public void recibirGolpe(int dano, Posicion posicion, Direccion direccion, Tablero tablero, Casilla casilla) {
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

    public void registrarMovimientoDesde(Posicion posicion) {
        
    }

    public void recibirEmpujeEn(Casilla casillaDestino, Casilla casillaOrigen) {
        casillaOrigen.recibirDanoPorColision();
    }

    public boolean detieneDisparo() {
        return true;
    }

    public boolean estaOcupada() {
        return true;
    }
}
