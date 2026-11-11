package edu.fiuba.algo3.modelo.Efecto;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Disparo implements Efecto{

    private final int danio;

    public Disparo(int danio) {
        this.danio = danio;
    }


    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        unidad.recibirDanio(danio, casillaActual);
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Mapa mapa) {

    }
}
