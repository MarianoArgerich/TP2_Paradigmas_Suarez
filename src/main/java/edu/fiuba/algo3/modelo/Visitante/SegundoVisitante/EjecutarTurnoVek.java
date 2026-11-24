package edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class EjecutarTurnoVek extends SegundoVisitante{

    private final Posicion posicionActual;
    private final Tablero tablero;
    private final List<Vek> veksEfectuados;

    public EjecutarTurnoVek(Posicion posicionActual, Tablero tablero, List<Vek> veksEfectuados) {
        this.posicionActual = posicionActual;
        this.tablero = tablero;
        this.veksEfectuados = veksEfectuados;
    }

    @Override
    public void visitar(Vek vek) {
        if (!this.veksEfectuados.contains(vek)) {
            this.veksEfectuados.add(vek);
            vek.ejecutarTurnoIA(this.posicionActual, this.tablero);
        }
    }
}
