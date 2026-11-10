package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Punio;

public class Combate extends Mech {

    public Combate() {
        super(3, 3, new Punio());
    }

    public Combate(Posicion posicion) {
        super(posicion, 3, 3, new Puno());
    }
}
