package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.ObjetivoDesalineadoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PosicionTest {

    @Test
    public void testDesplazarX2Y2en2DerechaY2AbajoDevuelveX4Y1() {
        Posicion origen = new Posicion(2, 3);

        assertEquals(new Posicion(4, 1), origen.desplazar(2, -2));
    }

    @Test
    public void testPosicionDevuelveLaSiguientePosicionEnUnaDireccion() {
        Posicion origen = new Posicion(2, 3);

        assertEquals(new Posicion(3, 3), origen.siguienteEn(new Derecha()));
    }

    @Test
    public void testPosicionDevuelveLaDistanciaAOtraPosicion() {
        Posicion a = new Posicion(1, 2);
        Posicion b = new Posicion(4, 6);

        assertEquals(7, a.distanciaA(b));
    }

    @Test
    public void testPosicionEnValidarAdyacenciaConPosicionAdyacenteAOtraNoLanzaError() {
        Posicion origen = new Posicion(3, 3);

        assertDoesNotThrow(() -> origen.validarAdyacenciaCon(new Posicion(3, 4)));
    }

    @Test
    public void testPosicionEnValidarAdyacenciaConPosicionNoAdyacenteAOtraLanzaError() {
        Posicion origen = new Posicion(3, 3);

        assertThrows(DistanciaInvalidaException.class, () -> origen.validarAdyacenciaCon(new Posicion(3, 3)));
    }

    @Test
    public void testValidarAlineacionConPosicionAceptaMismaFila() {
        Posicion origen = new Posicion(2, 2);

        assertDoesNotThrow(() -> origen.validarAlineacionCon(new Posicion(5, 2)));
    }

    @Test
    public void testValidarAlineacionConPosicionAceptaMismaColumna() {
        Posicion origen = new Posicion(2, 2);

        assertDoesNotThrow(() -> origen.validarAlineacionCon(new Posicion(2, 6)));
    }

    @Test
    public void testValidarAlineacionConPosicionRechazaDiagonal() {
        Posicion origen = new Posicion(2, 2);

        assertThrows(ObjetivoDesalineadoException.class, () -> origen.validarAlineacionCon(new Posicion(3, 4)));
    }

    @Test
    public void testPosicionValidarFueraDeAreaDeRechazaElArea3x3() {
        Posicion origen = new Posicion(3, 3);

        assertThrows(DistanciaInvalidaException.class, () -> origen.validarFueraDeAreaDe(new Posicion(3, 3)));
    }

    @Test
    public void testPosicionValidarFueraDeAreaDeAceptaFueraDeElla() {
        Posicion origen = new Posicion(3, 3);

        assertDoesNotThrow(() -> origen.validarFueraDeAreaDe(new Posicion(3, 5)));
    }

    @Test
    public void testCalcularDireccionHaciaDevuelveLaDireccionCorrectaParaPosicionArriba() {
        Posicion origen = new Posicion(3, 3);

        Direccion arriba = origen.calcularDireccionHacia(new Posicion(3, 6));

        assertEquals(new Posicion(3, 4), arriba.calcularSiguientePosicion(origen));
    }

    @Test
    public void testCalcularDireccionHaciaDevuelveLaDireccionCorrectaParaPosicionAbajo() {
        Posicion origen = new Posicion(3, 3);

        Direccion abajo = origen.calcularDireccionHacia(new Posicion(3, 0));

        assertEquals(new Posicion(3, 2), abajo.calcularSiguientePosicion(origen));
    }

    @Test
    public void testCalcularDireccionHaciaDevuelveLaDireccionCorrectaParaPosicionAIzquierda() {
        Posicion origen = new Posicion(3, 3);

        Direccion izquierda = origen.calcularDireccionHacia(new Posicion(1, 3));

        assertEquals(new Posicion(2, 3), izquierda.calcularSiguientePosicion(origen));
    }

    @Test
    public void testCalcularDireccionHaciaDevuelveLaDireccionCorrectaParaPosicionADerecha() {
        Posicion origen = new Posicion(3, 3);

        Direccion derecha = origen.calcularDireccionHacia(new Posicion(6, 3));

        assertEquals(new Posicion(4, 3), derecha.calcularSiguientePosicion(origen));
    }

    @Test
    public void testCalcularDireccionHaciaLanzaExcepcionSiNoEstaAlineadoConLaPosicion() {
        Posicion origen = new Posicion(2, 2);

        assertThrows(ObjetivoDesalineadoException.class,
                () -> origen.calcularDireccionHacia(new Posicion(4, 5)));
    }

    @Test
    public void testPosicionEsMenorQueOtraPosicionComparaPrimeroPorYDespuesPorX() {
        assertTrue(new Posicion(5, 1).esMenorQue(new Posicion(5, 2)));
    }

    @Test
    public void testEqualsComparaPorCoordenadasXeYSonIguales() {
        Posicion a = new Posicion(2, 3);
        Posicion b = new Posicion(2, 3);

        assertEquals(a, b);
    }

    @Test
    public void testEqualsComparaPorCoordenadasXeYNoSonIguales() {
        Posicion a = new Posicion(2, 3);
        Posicion b = new Posicion(3, 2);

        assertNotEquals(a, b);
    }
}
