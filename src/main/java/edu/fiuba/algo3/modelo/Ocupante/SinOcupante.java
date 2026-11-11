package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class SinOcupante implements Ocupante{

    @Override
    public boolean puedeIngresar() {
        return true;
    }

    @Override
    public boolean esUnidad(Unidad unidad) {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return false;
    }

    @Override
    public void recibirGolpe(int danio, Direccion direccion, Tablero tablero, Casilla casillaActual) {}

    @Override
    public void recibirDisparo(int danio, Casilla casillaActual) {}

    @Override
    public void recibirDanioPorColision(Casilla casillaActual) {}

    @Override
    public boolean estaVacio() {
        return true;
    }
}
