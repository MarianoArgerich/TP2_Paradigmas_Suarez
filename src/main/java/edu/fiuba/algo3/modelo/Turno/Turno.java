package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.*;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Turno {

    private final Tablero tablero;
    private Fase faseActual;

    public Turno(Tablero tablero) {
        this(tablero, new FaseVek());
    }

    public Turno(Tablero tablero, FaseVek faseVek) {
        this.tablero = tablero;
        this.faseActual = faseVek;
    }

    public void avanzarFase() {
        this.faseActual = this.faseActual.avanzar(this.tablero);
    }

    public void mover(Posicion origen, Direccion direccion, int distancia) {
        this.faseActual.mover(this.tablero, origen, direccion, distancia);
    }

    public void atacar(Posicion origen, Posicion objetivo) {
        this.faseActual.atacar(this.tablero, origen, objetivo);
    }

    public void deshacerMovimiento(Posicion posicionActual) {
        this.faseActual.deshacerMovimiento(this.tablero, posicionActual);
    }
}
