package edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public interface SegundoVisitante {

    void visitar(Mech mech);

    void visitar(Vek vek);

    void visitar(SinOcupante sinOcupante);

    void visitar(Destruible destruible);

    void visitar(Montania montania);
}
