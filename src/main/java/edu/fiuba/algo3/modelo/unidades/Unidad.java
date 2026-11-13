package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public abstract class Unidad extends Ocupante {


    private final int vidaMax;
    private final int rangoDeMovimiento;
    private int vidaActual;

    protected Unidad(int vidaMax, int rangoDeMovimiento) {
        this.vidaMax = vidaMax;
        this.vidaActual = this.vidaMax;
        this.rangoDeMovimiento = rangoDeMovimiento;
    }

    //TODO: Chequear
    @Override
    public boolean puedeIngresar() {
        return false;
    }

    //TODO: Chequear
    @Override
    public boolean esUnidad(Unidad unidad) {
        return this == unidad;
    }

    //TODO: Chequear
    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        visitante.visitar(this,  terreno, casillaActual, mapa);
    }

    public void recibirDanio(int danio, Casilla casillaActual){
        this.vidaActual -= danio;
        if (this.vidaActual <= 0) {
            casillaActual.vaciar();
        }
    }

    //TODO: Chequear
    public boolean estaViva(){
        return this.vidaActual > 0;
    }

    public void verificarRangoDeMovimiento(int distancia){
        if (distancia > rangoDeMovimiento) {
            throw new DistanciaInvalidaException("Fuera de rango");
        }
    }

    public void mover(Direccion direccion, int distancia, Tablero tablero) {
        tablero.moverUnidad(this, direccion, distancia);
    }

    //TODO: Chequear
    public boolean suVidaEsIgualA(int comparacion) {
        return vidaActual == comparacion;
    }

    public void registrarMovimientoDesde(Posicion posicion) {
    }
}
