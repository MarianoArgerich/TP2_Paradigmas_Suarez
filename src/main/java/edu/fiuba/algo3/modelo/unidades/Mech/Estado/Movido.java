package edu.fiuba.algo3.modelo.unidades.Mech.Estado;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.YaSeMovioException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public class Movido implements Estado {
    private final Posicion posicionAnterior;

    public Movido(Posicion posicion) {
        this.posicionAnterior = posicion;
    }

    @Override
    public void mover(Mech mech, Direccion direccion, int distancia, Tablero tablero) {
        throw new YaSeMovioException("El mech no debería poder moverse dos veces en un turno");
    }

    @Override
    public void atacar(Mech mech, Arma arma, Posicion objetivo, Tablero tablero) {
        tablero.atacarDesdeUnidad(mech, arma, objetivo);
        mech.cambiarEstado(new Finalizado());
    }

    @Override
    public void deshacerMovimiento(Mech mech, Tablero tablero) {
        tablero.revertirMovimiento(mech, this.posicionAnterior);
        mech.cambiarEstado(new MovimientoDeshecho());
    }
}
