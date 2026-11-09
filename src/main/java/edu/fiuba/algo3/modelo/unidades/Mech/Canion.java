package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.ocupantes.Ocupante;

public class Canion extends Mech {

    public Canion() {
        super(3);
    }

    public void atacar(Direccion direccion, Tablero tablero) {
        Ocupante blanco = tablero.primerBlancoDesde(obtenerPosicionActual(), direccion);
        if (blanco != null) {
            blanco.recibirDanio(1);
        }
    }
}