package edu.fiuba.algo3.entrega1.mechTest;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.ObjetivoDesalineadoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PunioTest {

    private Tablero tablero;
    private Mech combate;

    @BeforeEach
    public void setUp() {
        tablero = TableroTestFactory.crearVacio();
        combate = combate();
    }

    @Test
    public void testPunioCausa2DeDanioAUnidad() {
        Posicion ubicacionMech = new Posicion(2, 2);
        Posicion ubicacionVek = new Posicion(2, 3);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        tablero.atacar(ubicacionMech, ubicacionVek);

        assertTrue(vek.suVidaEsIgualA(1));
    }

    @Test
    public void testPunioCausaDanioAUnidadYLaEmpuja() {
        Posicion ubicacionMech = new Posicion(2, 2);
        Posicion ubicacionVek = new Posicion(2, 3);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        tablero.atacar(ubicacionMech, ubicacionVek);

        assertTrue(tablero.unidadEstaEn(vek, new Posicion(2, 4)));
    }

    @Test
    public void testPunioGolpeaYColisionaContraEdificio() {
        Posicion ubicacionMech = new Posicion(2, 2);
        Posicion ubicacionVek = new Posicion(2, 3);
        Posicion ubicacionEdificio = new Posicion(2, 4);
        Vek vek = new Luciernaga();

        tablero = TableroTestFactory.crearConOcupanteEn(ubicacionEdificio, new Edificio());
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        tablero.atacar(ubicacionMech, ubicacionVek);

        assertFalse(tablero.unidadEstaEn(vek, ubicacionVek));
    }

    @Test
    public void testPunioGolpeaEnElBordeDelTableroNoEmpuja() {
        Posicion ubicacionMech = new Posicion(2, 6);
        Posicion ubicacionVek = new Posicion(2, 7);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        tablero.atacar(ubicacionMech, ubicacionVek);

        assertTrue(tablero.unidadEstaEn(vek, ubicacionVek));
    }

    @Test
    public void testPunioGolpeaEnElBordeDelTableroNoAplicaDanioPorChoque() {
        Posicion ubicacionMech = new Posicion(2, 6);
        Posicion ubicacionVek = new Posicion(2, 7);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        tablero.atacar(ubicacionMech, ubicacionVek);
        assertTrue(vek.suVidaEsIgualA(1));
    }

    @Test
    public void testPunioNoPuedeAtacarACasillaNoAdyacente() {
        Posicion ubicacionMech = new Posicion(2, 2);
        Posicion ubicacionVek = new Posicion(2, 4);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        assertThrows(DistanciaInvalidaException.class,
                () -> tablero.atacar(ubicacionMech, ubicacionVek));
        assertTrue(vek.suVidaEsIgualA(3));
    }

    @Test
    public void testPunioNoPuedeAtacarEnDiagonal() {
        Posicion ubicacionMech = new Posicion(2, 2);
        Posicion ubicacionVek = new Posicion(3, 3);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        assertThrows(ObjetivoDesalineadoException.class,
                () -> tablero.atacar(ubicacionMech, ubicacionVek));
        assertTrue(vek.suVidaEsIgualA(3));
    }

    //medio gaga pero buenoxd, sirve
    @Test
    public void testPunioNoPuedeAtacarSuPropiaCasilla() {
        Posicion ubicacionMech = new Posicion(2, 2);
        tablero.colocarUnidad(ubicacionMech, combate);

        assertThrows(DistanciaInvalidaException.class,
                () -> tablero.atacar(ubicacionMech, ubicacionMech));
        assertTrue(combate.suVidaEsIgualA(3));
    }

    @Test
    public void testPunioPuedeVolverAAtacarLuegoDeUnAtaqueInvalido() {
        Posicion ubicacionMech = new Posicion(2, 2);
        Posicion objetivoLejano = new Posicion(2, 4);
        Posicion objetivoValido = new Posicion(2, 3);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(objetivoValido, vek);

        assertThrows(DistanciaInvalidaException.class,
                () -> tablero.atacar(ubicacionMech, objetivoLejano));

        tablero.atacar(ubicacionMech, objetivoValido);

        assertTrue(vek.suVidaEsIgualA(1));
    }
}
