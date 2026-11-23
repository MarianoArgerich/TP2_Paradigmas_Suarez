package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.ObjetivoDesalineadoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arco;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.DisparoCanion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Punio;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArmasTest {

    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = TableroTestFactory.crearVacio();
    }

    @Test
    public void testPunioCausa2DeDanioYEmpujaUnaCasillaAdyacente() {
        Punio punio = new Punio();
        Luciernaga vek = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 3), vek);

        punio.ejecutarAtaque(new Posicion(2, 2), new Posicion(2, 3), tablero);

        assertTrue(vek.suVidaEsIgualA(1));
    }

    @Test
    public void testPunioAlCausarDanioEmpujaUnaCasillaAdyacente() {
        Punio punio = new Punio();
        Luciernaga vek = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 3), vek);

        punio.ejecutarAtaque(new Posicion(2, 2), new Posicion(2, 3), tablero);

        assertTrue(tablero.unidadEstaEn(vek, new Posicion(2, 4)));
    }

    @Test
    public void testPunioRechazaObjetivosNoAdyacentes() {
        Punio punio = new Punio();

        assertThrows(DistanciaInvalidaException.class,
                () -> punio.ejecutarAtaque(new Posicion(2, 2), new Posicion(2, 4), tablero));
    }

    @Test
    public void testPunioRechazaObjetivosDiagonales() {
        Punio punio = new Punio();

        assertThrows(ObjetivoDesalineadoException.class,
                () -> punio.ejecutarAtaque(new Posicion(2, 2), new Posicion(3, 3), tablero));
    }

    @Test
    public void testDisparoCanionImpactaSoloAlPrimerObjetivoEnLineaRecta() {
        DisparoCanion canion = new DisparoCanion();
        Luciernaga primero = new Luciernaga();
        Luciernaga segundo = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 4), primero);
        tablero.colocarUnidad(new Posicion(2, 6), segundo);

        canion.ejecutarAtaque(new Posicion(2, 1), new Posicion(2, 4), tablero);

        assertTrue(primero.suVidaEsIgualA(2));
    }

    @Test
    public void testDisparoCanionLuegoDeImpactarAlObjetivoLoEmpuja() {
        DisparoCanion canion = new DisparoCanion();
        Luciernaga primero = new Luciernaga();
        Luciernaga segundo = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 4), primero);
        tablero.colocarUnidad(new Posicion(2, 6), segundo);

        canion.ejecutarAtaque(new Posicion(2, 1), new Posicion(2, 4), tablero);

        assertTrue(tablero.unidadEstaEn(primero, new Posicion(2, 5)));
    }

    @Test
    public void testDisparoCanionRechazaObjetivosDesalineados() {
        DisparoCanion canion = new DisparoCanion();

        assertThrows(ObjetivoDesalineadoException.class,
                () -> canion.ejecutarAtaque(new Posicion(2, 2), new Posicion(4, 3), tablero));
    }

    @Test
    public void testArcoIgnoraObstaculoIntermedioDaniaAlObjetivo() {
        tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(2, 3), new Montania());
        Arco arco = new Arco();
        Luciernaga objetivo = new Luciernaga();
        Luciernaga adyacente = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 5), objetivo);
        tablero.colocarUnidad(new Posicion(3, 5), adyacente);

        arco.ejecutarAtaque(new Posicion(2, 1), new Posicion(2, 5), tablero);

        assertTrue(objetivo.suVidaEsIgualA(2));
    }

    @Test
    public void testArcoAlDaniarObjetivoEmpujaAdyacentes() {
        tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(2, 3), new Montania());
        Arco arco = new Arco();
        Luciernaga objetivo = new Luciernaga();
        Luciernaga adyacente = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 5), objetivo);
        tablero.colocarUnidad(new Posicion(3, 5), adyacente);

        arco.ejecutarAtaque(new Posicion(2, 1), new Posicion(2, 5), tablero);

        assertTrue(tablero.unidadEstaEn(adyacente, new Posicion(4, 5)));
    }

    @Test
    public void testArcoNoPuedeAtacarAObjetivosEnElArea3x3() {
        Arco arco = new Arco();

        assertThrows(DistanciaInvalidaException.class,
                () -> arco.ejecutarAtaque(new Posicion(3, 3), new Posicion(3, 4), tablero));
    }

    @Test
    public void testArcoNoPuedeAtacarAObjetivosEnDiagonal() {
        Arco arco = new Arco();

        assertThrows(ObjetivoDesalineadoException.class,
                () -> arco.ejecutarAtaque(new Posicion(3, 3), new Posicion(5, 5), tablero));
    }
}