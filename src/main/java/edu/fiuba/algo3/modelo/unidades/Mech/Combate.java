package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.representacion.TraductorEntidad;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Punio;

public class Combate extends Mech {

    public Combate() {
        super(3, 3, new Punio());
    }

    @Override
    public void aceptarTraductor(TraductorEntidad traductor) {
        traductor.traducirMechCombate(this.vidaActual, this.vidaMax, this.rangoDeMovimiento);
    }
}
