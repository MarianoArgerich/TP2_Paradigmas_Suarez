package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public interface AparicionVek {
    boolean estaEn(Posicion posicion);
    void resolver(Tablero tablero);
}
