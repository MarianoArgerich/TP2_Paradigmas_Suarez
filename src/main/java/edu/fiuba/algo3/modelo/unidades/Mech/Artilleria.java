package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.representacion.TraductorEntidad;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arco;

public class Artilleria extends Mech{

    public Artilleria(){
        super(2, 3,new Arco() );
    }

    @Override
    public void aceptarTraductor(TraductorEntidad traductor) {
        traductor.traducirMechArtilleria(this.vidaActual, this.vidaMax, this.rangoDeMovimiento);
    }
}

