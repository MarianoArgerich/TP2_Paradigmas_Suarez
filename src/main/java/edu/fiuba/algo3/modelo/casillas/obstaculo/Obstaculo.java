package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
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
    public boolean admitirUnidad(Unidad unidad) {
        return false;
    }

    @Override
    public boolean transferirUnidadA(Casilla casilla) {
        return false;
    }

    @Override
    public void recibirGolpe(int danio, Direccion direccion, Tablero tablero) {
        recibirDanio(danio);
    }

    @Override
    public void recibirEmpuje(Casilla casillaOrigen) {
        casillaOrigen.recibirDanoPorColision();
        this.recibirDanoPorColision();
    }

    @Override
    public void recibirDisparo(int danio) {
        recibirDanio(danio);
    }

    @Override
    public void recibirDanoPorColision() {
        recibirDanio(1);
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public boolean tieneUnidad(Unidad unidad){
        return false;
    }

    @Override
    public void desocupar() {

    }

    private void recibirDanio(int danio) {
        this.vida -= danio;
    }
}
