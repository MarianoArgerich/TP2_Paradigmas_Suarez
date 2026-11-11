package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public abstract class Unidad implements Ocupante {


    private final int vidaMax;
    private final int rangoDeMovimiento;
    private int vidaActual;

    public Unidad(int vidaMax, int rangoDeMovimiento) {
        this.vidaMax = vidaMax;
        this.vidaActual = this.vidaMax;
        this.rangoDeMovimiento = rangoDeMovimiento;
    }

    @Override
    public boolean puedeIngresar() {
        return false;
    }

    @Override
    public boolean esUnidad(Unidad unidad) {
        return this.equals(unidad);
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public void recibirGolpe(int danio, Direccion direccion, Tablero tablero, Casilla casillaActual) {
        recibirDanio(danio, casillaActual);

        if (this.vidaActual > 0) {
            casillaActual.empujarHacia(direccion, tablero);
        }
    }

    @Override
    public void recibirDisparo(int danio, Casilla casillaActual) {
        recibirDanio(danio, casillaActual);
    }

    @Override
    public void recibirEmpuje(Casilla casillaOrigen, Casilla casillaDestino){
        casillaOrigen.recibirDanioPorColision();
    }

    @Override
    public void recibirDanioPorColision(Casilla casillaActual){
        recibirDanio(1, casillaActual);
    }


    public void recibirDanio(int danio, Casilla casillaActual ) {
        this.vidaActual -= danio;
        if (this.vidaActual <= 0) {
            casillaActual.vaciar();
        }
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

    public void registrarMovimientoDesde(Posicion posicion) {
    }

    @Override
    public boolean estaVacio() {
        return false;
    }

    public boolean estaOcupada() {
        return true;
    }
}
