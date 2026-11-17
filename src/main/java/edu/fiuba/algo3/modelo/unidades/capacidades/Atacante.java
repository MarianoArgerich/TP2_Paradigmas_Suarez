package edu.fiuba.algo3.modelo.unidades.capacidades;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public interface Atacante {
    void atacar(Posicion origen, Posicion objetivo, Tablero tablero);
}
