package edu.fiuba.algo3.entrega1.mechTest;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.transitable.Suelo;
import edu.fiuba.algo3.modelo.direccion.Abajo;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.direccion.Izquierda;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Mech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArtilleriaTest {

    private Tablero tablero;
    private List<Casilla> mapa = new ArrayList<>();
    private Mech artillero;

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa.add( new Suelo(new Posicion(i, j)));
            }
        }

        tablero = new Tablero(mapa);
        artillero = new Artilleria();
    }


    @Test
    public void testArtilleriaAtacaACasillaObjetivoPorEncimaDeUnVekSinQueElVekRecibaDanio() {
        Posicion posicionObjetivo = new Posicion(0, 4);
        Posicion posicionDelVek = new Posicion(0, 6);
        Posicion posicionDeArtillero = new Posicion(0, 7);

        Vek vek1 = new Luciernaga();

        tablero.colocarUnidad(posicionDeArtillero, artillero);
        tablero.colocarUnidad(posicionDelVek, vek1);

        artillero.atacar(posicionObjetivo, tablero);

        assertTrue(vek1.suVidaEsIgualA(3),
                "El Vek por encima del cual voló el disparo no debe recibir daño");
    }

    @Test
    public void testArtilleriaIntentaAtacarUnaCasillaAdyacenteYNoPuede() {

        Posicion posArtilleria = new Posicion(4, 4);
        Posicion posObjetivoAdyacente = new Posicion(4, 5);

        tablero.colocarUnidad(posArtilleria, artillero);

        assertThrows(AtaqueInvalidoException.class, () -> {
            artillero.atacar(posObjetivoAdyacente, tablero);
        }, "El Mech de Artillería no debe poder atacar casillas adyacentes");

    }

    @Test
    public void testArtilleriaAtacaALaCasillaObjetivoYLaEmpujaHaciaAfueraALasUnidadesDeLas4CasillasAdyacentes() {

        Posicion posArtilleria = new Posicion(0, 4);
        Posicion posObjetivo = new Posicion(4, 4);


        Posicion posNorte = new Posicion(3, 4);
        Posicion posSur   = new Posicion(5, 4);
        Posicion posOeste = new Posicion(4, 3);
        Posicion posEste  = new Posicion(4, 5);

        Vek vekNorte = new Luciernaga();
        Vek vekSur   = new Luciernaga();
        Vek vekOeste = new Luciernaga();
        Vek vekEste  = new Luciernaga();

        tablero.colocarUnidad(posArtilleria, artillero);
        tablero.colocarUnidad(posNorte, vekNorte);
        tablero.colocarUnidad(posSur, vekSur);
        tablero.colocarUnidad(posOeste, vekOeste);
        tablero.colocarUnidad(posEste, vekEste);


        artillero.atacar(posObjetivo, tablero);


        assertTrue(vekNorte.estaEn(new Posicion(2, 4), tablero), "La unidad al Norte debe ser empujada a (2, 4)");
        assertTrue(vekSur.estaEn(new Posicion(6, 4), tablero),   "La unidad al Sur debe ser empujada a (6, 4)");
        assertTrue(vekOeste.estaEn(new Posicion(4, 2), tablero), "La unidad al Oeste debe ser empujada a (4, 2)");
        assertTrue(vekEste.estaEn(new Posicion(4, 6), tablero),  "La unidad al Este debe ser empujada a (4, 6)");
    }

    @Test
    public void testArtilleriaAtacaACasillaObjetivoYEmpujaAUnidadAdyacenteHaciaLaDerecha() {
        Derecha derecha = new Derecha();
        Vek vekObjetivo = new Luciernaga();
        Vek vekDerecha = new Luciernaga();

        Posicion posicionObjetivo = new Posicion(2, 2);
        Posicion posicionInicialDerecha = new Posicion(3, 2); // Adyacente a la derecha de (2,2)

        tablero.colocarUnidad(posicionObjetivo, vekObjetivo);
        tablero.colocarUnidad(posicionInicialDerecha, vekDerecha);
        tablero.colocarUnidad(new Posicion(2, 5), artillero);

        // Act
        artillero.atacar(posicionObjetivo, tablero);

        // Assert: Daña al objetivo y empuja al Vek de (3,2) hacia (4,2)
        Posicion posicionFinalEsperada = derecha.calcularSiguientePosicion(posicionInicialDerecha); // (4, 2)
        assertTrue(vekDerecha.estaEn(posicionFinalEsperada, tablero));
    }

    @Test
    public void testArtilleriaAtacaACasillaObjetivoYEmpujaAUnidadAdyacenteHaciaLaIzquierda() {
        Izquierda izquierda = new Izquierda();
        Vek vekObjetivo = new Luciernaga();
        Vek vekIzquierda = new Luciernaga();

        Posicion posicionObjetivo = new Posicion(2, 2);
        Posicion posicionInicialIzquierda = new Posicion(1, 2); // Adyacente a la izquierda de (2,2)

        tablero.colocarUnidad(posicionObjetivo, vekObjetivo);
        tablero.colocarUnidad(posicionInicialIzquierda, vekIzquierda);
        tablero.colocarUnidad(new Posicion(2, 5), artillero);

        // Act
        artillero.atacar(posicionObjetivo, tablero);

        // Assert: Daña al objetivo y empuja al Vek de (1,2) hacia (0,2)
        Posicion posicionFinalEsperada = izquierda.calcularSiguientePosicion(posicionInicialIzquierda); // (0, 2)
        assertTrue(vekIzquierda.estaEn(posicionFinalEsperada, tablero));
    }

    @Test
    public void testArtilleriaAtacaACasillaObjetivoYEmpujaAUnidadAdyacenteHaciaArriba() {
        Arriba arriba = new Arriba();
        Vek vekObjetivo = new Luciernaga();
        Vek vekArriba = new Luciernaga();

        Posicion posicionObjetivo = new Posicion(2, 2);
        Posicion posicionInicialArriba = new Posicion(2, 1); // Adyacente arriba de (2,2)

        tablero.colocarUnidad(posicionObjetivo, vekObjetivo);
        tablero.colocarUnidad(posicionInicialArriba, vekArriba);
        tablero.colocarUnidad(new Posicion(2, 5), artillero);

        // Act
        artillero.atacar(posicionObjetivo, tablero);

        // Assert: Daña al objetivo y empuja al Vek de (2,1) hacia (2,0)
        Posicion posicionFinalEsperada = arriba.calcularSiguientePosicion(posicionInicialArriba); // (2, 0)
        assertTrue(vekArriba.estaEn(posicionFinalEsperada, tablero));
    }

    @Test
    public void testArtilleriaAtacaACasillaObjetivoYEmpujaAUnidadAdyacenteHaciaAbajo() {
        Abajo abajo = new Abajo();
        Vek vekObjetivo = new Luciernaga();
        Vek vekAbajo = new Luciernaga();

        Posicion posicionObjetivo = new Posicion(2, 2);
        Posicion posicionInicialAbajo = new Posicion(2, 3); // Adyacente abajo de (2,2)

        tablero.colocarUnidad(posicionObjetivo, vekObjetivo);
        tablero.colocarUnidad(posicionInicialAbajo, vekAbajo);
        tablero.colocarUnidad(new Posicion(2, 5), artillero);

        // Act
        artillero.atacar(posicionObjetivo, tablero);

        // Assert: Daña al objetivo y empuja al Vek de (2,3) hacia (2,4)
        Posicion posicionFinalEsperada = abajo.calcularSiguientePosicion(posicionInicialAbajo); // (2, 4)
        assertTrue(vekAbajo.estaEn(posicionFinalEsperada, tablero));

    }













}
