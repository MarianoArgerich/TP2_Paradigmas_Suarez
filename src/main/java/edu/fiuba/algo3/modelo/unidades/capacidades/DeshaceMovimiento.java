package edu.fiuba.algo3.modelo.unidades.capacidades;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;

public interface DeshaceMovimiento {
    void deshacerMovimientoDesde(Casilla casillaActual, Mapa mapa);
}
