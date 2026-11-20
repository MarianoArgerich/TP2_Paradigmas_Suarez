package edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public abstract class SegundoVisitante {

    public void visitar(Mech mech) {}

    public void visitar(Vek vek) {}

    public void visitar(SinOcupante sinOcupante) {}

    public void visitar(Destruible destruible) {}

    public void visitar(Montania montania) {}
}
