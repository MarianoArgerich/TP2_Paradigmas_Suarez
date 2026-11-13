package edu.fiuba.algo3.entrega1.mechTest;

import edu.fiuba.algo3.modelo.Tablero.Tablero;

import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Abajo;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.excepciones.*;

import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
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
                mapa.add(new Casilla(new Posicion(i, j), new Suelo()) {
                });
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

        assertThrows(DistanciaInvalidaException.class, () -> tablero.mover(new Posicion(0, 0), new Arriba(), 4));
    }

    @Test
    public void testMechNoPuedeMoverseFueraDelTablero() {
        Posicion coordenadas = new Posicion(7, 7);
        tablero.colocarUnidad(coordenadas, mech1);

        tablero.mover(new Posicion(7, 7), new Arriba(), 3);

        assertTrue(tablero.unidadEstaEn(mech1, coordenadas));

    }

    @Test
    public void testMechNoPuedeAtravesarUnidades() {
        Posicion posicionInicial = new Posicion(1, 1);
        tablero.colocarUnidad(posicionInicial, mech1);
        tablero.colocarUnidad(new Posicion(1, 2), new Combate());

        tablero.mover(new Posicion(1, 1), new Arriba(), 2);

        assertTrue(tablero.unidadEstaEn(mech1, posicionInicial));
    }

    @Test
    public void testMechNoPuedeAtravesarObstaculos() {
        mapa.removeIf(casilla -> casilla.estaEn(new Posicion(1, 2)));
        Casilla casilla = new Casilla(new Posicion(1, 2));
        casilla.colocarOcupante(new Edificio());

        mapa.add(casilla);
        tablero = new Tablero(mapa);

        Posicion posicionInicial = new Posicion(1, 1);
        tablero.colocarUnidad(posicionInicial, mech1);

        tablero.mover(new Posicion(1, 1), new Arriba(), 2);
        assertTrue(tablero.unidadEstaEn(mech1, posicionInicial));
    }

    @Test
    public void testMechNoPuedeMoverseDosVecesEnElMismoTurno() {

        Posicion coordenadas = new Posicion(5, 5);
        tablero.colocarUnidad(coordenadas, mech1);
        tablero.mover(coordenadas,new Arriba(), 1);

        assertThrows(YaSeMovioException.class, () ->
            tablero.mover(new Posicion(5, 6), new Derecha(), 1)
        , "El Mech no debería poder moverse dos veces en un turno");


    }


    @Test
    public void testMechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Posicion coordenadas = new Posicion(4, 4);
        tablero.colocarUnidad(coordenadas, mech1);
        tablero.atacar(new Posicion(4, 4), new Posicion(4, 5));

        assertThrows(YaAtacoException.class, () ->
            tablero.atacar(new Posicion(4, 4), new Posicion(4, 5))
        , "El Mech no debería poder atacar dos veces en un turno");
    }

    @Test
    public void testDeshacerMovimientoDeUnMechQueTodaviaNoAtacoYVuelveASuPosicionAnterior(){
        Posicion posicionInicial = new Posicion(3, 3);
        tablero.colocarUnidad(posicionInicial, mech1);

        tablero.mover(new Posicion(3, 3), new Abajo(), 2);
        tablero.deshacerMovimiento(new Posicion(3, 1));


        assertTrue(tablero.unidadEstaEn(mech1, posicionInicial));
    }

    @Test
    public void testNoSePuedeDeshacerMasDeUnMovimientoPorTurno(){
        Posicion posicionInicial = new Posicion(3, 3);
        tablero.colocarUnidad(posicionInicial, mech1);
        tablero.mover(posicionInicial, new Abajo(), 2);
        tablero.deshacerMovimiento(new Posicion(3, 1));

        assertThrows(NoSePuedeDeshacerMovimiento.class, () ->
            tablero.deshacerMovimiento(new Posicion(3, 3))
        , "No se puede deshacer mas de un movimiento");
    }

    @Test
    public void testNoSePuedeDeshacerMovimientoDespuesDeQueElMechAtaca(){
        Posicion posicionInicial = new Posicion(4, 6);
        tablero.colocarUnidad(posicionInicial, mech1);
        tablero.mover(posicionInicial, new Abajo(), 2);
        tablero.colocarUnidad(new Posicion(4, 3), new Luciernaga());
        tablero.atacar(new Posicion(4, 4), new Posicion(4, 3));


        assertThrows(NoSePuedeDeshacerMovimiento.class, () ->
            tablero.deshacerMovimiento(new Posicion(4, 4))
        , "No se puede deshacer un movimiento despues de atacar");
    }


    @Test
    public void testVariosMechPuedenDeshacerMovimientosAntesDeAtacar(){

        Posicion posicionInicial1 = new Posicion(3, 3);
        tablero.colocarUnidad(posicionInicial1, mech1);
        tablero.mover(posicionInicial1, new Abajo(), 2);


        Posicion posicionInicial2 = new Posicion(4, 4);
        tablero.colocarUnidad(posicionInicial2, mech2);
        tablero.mover(posicionInicial2, new Abajo(), 3);

        tablero.deshacerMovimiento(new Posicion(3, 1));
        tablero.deshacerMovimiento(new Posicion(4, 1));

        assertTrue(tablero.unidadEstaEn(mech2, posicionInicial2));
        assertTrue(tablero.unidadEstaEn(mech1, posicionInicial1));

    }

}
