package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public interface Arma {
    void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero);
}
