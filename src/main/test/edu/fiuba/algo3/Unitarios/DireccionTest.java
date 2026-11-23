package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.direccion.Abajo;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.direccion.Izquierda;
import edu.fiuba.algo3.modelo.direccion.SinDireccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DireccionTest {

    private final Posicion origen = new Posicion(2, 2);

    @Test
    public void testArribaSuma1ParaY() {
        assertEquals(new Posicion(2, 3), new Arriba().calcularSiguientePosicion(origen));
    }

    @Test
    public void testAbajoResta1ParaY() {
        assertEquals(new Posicion(2, 1), new Abajo().calcularSiguientePosicion(origen));
    }

    @Test
    public void testDerechaSuma1ParaX() {
        assertEquals(new Posicion(3, 2), new Derecha().calcularSiguientePosicion(origen));
    }

    @Test
    public void testIzquierdaResta1ParaX() {
        assertEquals(new Posicion(1, 2), new Izquierda().calcularSiguientePosicion(origen));
    }

    @Test
    public void testSinDireccionMantieneLaMismaPosicion() {
        assertEquals(origen, new SinDireccion().calcularSiguientePosicion(origen));
    }
}
