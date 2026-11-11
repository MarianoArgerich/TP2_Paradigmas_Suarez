package edu.fiuba.algo3.modelo.casillas.Terreno;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public interface Terreno {
    boolean esTransitable();
    void reaccionarAlIngresoDe(Unidad unidad);


}
