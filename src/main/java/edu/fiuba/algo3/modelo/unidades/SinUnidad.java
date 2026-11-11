package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class SinUnidad extends Unidad {
    public SinUnidad() {
        super(0, 0);
    }

    @Override
    public boolean estaOcupada() {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return false;
    }

    @Override
    public void recibirGolpe(int dano, Posicion posicion, Direccion direccion, Tablero tablero, Casilla casilla) {

    }

    @Override
    public void recibirDanio(int dano, Casilla casilla ){

    }

    @Override
    public void recibirEmpujeEn(Casilla casillaDestino, Casilla casillaOrigen) {
        casillaOrigen.transferirUnidadA(casillaDestino);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
