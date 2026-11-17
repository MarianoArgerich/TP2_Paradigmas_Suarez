package edu.fiuba.algo3.modelo.unidades.Vek.Ataque;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public abstract class Ataque {

    protected int danio;

    abstract public void ejecutarAtaque(Posicion origen, Direccion direccion, Tablero tablero);
}
