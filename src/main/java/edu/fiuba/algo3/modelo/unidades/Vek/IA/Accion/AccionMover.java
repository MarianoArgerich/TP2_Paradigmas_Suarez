package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaIA;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class AccionMover implements AccionIA {

    protected final Desplazamiento desplazamiento;


    public AccionMover(Desplazamiento desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    @Override
    public void ejecutar(Vek vek, Tablero tablero) {
        desplazamiento.aplicarEn(tablero);
    }

    @Override
    public AccionIA aceptarCandidata(AccionIA candidata) {
        return this;
    }

    @Override
    public AccionIA resolverCon(ReglaIA regla, Vek vek, Posicion origen, Tablero tablero) {
        return this;
    }
}
