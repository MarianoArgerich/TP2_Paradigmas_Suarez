package edu.fiuba.algo3.entrega1.mechTest;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanionTest {

    private Tablero tablero;
    private List<Casilla> mapa = new ArrayList<>();
    private Mech canion;

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa.add(new Casilla(new Posicion(i, j)));
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

        assertTrue( vek.suVidaEsIgualA(2));
    }
}