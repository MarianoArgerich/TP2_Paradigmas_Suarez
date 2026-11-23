package edu.fiuba.algo3.modelo.Visitante.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.unidades.Unidad;
public abstract class Visitante {
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Tablero tablero) {}
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Tablero tablero) {}
    public void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual,Tablero tablero) {}
    public void visitar(Montania montania, Terreno terreno, Casilla casillaActual, Tablero tablero) {}
}
