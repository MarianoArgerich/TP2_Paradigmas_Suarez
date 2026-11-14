package edu.fiuba.algo3.modelo.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.unidades.Unidad;
public interface Visitante {
    void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Mapa mapa);
    void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Mapa mapa);
    void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual, Mapa mapa);
}
