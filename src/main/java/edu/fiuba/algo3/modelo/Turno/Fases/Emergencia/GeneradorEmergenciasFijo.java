package edu.fiuba.algo3.modelo.Turno.Fases.Emergencia;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class GeneradorEmergenciasFijo implements GeneradorEmergencias {
    private final Posicion posicion;
    private final Vek vek;

    public GeneradorEmergenciasFijo(Posicion posicion, Vek vek) {
        this.posicion = posicion;
        this.vek = vek;
    }

    @Override
    public void generarEn(Tablero tablero) {
        tablero.anunciarEmergencia(this.posicion, this.vek);
    }
}
