package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.representacion.TraductorEntidad;

public class Luciernaga extends Vek {
    public Luciernaga() {
        super(3, 2);
    }

    @Override
    public void deshacerMovimientoDesde(Casilla casilla, Mapa mapa) {

    }

    @Override
    public void aceptarTraductor(TraductorEntidad traductor) {
        traductor.traducirLuciernaga(vidaActual, vidaMax, rangoDeMovimiento);
    }
}
