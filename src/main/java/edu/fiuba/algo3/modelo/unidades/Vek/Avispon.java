package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

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
    public boolean puedeTerminarEn(Terreno terreno) {
        return true;
    }

}
