package edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public class ReglaAtacarMech extends ReglaAtacar {

    @Override
    public void visitar(Mech mech) {
        this.confirmarObjetivo();
    }

}
