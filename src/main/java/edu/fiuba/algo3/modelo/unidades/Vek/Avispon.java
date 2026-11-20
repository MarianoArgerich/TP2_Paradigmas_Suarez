package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.ExploradorMovimientoVek;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;

public class Avispon extends Vek {

    public Avispon() {
        super(2, 4, 1);
    }

    @Override
    public void contaminarPorAcido() {
    }

    @Override
    public void caerAlAguaEn(Casilla casilla) {
    }

    @Override
    public void alExplorarAgua(ExploradorMovimientoVek explorador, Casilla casillaActual, Tablero tablero) {
        explorador.aceptarPasoYContinuar(casillaActual, tablero);
    }

    @Override
    public void alExplorarAcido(ExploradorMovimientoVek explorador, Casilla casillaActual, Tablero tablero) {
        explorador.aceptarPasoYContinuar(casillaActual, tablero);
    }

}
