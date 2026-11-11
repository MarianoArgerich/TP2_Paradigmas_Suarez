package edu.fiuba.algo3.modelo.Efecto;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.unidades.Unidad;
public interface Efecto {
    void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Mapa mapa);
    void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Mapa mapa);
}
