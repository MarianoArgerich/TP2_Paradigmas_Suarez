package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Vek extends Unidad {
    protected int danio;
    protected Vek(int vidaMax, int rangoDeMovimiento, int danio) {
        super(vidaMax, rangoDeMovimiento);
        this.danio = danio;
    }

    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {

    }

    @Override
    public void caerAlAguaEn(Casilla casilla) {
        casilla.vaciar();
    }
}
