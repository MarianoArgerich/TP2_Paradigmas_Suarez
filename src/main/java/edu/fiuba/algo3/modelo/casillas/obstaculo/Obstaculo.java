package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Obstaculo implements Ocupante {

    private int vida;

    public Obstaculo(int vida) {
        this.vida = vida;
    }

    @Override
    public boolean puedeIngresar() {
        return false;
    }

    @Override
    public boolean esUnidad(Unidad unidad) {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public void recibirGolpe(int danio, Direccion direccion, Tablero tablero, Casilla casillaActual) {
        recibirDanio(danio, casillaActual);
    }

    @Override
    public void recibirEmpuje(Casilla casillaOrigen, Casilla casillaActual) {
        casillaOrigen.recibirDanioPorColision();
        this.recibirDanioPorColision(casillaActual);
    }

    @Override
    public void recibirDisparo(int danio, Casilla casillaActual) {
        recibirDanio(danio, casillaActual);
    }

    @Override
    public void recibirDanioPorColision(Casilla casillaActual) {
        recibirDanio(1, casillaActual);
    }

    private void recibirDanio(int danio, Casilla casillaActual) {
        this.vida -= danio;
        if (this.vida <= 0) {
            casillaActual.vaciar();
        }
    }

    @Override
    public boolean estaVacio() {
        return false;
    }

}
