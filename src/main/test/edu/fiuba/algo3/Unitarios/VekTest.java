package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VekTest {

    @Test
    public void testVekSaltadorNaceConSuVidaIgualA2() {
        Saltador saltador = new Saltador();

        assertTrue(saltador.suVidaEsIgualA(2));
    }

    @Test
    public void testVekSaltadorNaceConSuRangoDeMovimientoIgualA3() {
        Saltador saltador = new Saltador();

        assertDoesNotThrow(() -> saltador.verificarRangoDeMovimiento(3));
    }

    @Test
    public void testVekLuciernagaNaceConSuVidaIgualA3() {
        Luciernaga luciernaga = new Luciernaga();

        assertTrue(luciernaga.suVidaEsIgualA(3));
    }

    @Test
    public void testVekLuciernagaNaceConSuRangoDeMovimientoIgualA2() {
        Luciernaga luciernaga = new Luciernaga();

        assertDoesNotThrow(() -> luciernaga.verificarRangoDeMovimiento(2));
    }

    @Test
    public void testVekAvisponNaceConSuVidaIgualA2() {
        Avispon avispon = new Avispon();

        assertTrue(avispon.suVidaEsIgualA(2));
    }

    @Test
    public void testVekAvisponNaceConSuRangoDeMovimientoIgualA4() {
        Avispon avispon = new Avispon();

        assertDoesNotThrow(() -> avispon.verificarRangoDeMovimiento(4));
    }

    @Test
    public void testSaltadorMuereAlCaerAlAgua() {
        Casilla casillaSaltador = new Casilla(new Posicion(0, 0), new Agua());
        Saltador saltador = new Saltador();
        casillaSaltador.colocarOcupante(saltador);

        assertTrue(saltador.suVidaEsIgualA(0));
    }

    @Test
    public void testAlMorirSaltadorSeVaciaSuCasilla() {
        Casilla casillaSaltador = new Casilla(new Posicion(0, 0), new Agua());
        Saltador saltador = new Saltador();
        casillaSaltador.colocarOcupante(saltador);

        assertFalse(casillaSaltador.tieneUnidad(saltador));
    }

    @Test
    public void testLuciernagaMuereAlCaerAlAgua() {
        Casilla casillaLuciernaga = new Casilla(new Posicion(1, 0), new Agua());
        Luciernaga luciernaga = new Luciernaga();
        casillaLuciernaga.colocarOcupante(luciernaga);

        assertTrue(luciernaga.suVidaEsIgualA(0));
    }

    @Test
    public void testAlMorirLuciernagaSeVaciaSuCasilla() {
        Casilla casillaLuciernaga = new Casilla(new Posicion(1, 0), new Agua());
        Luciernaga luciernaga = new Luciernaga();
        casillaLuciernaga.colocarOcupante(luciernaga);

        assertFalse(casillaLuciernaga.tieneUnidad(luciernaga));
    }

    @Test
    public void testAvisponNoMuereAlPosicionarseEnAgua() {
        Casilla casillaAvispon = new Casilla(new Posicion(2, 0), new Agua());
        Avispon avispon = new Avispon();
        casillaAvispon.colocarOcupante(avispon);

        assertTrue(avispon.suVidaEsIgualA(2));
    }


    @Test
    public void testAvisponNoSeContaminaPorAcido() {
        Casilla casilla = new Casilla(new Posicion(1, 1), new Acido());
        Avispon avispon = new Avispon();
        casilla.colocarOcupante(avispon);

        avispon.recibirDanio(1, casilla);

        assertTrue(avispon.suVidaEsIgualA(1));
    }
}
