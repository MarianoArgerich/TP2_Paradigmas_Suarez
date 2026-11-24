package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class FaseResolucion extends Fase {

    @Override
    public Fase avanzar(Tablero tablero) {
        tablero.resolverAtaques();
        return new FaseEmergencia();
    }

    @Override
    public void alFinalizar(Juego juego) {
        juego.evaluarFinDePartida();
    }
}