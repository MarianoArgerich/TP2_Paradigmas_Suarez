package edu.fiuba.algo3.modelo.casillas.transitable;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.SinUnidad;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Transitable extends Casilla {

    private Unidad unidad;

    public Transitable(Posicion posicion) {
        super(posicion);
        this.unidad = new SinUnidad();
    }

    @Override
    public boolean admitirUnidad(Unidad nuevaUnidad){
        if (this.unidad.estaOcupada()){
            return false;
        }
        this.unidad =  nuevaUnidad;
        return true;
    }

    @Override
    public boolean transferirUnidadA(Casilla casillaDestino) {
        if (casillaDestino.admitirUnidad(this.unidad)) {
            desocupar();
            return true;
        }
        return false;
    }

    @Override
    public void recibirGolpe(int danio, Direccion direccion, Tablero tablero) {
        this.unidad.recibirGolpe(danio, this.posicion, direccion, tablero, this);
    }

    @Override
    public void recibirEmpuje(Casilla casillaOrigen) {
        this.unidad.recibirEmpujeEn(this, casillaOrigen);
    }


    @Override
    public void recibirDisparo(int danio){
        this.unidad.recibirDanio(danio, this);
    }

    @Override
    public void recibirDanoPorColision() {
        this.unidad.recibirDanio(1, this);
    }

    @Override
    public boolean detieneDisparo() {
        return this.unidad.detieneDisparo();
    }

    @Override
    public void desocupar() {
        this.unidad = new SinUnidad();
    }

    @Override
    public boolean tieneUnidad(Unidad unidad) {
        return this.unidad.equals(unidad);
    }
}
