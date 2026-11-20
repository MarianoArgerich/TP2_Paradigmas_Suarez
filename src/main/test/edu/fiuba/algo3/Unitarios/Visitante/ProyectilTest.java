package edu.fiuba.algo3.Unitarios.Visitante;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Disparo;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Proyectil;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProyectilTest {

    @Test
    public void proyectilAtraviesaCasillaVaciaEImpactaEnLaSiguiente() {
        Casilla vacia = new Casilla(new Posicion(1, 0));
        Casilla objetivo = new Casilla(new Posicion(2, 0));
        Tablero tablero = new Tablero(List.of(vacia, objetivo));

        Mech mech = new Combate();
        tablero.colocarUnidad(new Posicion(2, 0), mech);

        tablero.aplicarEfecto(new Posicion(1, 0), new Proyectil(new Derecha(), new Disparo(1)));

        assertTrue(mech.suVidaEsIgualA(2));
    }

    @Test
    public void proyectilSeDetieneEnCasillaConUnidadYNoDaniaALaSiguiente() {
        Casilla primera = new Casilla(new Posicion(1, 0));
        Casilla segunda = new Casilla(new Posicion(2, 0));
        Tablero tablero = new Tablero(List.of(primera, segunda));

        Mech mechAdelante = new Combate();
        Mech mechAtras = new Combate();
        tablero.colocarUnidad(new Posicion(1, 0), mechAdelante);
        tablero.colocarUnidad(new Posicion(2, 0), mechAtras);

        tablero.aplicarEfecto(new Posicion(1, 0), new Proyectil(new Derecha(), new Disparo(1)));

        assertTrue(mechAdelante.suVidaEsIgualA(2));
        assertTrue(mechAtras.suVidaEsIgualA(3));
    }
}
