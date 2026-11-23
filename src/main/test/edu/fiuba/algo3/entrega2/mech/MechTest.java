package edu.fiuba.algo3.entrega2.mech;

import edu.fiuba.algo3.TableroTestFactory; // Ajustá el paquete/nombre si la dejaste como TableroFactory
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Empuje;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.canion;
import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MechTest {

    private Tablero tablero;
    private Mech mech1;
    private Mech mech2;

    @BeforeEach
    public void setUp() {
        tablero = TableroTestFactory.crearVacio();
        mech1 = combate();
        mech2 = canion();
    }

    @Test
    public void testMechCanionAtacaAMech2YMech2NoSeMuevePorObstaculo() {
        Posicion posicionMech1 = new Posicion(2, 1);
        Posicion posicionMech2 = new Posicion(2, 2);
        Posicion posicionObstaculo = new Posicion(2, 0);

        tablero = TableroTestFactory.crearConOcupanteEn(posicionObstaculo, new Edificio());
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionMech2, mech2);

        tablero.atacar(posicionMech2, posicionMech1);

        assertTrue(tablero.unidadEstaEn(mech1, posicionMech1));
    }

    @Test
    public void testMechCanionAtacaAMech2YAlChocarConEdificioEsteRecibeDanioPorColision() {
        Posicion posicionMech1 = new Posicion(2, 1);
        Posicion posicionMech2 = new Posicion(2, 2);
        Posicion posicionObstaculo = new Posicion(2, 3);

        tablero = TableroTestFactory.crearConOcupanteEn(posicionObstaculo, new Edificio());
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionMech2, mech2);

        tablero.atacar(posicionMech1, posicionMech2);

        assertFalse(tablero.obtenerPosicionesDeEdificios().contains(posicionObstaculo));
    }

    @Test
    public void testMechCanionAtacaAMech2YAlChocarConEdificioElMechRecibeDanioPorColision() {
        Posicion posicionMech1 = new Posicion(2, 1);
        Posicion posicionMech2 = new Posicion(2, 2);
        Posicion posicionObstaculo = new Posicion(2, 0);

        tablero = TableroTestFactory.crearConOcupanteEn(posicionObstaculo, new Edificio());
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionMech2, mech2);

        tablero.atacar(posicionMech2, posicionMech1);

        assertTrue(mech1.suVidaEsIgualA(1));
    }

    @Test
    public void testAlEmpujarUnMech1ContraOtroMech2ElMech1NoSeMueve() {
        Posicion posicionMech1 = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(new Posicion(2, 3), mech2);

        tablero.aplicarEfecto(posicionMech1, new Empuje(new Arriba()));

        assertTrue(tablero.unidadEstaEn(mech1, posicionMech1));
    }

    @Test
    public void test02AlEmpujarUnMech1ContraOtroMech2SeAplicaElDanioDeColision() {
        Posicion posicionMech1 = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(new Posicion(2, 3), mech2);

        tablero.aplicarEfecto(posicionMech1, new Empuje(new Arriba()));

        assertTrue(mech1.suVidaEsIgualA(2));
        assertTrue(mech2.suVidaEsIgualA(2));
    }

    @Test
    public void test03AlEmpujarUnMechContraUnVekElMechNoSeMueve() {
        Posicion posicionMech1 = new Posicion(2, 2);
        Posicion posicionVek = new Posicion(3, 2);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionVek, vek);

        tablero.aplicarEfecto(posicionMech1, new Empuje(new Derecha()));

        assertTrue(tablero.unidadEstaEn(mech1, posicionMech1));
    }

    @Test
    public void test04AlEmpujarUnMechContraUnVekSeAplicaElDañoDeColision() {
        Posicion posicionMech1 = new Posicion(2, 2);
        Posicion posicionVek = new Posicion(3, 2);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionVek, vek);

        tablero.aplicarEfecto(posicionMech1, new Empuje(new Derecha()));

        assertTrue(mech1.suVidaEsIgualA(2));
    }

    @Test
    public void testMechColocadoEnElAguaNoPuedeAtacar() {
        Posicion posicionAgua = new Posicion(2, 2);
        tablero = TableroTestFactory.crearConTerrenoEn(posicionAgua, new Agua());
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(posicionAgua, mech1);
        tablero.colocarUnidad(new Posicion(2, 3), luciernaga);

        assertThrows(AtaqueInvalidoException.class,
                () -> tablero.atacar(posicionAgua, new Posicion(2, 3)));
        assertTrue(luciernaga.suVidaEsIgualA(3));
    }
}