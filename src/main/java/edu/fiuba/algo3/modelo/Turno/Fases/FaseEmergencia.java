package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class FaseEmergencia extends Fase {

    @Override
    public Fase avanzar(Tablero tablero) {
        tablero.resolverEmergencia();
        return new FaseVek();
    }

    @Override
    public void alFinalizar(Juego juego) {
        juego.avanzarNumeroDeTurno();
    }
}