package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Efecto.Efecto;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class SinOcupante extends Ocupante{

    @Override
    public boolean puedeIngresar() {
        return true;
    }

    @Override
    public boolean esUnidad(Unidad unidad) {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return false;
    }

    @Override
    public void aceptar(Efecto efecto, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        efecto.visitar(this, terreno, casillaActual, mapa);
    }
}
