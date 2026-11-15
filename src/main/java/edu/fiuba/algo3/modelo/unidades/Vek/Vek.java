package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Vek extends Unidad {
    protected Vek(int vidaMax, int rangoDeMovimiento) {
        super(vidaMax, rangoDeMovimiento);
    }

    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {

    }

    @Override
    public void caerAlAguaEn(Casilla casilla) {
        casilla.vaciar();
    }
}
