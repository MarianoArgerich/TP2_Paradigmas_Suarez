package edu.fiuba.algo3.modelo.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import edu.fiuba.algo3.modelo.unidades.capacidades.Contaminable;

public class ContaminarOcupante implements SegundoVisitante {

    private void contaminar(Contaminable contaminable) {
        contaminable.contaminar();
    }

    @Override
    public void visitar(Mech mech) {
        this.contaminar(mech);
    }

    @Override
    public void visitar(Vek vek) {
        this.contaminar(vek);
    }

    @Override
    public void visitar(SinOcupante sinOcupante) {
    }

    @Override
    public void visitar(Destruible destruible) {
    }

    @Override
    public void visitar(Montania montania) {
    }
}
