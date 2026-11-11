package edu.fiuba.algo3.entrega1.mechTest;

import edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.transitable.Suelo;
import edu.fiuba.algo3.modelo.direccion.Abajo;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeDeshacerMovimiento;
import edu.fiuba.algo3.modelo.excepciones.YaAtacoException;
import edu.fiuba.algo3.modelo.excepciones.YaSeMovioException;

import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MechTest {

    private List<Casilla> mapa =  new ArrayList<>();
    private Tablero tablero;
    private Mech mech1;
    private Mech mech2;

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa.add( new Suelo(new Posicion(i, j)));
            }
        }
        tablero = new Tablero(mapa);
        mech1 = new Combate();
        mech2 = new Canion();
    }

    @Test
    public void testMechNoPuedeMoverseMasAllaDeSuRango() {
        // Mech en (0, 0). Intenta moverse a (4, 0) solo tiene 3 pasos
        Posicion coordenadas = new Posicion(0, 0);
        tablero.colocarUnidad(coordenadas, mech1);

        assertThrows(RuntimeException.class, () -> mech1.mover(new Arriba(), 4, tablero));
    }

    @Test
    public void testMechNoPuedeMoverseFueraDelTablero() {
        Posicion coordenadas = new Posicion(7, 7);
        tablero.colocarUnidad(coordenadas, mech1);

        mech1.mover(new Abajo(), 3, tablero);

        assertTrue(mech1.estaEn(coordenadas, tablero));

    }

    @Test
    public void testMechNoPuedeAtravesarUnidades() {
        Posicion posicionInicial = new Posicion(1, 1);
        tablero.colocarUnidad(posicionInicial, mech1);
        tablero.colocarUnidad(new Posicion(1, 2), new Combate());

        mech1.mover(new Abajo(), 2, tablero);

        assertTrue(mech1.estaEn(posicionInicial, tablero));
    }

    @Test
    public void testMechNoPuedeAtravesarObstaculos() {
        mapa.removeIf(casilla -> casilla.estaEn(new Posicion(1, 2)));
        mapa.add(new Edificio(new Posicion(1, 2)));
        tablero = new Tablero(mapa);

        Posicion posicionInicial = new Posicion(1, 1);
        tablero.colocarUnidad(posicionInicial, mech1);

        mech1.mover(new Abajo(), 2, tablero);
        assertTrue(mech1.estaEn(posicionInicial, tablero));
    }

    @Test
    public void testMechNoPuedeMoverseDosVecesEnElMismoTurno() {

        Posicion coordenadas = new Posicion(5, 5);
        tablero.colocarUnidad(coordenadas, mech1);
        mech1.mover(new Arriba(), 1, tablero);

        assertThrows(YaSeMovioException.class, () ->
            mech1.mover(new Derecha(), 1, tablero)
        , "El Mech no debería poder moverse dos veces en un turno");


    }


    @Test
    public void testMechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Posicion coordenadas = new Posicion(4, 4);
        tablero.colocarUnidad(coordenadas, mech1);
        mech1.atacar(new Posicion(4, 5), tablero);

        assertThrows(YaAtacoException.class, () ->
            mech1.atacar(new Posicion(4, 6), tablero)
        , "El Mech no debería poder atacar dos veces en un turno");
    }

    @Test
    public void testDeshacerMovimientoDeUnMechQueTodaviaNoAtacoYVuelveASuPosicionAnterior(){
        Posicion posicionInicial = new Posicion(3, 3);
        tablero.colocarUnidad(posicionInicial, mech1);

        mech1.mover(new Abajo(), 2, tablero);
        mech1.deshacerMovimiento(tablero);


        assertTrue(mech1.estaEn(posicionInicial, tablero));
    }

    @Test
    public void testNoSePuedeDeshacerMasDeUnMovimientoPorTurno(){
        Posicion posicionInicial = new Posicion(3, 3);
        tablero.colocarUnidad(posicionInicial, mech1);
        mech1.mover(new Abajo(), 2, tablero);
        mech1.deshacerMovimiento(tablero);

        assertThrows(NoSePuedeDeshacerMovimiento.class, () ->
            mech1.deshacerMovimiento(tablero)
        , "No se puede deshacer mas de un movimiento");

    }

    @Test
    public void testNoSePuedeDeshacerMovimientoDespuesDeQueElMetchAtaca(){
        Posicion posicionInicial = new Posicion(3, 3);
        tablero.colocarUnidad(posicionInicial, mech1);
        mech1.mover(new Abajo(), 2, tablero);
        mech1.atacar(new Posicion(4, 5), tablero);

        assertThrows(NoSePuedeDeshacerMovimiento.class, () ->
            mech1.deshacerMovimiento(tablero)
        , "No se puede deshacer un movimiento despues de atacar");
    }


    @Test
    public void testVariosMechPuedenDeshacerMovimientosAntesDeAtacar(){

        Posicion posicionInicial1 = new Posicion(3, 3);
        tablero.colocarUnidad(posicionInicial1, mech1);
        mech1.mover(new Abajo(), 2, tablero);


        Posicion posicionInicial2 = new Posicion(4, 4);
        tablero.colocarUnidad(posicionInicial2, mech2);
        mech2.mover(new Abajo(), 3, tablero);

        mech1.deshacerMovimiento(tablero);
        mech2.deshacerMovimiento(tablero);

        assertTrue(mech2.estaEn(posicionInicial2, tablero));
        assertTrue(mech1.estaEn(posicionInicial1, tablero));

    }

}
