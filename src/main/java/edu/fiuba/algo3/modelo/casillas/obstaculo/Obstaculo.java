package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Obstaculo implements Ocupante {

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
    public void recibirGolpe(int danio, Direccion direccion, Tablero tablero, Casilla casillaActual) {}

    @Override
    public void recibirDisparo(int danio, Casilla casillaActual) {}

    @Override
    public void recibirDanioPorColision(Casilla casillaActual) {}

    @Override
    public void empujarA(Casilla origen, Casilla destino) {}

}
