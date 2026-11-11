package edu.fiuba.algo3.modelo.unidades.Mech.Estado;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeDeshacerMovimiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public class MovimientoDeshecho implements Estado {
    @Override
    public void mover(Mech mech, Direccion direccion, int distancia, Tablero tablero) {

    }

    @Override
    public void atacar(Mech mech, Arma arma, Posicion objetivo, Tablero tablero) {
        tablero.atacarDesdeUnidad(mech, arma, objetivo);
        mech.cambiarEstado(new Finalizado());
    }

    @Override
    public void deshacerMovimiento(Mech mech, Tablero tablero) {
        throw new NoSePuedeDeshacerMovimiento("No se puede deshacer mas de un movimiento");
    }
}
