package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.ArrayList;
import java.util.List;

public class FaseVek implements Fase {

    public void ejecutar(List<Posicion> posicionesVeks, List<Vek> veks, List<Posicion> posicionesMechs, List<Mech> mechs, Tablero tablero){


        for (int i = 0; i < veks.size(); i++) {
            veks.get(i).ejecutarTurnoIA(posicionesVeks.get(i), tablero);
        }

        anunciarAparicionDeVek();
    }

    private void anunciarAparicionDeVek(){
        //TODO
        return;
    }
}
