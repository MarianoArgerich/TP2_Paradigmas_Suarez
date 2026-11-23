package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaIA;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class AccionNula implements AccionIA{

    @Override
    public void ejecutar(Vek vek, Tablero tablero) {}

    @Override
    public AccionIA aceptarCandidata(AccionIA candidata) {
        return candidata;
    }
    @Override
    public AccionIA resolverCon(ReglaIA regla, Vek vek, Posicion origen, Tablero tablero) {
        return regla.evaluar(vek, origen, tablero);
    }
}
