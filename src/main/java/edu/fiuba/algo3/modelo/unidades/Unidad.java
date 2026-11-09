package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.ocupantes.Ocupante;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Unidad implements Ocupante {

    private Posicion posicion;
    private int vida;

    public Unidad(int vida) {
        this.vida = vida;
    }

    public int obtenerVida() {
        return vida;
    }

    @Override
    public void recibirDanio(int cantidad) {
        vida -= cantidad;
    }

    @Override
    public void empujar() {
        // lo completa quien haga el Puño
    }

    public Posicion obtenerPosicionActual() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
}