package edu.fiuba.algo3.modelo.unidades.Mech.Estado;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public interface Estado {
    void mover(Mech mech, Casilla origen, Direccion direccion, int distancia, Mapa mapa);
    void atacar(Mech mech, Arma arma, Posicion origen, Posicion objetivo, Tablero tablero);
    void deshacerMovimiento(Mech mech, Casilla casillaActual, Mapa mapa);
}
