package edu.fiuba.algo3.modelo.casillas.Terreno;


import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.ExploradorMovimientoVek;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class Agua extends Terreno {

    @Override
    public void reaccionarAlIngresoDe(Unidad unidad, Casilla casilla) {
        unidad.caerAlAguaEn(casilla);
    }

    @Override
    public void verificarAtaqueDe(Unidad unidad) {
        unidad.verificarAtaqueEnAgua();
    }

    @Override
    public void evaluarMovimientoVek(Vek vek, ExploradorMovimientoVek exploradorMovimientoVek, Casilla casillaActual, Tablero tablero) {
        vek.alExplorarAgua(exploradorMovimientoVek, casillaActual, tablero);
    }
}
