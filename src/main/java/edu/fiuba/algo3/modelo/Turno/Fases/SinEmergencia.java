package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class SinEmergencia implements AparicionVek {

    @Override
    public boolean estaEn(Posicion posicion) {
        return false;
    }

    @Override
    public void resolver(Tablero tablero) {
    }
}
