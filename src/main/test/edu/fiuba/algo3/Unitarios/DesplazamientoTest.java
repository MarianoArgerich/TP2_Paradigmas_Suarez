package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DesplazamientoTest {

    @Test
    public void testDestinoCalculaLaPosicionFinalSegunDireccionYDistancia() {
        Desplazamiento desplazamiento = new Desplazamiento(new Posicion(1, 1), new Derecha(), 3);

        assertEquals(new Posicion(4, 1), desplazamiento.destino());
    }

    @Test
    public void testSinMoverMantieneLaPosicionDeOrigenComoDestino() {
        Posicion origen = new Posicion(2, 3);
        Desplazamiento desplazamiento = Desplazamiento.sinMover(origen);

        assertEquals(origen, desplazamiento.destino());
    }

    @Test
    public void testEsMenorQueComparaPorPosicionDeDestino() {
        Desplazamiento menor = new Desplazamiento(new Posicion(1, 1), new Derecha(), 2);
        Desplazamiento mayor = new Desplazamiento(new Posicion(1, 1), new Arriba(), 2);

        assertTrue(menor.esMenorQue(mayor));
    }

    @Test
    public void testAplicarEnMueveLaUnidadEnElTableroCuandoLaDistanciaEsMayorACero() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion origen = new Posicion(2, 2);
        Mech mech = combate();
        tablero.colocarUnidad(origen, mech);

        Desplazamiento desplazamiento = new Desplazamiento(origen, new Arriba(), 2);
        desplazamiento.aplicarEn(tablero);

        assertTrue(tablero.unidadEstaEn(mech, new Posicion(2, 4)));
    }

    @Test
    public void aplicarEnSinMoverDejaLaUnidadEnSuLugar() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion origen = new Posicion(2, 2);
        Mech mech = combate();
        tablero.colocarUnidad(origen, mech);

        Desplazamiento.sinMover(origen).aplicarEn(tablero);

        assertTrue(tablero.unidadEstaEn(mech, origen));
    }
}
