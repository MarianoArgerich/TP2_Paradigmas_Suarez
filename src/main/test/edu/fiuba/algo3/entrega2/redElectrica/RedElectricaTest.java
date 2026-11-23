package edu.fiuba.algo3.entrega2.redElectrica;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedElectricaTest {

    private Tablero tablero;
    private Mech combate;
    private Posicion posicionMech;
    private Posicion posicionEdificio;

    @BeforeEach
    public void setUp() {
        combate = combate();
        posicionMech = new Posicion(2, 2);
        posicionEdificio = new Posicion(2, 3);
        tablero = TableroTestFactory.crearConOcupanteEn(posicionEdificio, new Edificio());
    }

    @Test
    public void testAlHacerDanioAUnEdificioLaRedElectricaSeReduceEn1() {
        tablero.colocarUnidad(posicionMech, combate);

        tablero.atacar(posicionMech, posicionEdificio);

        assertEquals(4, tablero.getEnergia());
    }

    @Test
    public void testAlFallarElAtaqueAUnEdificioLaRedElectricaNoPierdeEnergia() {
        Posicion posicionErrada = new Posicion(2, 1);
        tablero.colocarUnidad(posicionMech, combate);

        tablero.atacar(posicionMech, posicionErrada);

        assertEquals(5, tablero.getEnergia());
    }

    @Test
    public void testUnVekEmpujadoContraUnEdificioReduceLaRedElectricaEn1() {
        Vek vek = new Luciernaga();
        Posicion posicionVek = new Posicion(2, 1);
        Posicion posicionEdificioChocable = new Posicion(2, 0);

        tablero = TableroTestFactory.iniciar()
                .conOcupanteEn(posicionEdificio, new Edificio())
                .conOcupanteEn(posicionEdificioChocable, new Edificio())
                .construir();
        tablero.colocarUnidad(posicionVek, vek);
        tablero.colocarUnidad(posicionMech, combate);

        tablero.atacar(posicionMech, posicionVek);

        assertEquals(4, tablero.getEnergia());
    }

    @Test
    public void testAtacarUnEdificioPreviamenteDestruidoNoReduceLaEnergia() {
        Mech mech2 = combate();
        Posicion posicionMech2 = new Posicion(3, 3);
        tablero.colocarUnidad(posicionMech, combate);
        tablero.colocarUnidad(posicionMech2, mech2);

        tablero.atacar(posicionMech2, posicionEdificio);
        tablero.atacar(posicionMech, posicionEdificio);

        assertEquals(4, tablero.getEnergia());
    }

    @Test
    public void testAtacarDosEdificiosEnElMismoTurnoReduceLaEnergiaEn2() {
        Posicion posicionEdificio2 = new Posicion(3, 2);
        Posicion posicionMech2 = new Posicion(3, 3);
        Mech mech2 = combate();

        tablero = TableroTestFactory.iniciar()
                .conOcupanteEn(posicionEdificio, new Edificio())
                .conOcupanteEn(posicionEdificio2, new Edificio())
                .construir();
        tablero.colocarUnidad(posicionMech, combate);
        tablero.colocarUnidad(posicionMech2, mech2);

        tablero.atacar(posicionMech, posicionEdificio);
        tablero.atacar(posicionMech2, posicionEdificio2);

        assertEquals(3, tablero.getEnergia());
    }

    @Test
    public void testUnAtaqueDeVekAUnEdificioReduceLaRedElectrica() {
        Vek vek = new Luciernaga();
        Posicion posicionVek = new Posicion(2, 1);
        tablero.colocarUnidad(posicionVek, vek);

        tablero.atacar(posicionVek, posicionEdificio);

        assertEquals(4, tablero.getEnergia());
    }

    @Test
    public void testDestruirCincoEdificiosDejaLaRedElectricaEnCero() {
        tablero = TableroTestFactory.iniciar()
                .conOcupanteEn(new Posicion(0, 1), new Edificio())
                .conOcupanteEn(new Posicion(1, 1), new Edificio())
                .conOcupanteEn(new Posicion(2, 1), new Edificio())
                .conOcupanteEn(new Posicion(3, 1), new Edificio())
                .conOcupanteEn(new Posicion(4, 1), new Edificio())
                .construir();

        for (int i = 0; i < 5; i++) {
            Posicion origen = new Posicion(i, 0);
            tablero.colocarUnidad(origen, combate());
            tablero.atacar(origen, new Posicion(i, 1));
        }

        assertEquals(0, tablero.getEnergia());
    }
}