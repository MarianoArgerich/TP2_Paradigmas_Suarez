package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.transitable.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Obstaculo extends Casilla {

    private int vida;

    public Obstaculo(Posicion posicion, int vida) {
        super(posicion);
        this.vida = vida;
    }

    @Override
    public void ocupar(Unidad unidad) {
        throw new RuntimeException("casilla invalida");
    }



    @Override
    public void transferirUnidadA(Casilla casilla) {
        throw new RuntimeException("No se puede atravesar un Obstaculo");
    }

    @Override
    public void unidadRecibirGolpe(int danio, Direccion direccion, Tablero tablero) {
    }

    @Override
    public void recibirEmpuje(Casilla casillaOrigen) {
        casillaOrigen.recibirDanoPorColision();
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public void recibirDanoPorColision() {
        throw new RuntimeException("Un obstaculo no tiene unidades");

    }

    @Override
    public void desocupar() {
        throw new RuntimeException("No se puede desocupar un obstaculo");

    }

    public void unidadGolpeada(int danio) {
    }
}
