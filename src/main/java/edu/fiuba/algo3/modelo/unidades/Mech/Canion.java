package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.DisparoCanion;

public class Canion extends Mech {

    public Canion() {
        super(3, 3, new DisparoCanion());
    }
}