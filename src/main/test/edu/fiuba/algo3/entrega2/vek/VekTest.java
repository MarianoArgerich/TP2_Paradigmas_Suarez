package edu.fiuba.algo3.entrega2.vek;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.FaseEmergencia;
import edu.fiuba.algo3.modelo.Turno.Fases.FaseResolucion;
import edu.fiuba.algo3.modelo.Turno.Fases.FaseVek;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Empuje;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.canion;
import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VekTest {

    private Tablero tablero;
    private Luciernaga luciernaga1;
    private Luciernaga luciernaga2;
    private Mech mech1;
    private Mech mech2;
    private Saltador saltador;
    private Avispon avispon;
    private FaseVek faseVek;
    private FaseResolucion faseResolucion;

    @BeforeEach
    public void setUp() {
        tablero = TableroTestFactory.crearVacio();
        faseVek = new FaseVek();
        faseResolucion = new FaseResolucion();
        luciernaga1 = new Luciernaga();
        luciernaga2 = new Luciernaga();
        mech1 = combate();
        mech2 = canion();
        saltador = new Saltador();
        avispon = new Avispon();
    }

    @Test
    public void testAlEmpujarUnVek1ContraOtroVek2ElVek1NoSeMueve() {
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), luciernaga2);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(tablero.unidadEstaEn(luciernaga1, new Posicion(2, 2)));
    }

    @Test
    public void testAlEmpujarLuciernagaContraOtroAvisponSeAplicaElDanioDeColisionALaLuciernaga() {
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), avispon);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(luciernaga1.suVidaEsIgualA(2));
    }

    @Test
    public void testAlEmpujarLuciernagaContraOtroAvisponSeAplicaElDanioDeColisionAlAvispon() {
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), avispon);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));
        assertTrue(avispon.suVidaEsIgualA(1));
    }

    @Test
    public void testAlEmpujarLuciernagaContraOtroSaltadorSeAplicaElDanioDeColisionAlSaltador() {
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), saltador);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(saltador.suVidaEsIgualA(1));
    }

    @Test
    public void testAlEmpujarUnVekContraOtroMechElVekNoSeMueve() {
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), mech1);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(tablero.unidadEstaEn(luciernaga1, new Posicion(2, 2)));
    }

    @Test
    public void testAlEmpujarUnVekContraOtroMechSeAplicaElDanioDeColisionAlMech() {
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), mech1);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(mech1.suVidaEsIgualA(2));
    }

    @Test
    public void testAlEmpujarUnVekContraOtroMechSeAplicaElDanioDeColisionAlVek() {
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), mech1);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(luciernaga1.suVidaEsIgualA(2));
    }

    @Test
    public void testAlEmpujarUnVekContraUnEdificioLaUnidadNoSeMueve() {
        tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(2, 3), new Edificio());
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(tablero.unidadEstaEn(luciernaga1, new Posicion(2, 2)));
    }

    @Test
    public void testAlEmpujarUnVekContraUnMontaniaLaUnidadNoSeMueve() {
        tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(2, 3), new Montania());
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(tablero.unidadEstaEn(luciernaga1, new Posicion(2, 2)));
    }

    @Test
    public void testAlEmpujarUnVekContraUnEdificioSeAplicaElDanioDeColision() {
        tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(2, 3), new Edificio());
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(luciernaga1.suVidaEsIgualA(2));
    }

    @Test
    public void testAlEmpujarUnVekContraUnMontaniaSeAplicaElDanioDeColision() {
        tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(2, 3), new Montania());
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(luciernaga1.suVidaEsIgualA(2));
    }

    @Test
    public void test09AlColocarseUnVekSobreLaCasillaDeEmergenciaBloqueaLaAparicionDelNuevoVek() {
        Posicion posicionEmergencia = new Posicion(4, 4);
        Vek vekBloqueador = new Luciernaga();
        Vek vekEntrante = new Saltador();
        FaseEmergencia faseEmergencia = new FaseEmergencia();

        tablero.anunciarEmergencia(posicionEmergencia, vekEntrante);
        tablero.colocarUnidad(posicionEmergencia, vekBloqueador);

       faseEmergencia.avanzar(tablero);

        assertFalse(tablero.tieneEmergenciaAnunciadaEn(posicionEmergencia));
        assertTrue(tablero.unidadEstaEn(vekBloqueador, posicionEmergencia));
        assertFalse(tablero.unidadEstaEn(vekEntrante, posicionEmergencia));
    }

    @Test
    public void test10AlColocarseUnMechSobreLaCasillaDeEmergenciaRecibeUnoDeDanio() {
        Posicion posicionEmergencia = new Posicion(4, 4);
        FaseEmergencia faseEmergencia = new FaseEmergencia();

        tablero.anunciarEmergencia(posicionEmergencia, saltador);
        tablero.colocarUnidad(posicionEmergencia, mech1);

        faseEmergencia.avanzar(tablero);
        assertTrue(mech1.suVidaEsIgualA(2));
    }

    @Test
    public void test10AlColocarseUnMechSobreLaCasillaDeEmergenciaNoDejaQueAparezcaElVek() {
        Posicion posicionEmergencia = new Posicion(4, 4);
        FaseEmergencia faseEmergencia = new FaseEmergencia();

        tablero.anunciarEmergencia(posicionEmergencia, saltador);
        tablero.colocarUnidad(posicionEmergencia, mech1);

        faseEmergencia.avanzar(tablero);
        assertFalse(tablero.tieneEmergenciaAnunciadaEn(posicionEmergencia));
        assertTrue(tablero.unidadEstaEn(mech1, posicionEmergencia));
        assertFalse(tablero.unidadEstaEn(saltador, posicionEmergencia));
    }

    @Test
    public void test11LuciernagaEsEmpujadaAlAguaYSuCasillaSeVacia() {
        tablero = TableroTestFactory.crearConTerrenoEn(new Posicion(2, 3), new Agua());
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertFalse(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
        assertFalse(tablero.unidadEstaEn(luciernaga, new Posicion(2, 3)));
    }

    @Test
    public void test11bLuciernagaEsEmpujadaAlAguaYSeMuere() {
        tablero = TableroTestFactory.crearConTerrenoEn(new Posicion(2, 3), new Agua());
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(luciernaga.suVidaEsIgualA(0));
    }

    @Test
    public void test12SaltadorEsEmpujadaAlAguaYSuCasillaSeVacia() {
        tablero = TableroTestFactory.crearConTerrenoEn(new Posicion(2, 3), new Agua());
        Saltador saltadorLocal = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltadorLocal);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertFalse(tablero.unidadEstaEn(saltadorLocal, new Posicion(2, 2)));
        assertFalse(tablero.unidadEstaEn(saltadorLocal, new Posicion(2, 3)));
    }

    @Test
    public void test12bSaltadorEsEmpujadaAlAguaYSeMuere() {
        tablero = TableroTestFactory.crearConTerrenoEn(new Posicion(2, 3), new Agua());
        Saltador saltadorLocal = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltadorLocal);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(saltadorLocal.suVidaEsIgualA(0));
    }

    @Test
    public void test13AvisponEsEmpujadaAlAgua() {
        tablero = TableroTestFactory.crearConTerrenoEn(new Posicion(2, 3), new Agua());
        Avispon avisponLocal = new Avispon();
        tablero.colocarUnidad(new Posicion(2, 2), avisponLocal);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(tablero.unidadEstaEn(avisponLocal, new Posicion(2, 3)));
    }

    @Test
    public void test13bAvisponEsEmpujadaAlAguaYNoSeMuere() {
        tablero = TableroTestFactory.crearConTerrenoEn(new Posicion(2, 3), new Agua());
        Avispon avisponLocal = new Avispon();
        tablero.colocarUnidad(new Posicion(2, 2), avisponLocal);

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Arriba()));

        assertTrue(avisponLocal.suVidaEsIgualA(2));
    }

    @Test
    public void test14LaIADeLosVekAplicaLasReglasDePrioridadEnOrden() {
        tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(4, 5), new Edificio());
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 3), mech);

        faseVek.avanzar(tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(4, 2)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(4, 5)));
    }

    @Test
    public void test14bSiNoPuedeAtacarEdificioAtacaMechYDesempataPorMenorFilaYColumna() {
        tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(7, 7), new Edificio());
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(1, 1), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 4), mech);

        faseVek.avanzar(tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(2, 1)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 4)));
    }

    @Test
    public void test14cSiNoPuedeAtacarSeAcercaAlEdificioMasCercanoSinAnunciarAtaqueYEvitandoAguaYAcido() {
        Posicion edificioCercano = new Posicion(3, 5);
        Posicion edificioLejano = new Posicion(7, 4);

        tablero = TableroTestFactory.iniciar()
                .conTerrenoEn(new Posicion(3, 1), new Agua())
                .conTerrenoEn(new Posicion(2, 2), new Acido())
                .conOcupanteEn(edificioCercano, new Edificio())
                .conOcupanteEn(edificioLejano, new Edificio())
                .construir();

        Vek luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(1, 1), luciernaga);

        faseVek.avanzar(tablero);
        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(1, 3)));
        assertFalse(tablero.tieneAtaqueAnunciadoEn(edificioCercano));
        assertFalse(tablero.tieneAtaqueAnunciadoEn(edificioLejano));

        tablero.resolverAtaques();
        assertEquals(5, tablero.getEnergia());
    }

    @Test
    public void test15AlEmpujarAUnVekSuAtaqueSeDesplazaConEl() {
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Derecha()));

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(3, 2)));
        assertFalse(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(3, 5)));
    }

    @Test
    public void testEmpujarAUnaLuciernagaPrevioAAtacar() {
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);
        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Derecha()));

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(3, 2)));
    }

    @Test
    public void testAlEmpujarAUnaLuciernagaSuAtaqueSeDesplazaConEl() {
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);
        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Derecha()));

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(3, 5)));
    }

    @Test
    public void testAlEmpujarAUnSaltadorSuAtaqueSeDesplazaConEl() {
        Vek saltadorLocal = new Saltador();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 3), saltadorLocal);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);
        tablero.aplicarEfecto(new Posicion(2, 4), new Empuje(new Derecha()));

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(3, 5)));
    }

    @Test
    public void testAlEmpujarAUnSaltadorPrevioAAtacar() {
        Vek saltadorLocal = new Saltador();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 3), saltadorLocal);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);
        tablero.aplicarEfecto(new Posicion(2, 4), new Empuje(new Derecha()));

        assertTrue(tablero.unidadEstaEn(saltadorLocal, new Posicion(3, 4)));
    }

    @Test
    public void testAlEmpujarAUnAvisponPrevioAAtacar() {
        Vek avisponLocal = new Avispon();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 3), avisponLocal);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);
        tablero.aplicarEfecto(new Posicion(2, 4), new Empuje(new Derecha()));

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(3, 5)));
    }

    @Test
    public void testAlEmpujarAUnAvisponSuAtaqueSeDesplazaConEl() {
        Vek avisponLocal = new Avispon();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 3), avisponLocal);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);

        assertTrue(tablero.unidadEstaEn(avisponLocal, new Posicion(2, 4)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));

        tablero.aplicarEfecto(new Posicion(2, 4), new Empuje(new Derecha()));

        assertTrue(tablero.unidadEstaEn(avisponLocal, new Posicion(3, 4)));
        assertFalse(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(3, 5)));
    }

    @Test
    public void test16AlEmpujarAUnVekSuAtaqueSeDesplazaConElApuntandoAOtroMechDaniandolo() {
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();
        Mech otroMech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);
        tablero.colocarUnidad(new Posicion(3, 5), otroMech);

        faseVek.avanzar(tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Derecha()));

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(3, 2)));
        assertFalse(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(3, 5)));

        faseResolucion.avanzar(tablero);
        assertTrue(otroMech.suVidaEsIgualA(2));
    }

    @Test
    public void test16AlEmpujarAUnVekSuAtaqueSeDesplazaConElApuntandoAOtroVekDaniandolo() {
        Vek luciernaga = new Luciernaga();
        Vek otraLuciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);
        tablero.colocarUnidad(new Posicion(3, 5), otraLuciernaga);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));

        tablero.aplicarEfecto(new Posicion(2, 2), new Empuje(new Derecha()));

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(3, 2)));
        assertFalse(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(3, 5)));

        faseResolucion.avanzar(tablero);
        assertTrue(otraLuciernaga.suVidaEsIgualA(2));
    }

    @Test
    public void testSaltadorAnunciaSuAtaqueEnFaseVek() {
        Vek saltadorLocal = new Saltador();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), saltadorLocal);
        tablero.colocarUnidad(new Posicion(2, 3), mech);

        faseVek.avanzar(tablero);

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 3)));
    }

    @Test
    public void testSaltadorAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia() {
        Vek saltadorLocal = new Saltador();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), saltadorLocal);
        tablero.colocarUnidad(new Posicion(2, 3), mech);

        faseVek.avanzar(tablero);

        assertTrue(mech.suVidaEsIgualA(3));
    }

    @Test
    public void test18AvisponAnunciaSuAtaqueEnFaseVek() {
        Vek avisponLocal = new Avispon();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), avisponLocal);
        tablero.colocarUnidad(new Posicion(2, 4), mech);

        faseVek.avanzar(tablero);

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 4)));
    }

    @Test
    public void test18AvisponAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia() {
        Vek avisponLocal = new Avispon();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), avisponLocal);
        tablero.colocarUnidad(new Posicion(2, 4), mech);

        faseVek.avanzar(tablero);

        assertTrue(mech.suVidaEsIgualA(3));
    }

    @Test
    public void testLuciernagaAnunciaSuAtaqueEnFaseVek() {
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));
    }

    @Test
    public void testLuciernagaAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia() {
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);

        assertTrue(mech.suVidaEsIgualA(3));
    }

    @Test
    public void testAtaquesAnunciadosDelaLuciernagaSeEjecutanEnFaseDeResolucion() {
        Vek luciernaga = new Luciernaga();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.avanzar(tablero);
        faseResolucion.avanzar(tablero);
        assertTrue(mech.suVidaEsIgualA(2));
    }

    @Test
    public void testAtaqueAnunciadosDelAvisponSeEjecutaEnFaseDeResolucion() {
        Vek avisponLocal = new Avispon();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), avisponLocal);
        tablero.colocarUnidad(new Posicion(2, 4), mech);

        faseVek.avanzar(tablero);
        faseResolucion.avanzar(tablero);
        assertTrue(mech.suVidaEsIgualA(2));
    }

    @Test
    public void testAtaquesAnunciadosDelSaltadorSeEjecutanEnFaseDeResolucion() {
        Vek saltadorLocal = new Saltador();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(2, 2), saltadorLocal);
        tablero.colocarUnidad(new Posicion(2, 4), mech);

        faseVek.avanzar(tablero);
        faseResolucion.avanzar(tablero);
        assertTrue(mech.suVidaEsIgualA(1));
    }

    @Test
    public void test23AtaqueDeVekASeEjecutaAntesQueElDeVekBSiguiendoElOrdenAnunciado() {
        Vek luciernagaA = new Luciernaga();
        Vek avisponB = new Avispon();
        Mech mech = combate();

        tablero.colocarUnidad(new Posicion(0, 2), luciernagaA);
        tablero.colocarUnidad(new Posicion(4, 2), avisponB);
        tablero.colocarUnidad(new Posicion(3, 2), mech);

        faseVek.avanzar(tablero);
        faseResolucion.avanzar(tablero);
        assertTrue(mech.suVidaEsIgualA(0));
    }

    @Test
    public void test24LuciernagaAtacaAUnVekYLoDejaContaminadoHaciendoleElDobleDeDanio() {
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero = TableroTestFactory.crearConTerrenoEn(posicionAtacada, new Acido());
        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, luciernaga2);

        tablero.atacar(posicionAtacante, posicionAtacada);
        tablero.atacar(posicionAtacante, posicionAtacada);

        assertTrue(luciernaga2.suVidaEsIgualA(0));
    }

    @Test
    public void test25LuciernagaAtacaAUnMechYLoDejaContaminadoHaciendoleElDobleDeDanio() {
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, mech1);

        tablero.atacar(posicionAtacante, posicionAtacada);
        tablero.atacar(posicionAtacante, posicionAtacada);

        assertTrue(mech1.suVidaEsIgualA(0));
    }

    @Test
    public void testVekTerrestreTerminaEnAcidoYQuedaContaminadoRecibiendoElDobleDeDanioAlSerAtacado() {
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero = TableroTestFactory.crearConTerrenoEn(posicionAtacada, new Acido());
        tablero.colocarUnidad(posicionAtacante, mech2);
        tablero.colocarUnidad(posicionAtacada, saltador);

        tablero.atacar(posicionAtacante, posicionAtacada);

        assertTrue(saltador.suVidaEsIgualA(0));
    }

    @Test
    public void testMechTerminaEnAcidoYQuedaContaminadoRecibiendoElDobleDeDanioAlSerAtacado() {
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero = TableroTestFactory.crearConTerrenoEn(posicionAtacada, new Acido());
        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, mech1);

        tablero.atacar(posicionAtacante, posicionAtacada);

        assertTrue(mech1.suVidaEsIgualA(1));
    }

    @Test
    public void test28VekAvisponTerminaEnAcidoYNoQuedaContaminadoRecibiendoElDanioNormalAlSerAtacado() {
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero = TableroTestFactory.crearConTerrenoEn(posicionAtacada, new Acido());
        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, avispon);

        tablero.atacar(posicionAtacante, posicionAtacada);

        assertTrue(avispon.suVidaEsIgualA(1));
    }
}