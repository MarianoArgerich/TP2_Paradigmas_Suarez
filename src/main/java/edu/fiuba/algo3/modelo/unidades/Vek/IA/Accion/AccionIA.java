package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public interface AccionIA {
    boolean esValida();
    //TODO:viola tell don't ask
    void ejecutar(Vek vek, Tablero tablero);
}
