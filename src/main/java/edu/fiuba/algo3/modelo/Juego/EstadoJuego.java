package edu.fiuba.algo3.modelo.Juego;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Turno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.JuegoFinalizadoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public abstract class EstadoJuego {

    public void mover(Turno turno, Posicion origen, Direccion direccion, int distancia) {
        throw new JuegoFinalizadoException("La partida ya finalizo.");
    }

    public void atacar(Turno turno, Posicion origen, Posicion objetivo) {
        throw new JuegoFinalizadoException("La partida ya finalizo.");
    }

    public void deshacerMovimiento(Turno turno, Posicion posicionActual) {
        throw new JuegoFinalizadoException("La partida ya finalizo.");
    }

    public void avanzarFase(Turno turno, Juego juego) {
        throw new JuegoFinalizadoException("La partida ya finalizo.");
    }

    public EstadoJuego evaluarFin(Tablero tablero, int turnoActual, int turnosTotales) {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }
}
