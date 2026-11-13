package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public abstract class Arma {

    protected int danio;

    abstract public void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero);
}
