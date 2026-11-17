package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.*;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.ArrayList;
import java.util.List;

public abstract class Turno {

    private final List<Fase> fases;

    public Turno() {
        this.fases = List.of( new FaseVek(), new FaseJugador(), new Resolucion(),  new Emergencia());
    }

    public void jugar(List<Posicion> posicionesVeks, List<Vek> veks, List<Posicion> posicionesMechs, List<Mech> mechs, Tablero tablero){
        for (Fase fase : fases) {
            fase.ejecutar(posicionesVeks, veks, posicionesMechs, mechs, tablero);
        }
    }
}
