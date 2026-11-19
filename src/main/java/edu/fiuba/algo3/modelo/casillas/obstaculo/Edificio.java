package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;

public class Edificio extends Destruible {

    public Edificio() {
        super(1);
    }

    @Override
    public boolean esEdificio() {
        return true;
    }

    @Override
    public void recibirDanio(int danio, Casilla casillaActual, Tablero tablero) {
        super.recibirDanio(danio, casillaActual, tablero);
        tablero.reducirRedElectrica();
    }

}
