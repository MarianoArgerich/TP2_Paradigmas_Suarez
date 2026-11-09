package edu.fiuba.algo3.entrega1.mechTest;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Suelo;
import edu.fiuba.algo3.modelo.direccion.Abajo;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.excepciones.YaAtacoException;
import edu.fiuba.algo3.modelo.excepciones.YaSeMovioException;
import edu.fiuba.algo3.modelo.ocupantes.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class mechTest1 {

    private Casilla[][] mapa;
    private Tablero tablero;
    private Mech mech;

    @BeforeEach
    public void setUp() {
        mapa = new Casilla[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa[i][j] = new Suelo(new Posicion(i, j));
            }
        }
        tablero = new Tablero(mapa);
        mech = new Combate();
    }

    @Test
    public void testMechNoPuedeMoverseMasAllaDeSuRango() {
        // Mech en (0, 0). Intenta moverse a (4, 0) solo tiene 3 pasos
        Posicion coordenadas = new Posicion(0, 0);
        tablero.colocarUnidad(coordenadas, mech);

        assertThrows(RuntimeException.class, () -> mech.mover(new Arriba(), 4, tablero));
    }

    @Test
    public void testMechNoPuedeMoverseFueraDelTablero() {
        Posicion coordenadas = new Posicion(7, 7);
        tablero.colocarUnidad(coordenadas, mech);

        mech.mover(new Abajo(), 3, tablero);

        assertTrue(mech.estaEn(coordenadas));

    }

    @Test
    public void testMechNoPuedeAtravesarUnidades() {
        Posicion posicionInicial = new Posicion(1, 1);
        tablero.colocarUnidad(posicionInicial, mech);
        tablero.colocarUnidad(new Posicion(1, 2), new Combate());

        mech.mover(new Abajo(), 2, tablero);

        assertTrue(mech.estaEn(posicionInicial));
    }

    @Test
    public void testMechNoPuedeAtravesarObstaculos() {

        mapa[1][2] = new Edificio(new Posicion(1, 2));
        tablero = new Tablero(mapa);

        Posicion posicionInicial = new Posicion(1, 1);
        tablero.colocarUnidad(posicionInicial, mech);

        mech.mover(new Abajo(), 2, tablero);
        assertTrue(mech.estaEn(posicionInicial));
    }

    @Test
    public void testMechNoPuedeMoverseDosVecesEnElMismoTurno() {

        Posicion coordenadas = new Posicion(5, 5);
        tablero.colocarUnidad(coordenadas, mech);
        mech.mover(new Arriba(), 1, tablero);

        assertThrows(YaSeMovioException.class, () -> {
            mech.mover(new Derecha(), 1, tablero);
        }, "El Mech no debería poder moverse dos veces en un turno");


    }


    @Test
    public void testMechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Posicion coordenadas = new Posicion(4, 4);
        tablero.colocarUnidad(coordenadas, mech);
        mech.atacar(new Posicion(4, 5), tablero);

        assertThrows(YaAtacoException.class, () -> {
            mech.atacar(new Posicion(4, 6), tablero);
        }, "El Mech no debería poder atacar dos veces en un turno");
    }
}
