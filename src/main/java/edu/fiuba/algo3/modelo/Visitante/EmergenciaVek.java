package edu.fiuba.algo3.modelo.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class EmergenciaVek implements Visitante {

    private final Vek vekAEmerger;

    public EmergenciaVek(Vek vek) {
        this.vekAEmerger = vek;
    }

    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        unidad.recibirDanio(1, casillaActual);
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        casillaActual.colocarUnidad(vekAEmerger);
    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual, Mapa mapa) {
    }

    @Override
    public void visitar(Montania montania, Terreno terreno, Casilla casillaActual, Mapa mapa) {
    }
}
