package edu.fiuba.algo3.modelo.Visitante.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Golpe extends Visitante {

    private final int danio;
    private final Direccion direccion;

    public Golpe(int danio, Direccion direccion){
        this.danio = danio;
        this.direccion = direccion;
    }

    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        unidad.recibirDanio(danio, casillaActual);

        casillaActual.aceptar(new Empuje(direccion), tablero);
    }

    @Override
        public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Tablero tablero) {

    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        destruible.recibirDanio(danio, casillaActual, tablero);
    }

    @Override
    public void visitar(Montania montania, Terreno terreno, Casilla casillaActual, Tablero tablero) {
    }
}
