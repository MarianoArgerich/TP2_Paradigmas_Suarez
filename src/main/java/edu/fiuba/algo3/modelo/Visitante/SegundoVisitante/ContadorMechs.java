package edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;

import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public class ContadorMechs extends SegundoVisitante{

    private int cantidad = 0;

    @Override
    public void visitar(Mech mech) {
        this.cantidad++;
    }

    public int cantidad() {
        return this.cantidad;
    }
}
