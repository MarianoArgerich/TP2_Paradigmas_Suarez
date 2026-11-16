package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class AccionNula implements AccionIA{

    @Override
    public boolean esValida() {
        return false;
    }

    @Override
    public void ejecutar(Vek vek, Tablero tablero) {

    }
}
