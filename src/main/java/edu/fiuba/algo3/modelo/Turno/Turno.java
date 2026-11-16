package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.Fase;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public abstract class Turno {

    private Fase fase;

    public void jugar(List<Posicion> posicionesVeks, List<Vek> veks, List<Posicion> posicionesMechs, List<Mech> mechs, Tablero tablero){
        fase.ejecutar(posicionesVeks, veks, posicionesMechs, mechs, tablero);
    }
}
