package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Puno;

public class Combate extends Mech {

    public Combate() {
        super(3, 3, new Puno());
    }
}
