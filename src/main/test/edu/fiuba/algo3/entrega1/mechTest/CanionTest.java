package edu.fiuba.algo3.entrega1.mechTest;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanionTest {

    private Tablero tablero;
    private List<Casilla> mapa;
    private Mech canion;

    @BeforeEach
    public void setUp() {
        mapa = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa.add(new Casilla(new Posicion(i, j)));
            }
        }
        tablero = new Tablero(mapa);
        canion = new Canion();
    }

    private void colocarObstaculo(Posicion posicion, Ocupante obstaculo) {
        mapa.removeIf(casilla -> casilla.estaEn(posicion));
        Casilla casilla = new Casilla(posicion);
        casilla.colocarOcupante(obstaculo);
        mapa.add(casilla);
        tablero = new Tablero(mapa);
    }

    @Test
    public void testCanionDaniaALaPrimeraUnidadEnLineaRecta() {
        tablero.colocarUnidad(new Posicion(2, 2), canion);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2, 5);
        tablero.colocarUnidad(ubicacionVek, vek);

        canion.atacar(ubicacionVek, tablero);

        assertTrue(vek.suVidaEsIgualA(2));
    }

    @Test
    public void testCanionNoImpactaALasUnidadesDeAtras() {
        tablero.colocarUnidad(new Posicion(2, 2), canion);
        Vek delante = new Luciernaga();
        Vek atras = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 4), delante);
        tablero.colocarUnidad(new Posicion(2, 7), atras);

        canion.atacar(new Posicion(2, 7), tablero);

        assertTrue(delante.suVidaEsIgualA(2));
        assertTrue(atras.suVidaEsIgualA(3));
    }

    @Test
    public void testUnaMontaniaFrenaElDisparoDelCanion() {
        colocarObstaculo(new Posicion(2, 4), new Montania());
        tablero.colocarUnidad(new Posicion(2, 2), canion);
        Vek atras = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 7), atras);

        canion.atacar(new Posicion(2, 7), tablero);

        assertTrue(atras.suVidaEsIgualA(3));
    }

    @Test
    public void testUnEdificioFrenaElDisparoDelCanion() {
        colocarObstaculo(new Posicion(2, 4), new Edificio());
        tablero.colocarUnidad(new Posicion(2, 2), canion);
        Vek atras = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 7), atras);

        canion.atacar(new Posicion(2, 7), tablero);

        assertTrue(atras.suVidaEsIgualA(3));
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

        assertTrue(tablero.unidadEstaEn(vek, new Posicion(2, 6)));
    }

    @Test
    public void testCanionNoPuedeDispararFueraDeLineaRecta() {
        tablero.colocarUnidad(new Posicion(2, 2), canion);

        assertThrows(AtaqueInvalidoException.class, () -> canion.atacar(new Posicion(4, 3), tablero));
    }
}