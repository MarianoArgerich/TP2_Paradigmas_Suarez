package edu.fiuba.algo3.modelo.Juego;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Turno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Juego {

    private static final int TURNOS_POR_DEFECTO = 5;

    private final Tablero tablero;
    private final Turno turno;
    private final int turnosTotales;
    private int turnoActual;
    private EstadoJuego estado;

    public Juego(Tablero tablero) {
        this(tablero, new Turno(tablero), TURNOS_POR_DEFECTO);
    }

    public Juego(Tablero tablero, Turno turno) {
        this(tablero, turno, TURNOS_POR_DEFECTO);
    }

    public Juego(Tablero tablero, Turno turno, int turnosTotales) {
        this.tablero = tablero;
        this.turno = turno;
        this.turnosTotales = turnosTotales;
        this.turnoActual = 1;
        this.estado = new EnCurso();
    }

    public void evaluarFinDePartida() {
        this.estado = this.estado.evaluarFin(this.tablero, this.turnoActual, this.turnosTotales);
    }

    public void mover(Posicion origen, Direccion direccion, int distancia) {
        this.estado.mover(this.turno, origen, direccion, distancia);
    }

    public void atacar(Posicion origen, Posicion objetivo) {
        this.estado.atacar(this.turno, origen, objetivo);
    }

    public void deshacerMovimiento(Posicion posicionActual) {
        this.estado.deshacerMovimiento(this.turno, posicionActual);
    }

    public void avanzarFase() {
        this.estado.avanzarFase(this.turno, this);
    }

    public void avanzarNumeroDeTurno() {
        this.turnoActual++;
    }

    public EstadoJuego getEstado() {
        return this.estado;
    }
}