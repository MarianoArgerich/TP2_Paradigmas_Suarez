package edu.fiuba.algo3.modelo.casillas.transitable;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Transitable extends Casilla{
    private Unidad unidad;

    public Transitable(Posicion posicion) {
        super(posicion);
    }

    public void ocupar(Unidad unidad){
        if(estaOcupada()){
            throw new RuntimeException("casilla ocupada");
        }
        this.unidad = unidad;
        this.unidad.moverA(this.posicion);
    }

    public void transferirUnidadA(Casilla casilla) {
        casilla.ocupar(this.unidad);
        desocupar();
    }

    public void unidadRecibirGolpe(int danio, Direccion direccion, Tablero tablero) {
        if (!estaOcupada()) {
            return;
        }

        this.unidad.recibirGolpe(danio, this.posicion, direccion, tablero, this);
    }

    public void recibirEmpuje(Casilla casilla) {
        if (estaOcupada()) {

            this.unidad.recibirDanio(1, this);
            casilla.recibirDanoPorColision();
        } else {
            casilla.transferirUnidadA(this);
        }
    }

    public boolean detieneDisparo() {
        return estaOcupada();
    }

    public void recibirDanoPorColision() {
        if (estaOcupada()) {
            this.unidad.recibirDanio(1, this);
        }
    }

    public void desocupar() {
        this.unidad = null;
    }

    private boolean estaOcupada() {
        return unidad != null;
    }
}
