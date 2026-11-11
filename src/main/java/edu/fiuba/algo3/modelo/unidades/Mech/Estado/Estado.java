package edu.fiuba.algo3.modelo.unidades.Mech.Estado;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public interface Estado {
    void mover(Mech mech, Direccion direccion, int distancia, Tablero tablero);
    void atacar(Mech mech, Arma arma, Posicion objetivo, Tablero tablero);
    void deshacerMovimiento(Mech mech, Tablero tablero);
}
