package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaEnFaseException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public abstract class Fase {
    public void mover(Tablero tablero, Posicion origen, Direccion direccion, int distancia) {
        throw new AccionInvalidaEnFaseException("Solo se puede mover durante la Fase del Jugador.");
    }

    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        throw new AccionInvalidaEnFaseException("Solo se puede atacar durante la Fase del Jugador.");
    }

    public void deshacerMovimiento(Tablero tablero, Posicion posicionActual) {
        throw new AccionInvalidaEnFaseException("Solo se puede deshacer movimiento durante la Fase del Jugador.");
    }

    public abstract Fase avanzar(Tablero tablero);

    public void alFinalizar(Juego juego) {
    }
} 