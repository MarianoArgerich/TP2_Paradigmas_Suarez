package edu.fiuba.algo3.modelo.Juego;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Turno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class EnCurso extends EstadoJuego {

    @Override
    public void mover(Turno turno, Posicion origen, Direccion direccion, int distancia) {
        turno.mover(origen, direccion, distancia);
    }

    @Override
    public void atacar(Turno turno, Posicion origen, Posicion objetivo) {
        turno.atacar(origen, objetivo);
    }

    @Override
    public void deshacerMovimiento(Turno turno, Posicion posicionActual) {
        turno.deshacerMovimiento(posicionActual);
    }

    @Override
    public void avanzarFase(Turno turno, Juego juego) {
        turno.avanzarFase(juego);
    }

    @Override
    public EstadoJuego evaluarFin(Tablero tablero, int turnoActual, int turnosTotales) {
        if (tablero.getEnergia() <= 0 || tablero.cantidadDeMechsVivos() == 0) {
            return new Derrota();
        }
        if (turnoActual >= turnosTotales) {
            return new Victoria();
        }
        return this;
    }
}