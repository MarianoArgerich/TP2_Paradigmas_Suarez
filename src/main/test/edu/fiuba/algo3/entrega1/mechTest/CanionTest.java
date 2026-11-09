package edu.fiuba.algo3.entrega_1.mech_test;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Suelo;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanionTest {

    private Tablero tablero;
    private Canion canion;

    @BeforeEach
    public void setUp() {
        Casilla[][] mapa = new Casilla[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa[i][j] = new Suelo();
            }
        }
        tablero = new Tablero(mapa);
        canion = new Canion();
    }

    @Test
    public void testCanionDaniaALaPrimeraUnidadEnLineaRecta() {
        Unidad enemigo = new Combate();
        tablero.colocarUnidad(new Posicion(1, 1), canion);
        tablero.colocarUnidad(new Posicion(1, 4), enemigo);

        canion.atacar(new Arriba(), tablero);

        assertEquals(2, enemigo.obtenerVida());
    }
}
