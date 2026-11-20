package edu.fiuba.algo3.modelo.unidades.Mech.Estado;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeDeshacerMovimiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public class InicioTurno implements Estado{

    @Override
    public void mover(Mech mech, Casilla origen, Direccion direccion, int distancia, Tablero tablero) {
        mech.ejecutarDesplazamiento(origen, direccion, distancia, tablero);
    }

    @Override
    public void atacar(Mech mech, Arma arma, Posicion origen, Posicion objetivo, Tablero tablero) {
        arma.ejecutarAtaque(origen, objetivo, tablero);
        mech.cambiarEstado(new Finalizado());
    }

    @Override
    public void deshacerMovimiento(Mech mech, Casilla casillaActual, Tablero tablero) {
        throw new NoSePuedeDeshacerMovimiento("No se puede deshacer movimiento si no se movió");
    }
}
