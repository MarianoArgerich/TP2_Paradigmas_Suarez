package edu.fiuba.algo3.modelo.unidades.Mech.Estado;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeDeshacerMovimiento;
import edu.fiuba.algo3.modelo.excepciones.YaAtacoException;
import edu.fiuba.algo3.modelo.excepciones.YaSeMovioException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;

public class Finalizado implements Estado {
    @Override
    public void mover(Mech mech, Direccion direccion, int distancia, Tablero tablero) {
        throw new YaSeMovioException("El mech no debería poder moverse dos veces en un turno");
    }

    @Override
    public void atacar(Mech mech, Arma arma, Posicion objetivo, Tablero tablero) {
        throw new YaAtacoException("El mech no debería poder atacar dos veces en un turno");
    }

    @Override
    public void deshacerMovimiento(Mech mech, Tablero tablero) {
        throw new NoSePuedeDeshacerMovimiento("No se puede deshacer un movimiento despues de atacar");
    }
}
