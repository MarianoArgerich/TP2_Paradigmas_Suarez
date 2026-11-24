package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class FaseResolucion extends Fase {

    @Override
    public Fase avanzar(Tablero tablero) {
        tablero.resolverAtaques();
        return new FaseEmergencia();
    }
}