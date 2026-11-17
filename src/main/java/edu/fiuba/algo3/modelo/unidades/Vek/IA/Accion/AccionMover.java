package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class AccionMover implements AccionIA {

    protected final Desplazamiento desplazamiento;


    public AccionMover(Desplazamiento desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    @Override
    public boolean esValida() {
        return true;
    }

    @Override
    public void ejecutar(Vek vek, Tablero tablero) {
        desplazamiento.aplicarEn(tablero);
    }
}
