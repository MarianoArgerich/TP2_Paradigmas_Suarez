package edu.fiuba.algo3.entrega_1.mech_test;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Suelo;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
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
}