package edu.fiuba.algo3.modelo.unidades.capacidades;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;

public interface Movible {
    void moverDesde(Casilla origen, Direccion direccion, int distancia, Tablero tablero);
}
