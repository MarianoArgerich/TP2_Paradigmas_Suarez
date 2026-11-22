package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaIA;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public interface AccionIA {

    void ejecutar(Vek vek, Tablero tablero);
    AccionIA aceptarCandidata(AccionIA candidata);
    AccionIA resolverCon(ReglaIA regla, Vek vek, Posicion origen, Tablero tablero);
}
