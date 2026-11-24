package edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;

import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public class ReiniciarTurnoMech extends SegundoVisitante {
    @Override
    public void visitar(Mech mech) {
        mech.reiniciarTurno();
    }
}
