package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class FaseEmergencia implements Fase {

    @Override
    public void ejecutar(List<Posicion> posicionesVeks, List<Vek> veks, List<Posicion> posicionesMechs, List<Mech> mechs, Tablero tablero){
        tablero.resolverEmergencia();

    };
}