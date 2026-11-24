package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class FaseJugador extends Fase {

    @Override
    public void mover(Tablero tablero, Posicion origen, Direccion direccion, int distancia) {
        tablero.mover(origen, direccion, distancia);
    }

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        tablero.atacar(origen, objetivo);
    }

    @Override
    public void deshacerMovimiento(Tablero tablero, Posicion posicionActual) {
        tablero.deshacerMovimiento(posicionActual);
    }

    @Override
    public Fase avanzar(Tablero tablero) {
        return new FaseResolucion();
    }
}