package edu.fiuba.algo3.entrega1.mechTest;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.casillas.transitable.Casilla;
import edu.fiuba.algo3.modelo.casillas.transitable.Suelo;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanionTest {

    private Tablero tablero;
    private Casilla[][] mapa;
    private Mech canion;

    @BeforeEach
    public void setUp() {
        mapa = new Casilla[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa[i][j] = new Suelo(new Posicion(i, j));
            }
        }
        tablero = new Tablero(mapa);
        canion = new Canion();
    }

    @Test
    public void testCanionDaniaALaPrimeraUnidadEnLineaRecta() {
        tablero.colocarUnidad(new Posicion(2, 2), canion);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2, 5);
        tablero.colocarUnidad(ubicacionVek, vek);

        canion.atacar(ubicacionVek, tablero);

        assertEquals(2, vek.obtenerVidaActual());
    }

    @Test
    public void testCanionNoImpactaALasUnidadesDeAtras() {
        tablero.colocarUnidad(new Posicion(2, 2), canion);
        Vek delante = new Luciernaga();
        Vek atras = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 4), delante);
        tablero.colocarUnidad(new Posicion(2, 7), atras);

        canion.atacar(new Posicion(2, 7), tablero);

        assertEquals(2, delante.obtenerVidaActual());
        assertEquals(3, atras.obtenerVidaActual());
    }

    @Test
    public void testUnaMontaniaFrenaElDisparoDelCanion() {
        mapa[2][4] = new Montania(new Posicion(2, 4));
        tablero = new Tablero(mapa);
        tablero.colocarUnidad(new Posicion(2, 2), canion);
        Vek atras = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 7), atras);

        canion.atacar(new Posicion(2, 7), tablero);

        assertEquals(3, atras.obtenerVidaActual());
    }

    @Test
    public void testUnEdificioFrenaElDisparoDelCanion() {
        mapa[2][4] = new Edificio(new Posicion(2, 4));
        tablero = new Tablero(mapa);
        tablero.colocarUnidad(new Posicion(2, 2), canion);
        Vek atras = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 7), atras);

        canion.atacar(new Posicion(2, 7), tablero);

        assertEquals(3, atras.obtenerVidaActual());
    }

    @Test
    public void testCanionQueNoEncuentraNadaEnLaLineaNoFalla() {
        tablero.colocarUnidad(new Posicion(2, 2), canion);

        assertDoesNotThrow(() -> canion.atacar(new Posicion(2, 7), tablero));
    }

    @Test
    public void testCanionEmpujaUnaCasillaALaUnidadImpactada() {
        tablero.colocarUnidad(new Posicion(2, 2), canion);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 5), vek);

        canion.atacar(new Posicion(2, 5), tablero);

        assertTrue(vek.estaEn(new Posicion(2, 6)));
    }
}