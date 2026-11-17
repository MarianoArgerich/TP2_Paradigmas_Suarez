package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class AccionMover implements AccionIA {
    protected final Posicion origen;
    protected final List<Direccion> camino;


    public AccionMover(Posicion origen, List<Direccion> camino) {
        this.origen = origen;
        this.camino = camino;
    }

    @Override
    public boolean esValida() {
        return true;
    }

    @Override
    public void ejecutar(Vek vek, Tablero tablero) {
        Posicion actual = origen;
        for (Direccion direccion : camino) {
            tablero.mover(actual, direccion, 1);
            actual = actual.siguienteEn(direccion);
        }
    }

    protected Posicion obtenerDestino() {
        Posicion actual = origen;
        for (Direccion direccion : camino) {
            actual = actual.siguienteEn(direccion);
        }
        return actual;
    }
}
