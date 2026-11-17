package edu.fiuba.algo3.entrega2.vek;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.Emergencia;
import edu.fiuba.algo3.modelo.Turno.Fases.FaseVek;
import edu.fiuba.algo3.modelo.Turno.Fases.Resolucion;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VekTest {


    private Tablero tablero;
    private List<Casilla> mapa = new ArrayList<>();
    private Luciernaga luciernaga1;
    private Luciernaga luciernaga2;
    private Mech mech1;
    private Mech mech2;
    private Saltador saltador;
    private Avispon avispon;
    private Posicion posicionAgua;
    private FaseVek faseVek;
    private Resolucion faseResolucion;


    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa.add(new Casilla(new Posicion(i, j), new Suelo()));
            }
        }
        tablero = new Tablero(mapa);
        faseVek = new FaseVek();
        faseResolucion = new Resolucion();
        luciernaga1 = new Luciernaga();
        mech1 = new Combate();
        saltador = new Saltador();
        avispon = new Avispon();
        luciernaga2 = new Luciernaga();
        mech2 = new Canion();
    }

    private void colocarObstaculo(Posicion posicion, Ocupante obstaculo) {
        mapa.removeIf(casilla -> casilla.estaEn(posicion));
        Casilla casilla = new Casilla(posicion, new Suelo());
        casilla.colocarOcupante(obstaculo);
        mapa.add(casilla);
        tablero = new Tablero(mapa);
    }

    private void cambiarTerreno(Posicion posicion, Terreno terreno) {
        mapa.removeIf(casilla -> casilla.estaEn(posicion));
        mapa.add(new Casilla(posicion, terreno));
        tablero = new Tablero(mapa);
    }

    @Test
    public void testAlEmpujarUnVek1ContraOtroVek2ElVek1NoSeMueve(){
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), luciernaga2);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());
        assertTrue(tablero.unidadEstaEn(luciernaga1, new Posicion(2,2)));
    }

    @Test
    public void testAlEmpujarLuciernagaContraOtroAvisponSeAplicaElDanioDeColision(){
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), avispon);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(luciernaga1.suVidaEsIgualA(2));
        assertTrue(avispon.suVidaEsIgualA(1));
    }

    @Test
    public void testAlEmpujarLuciernagaContraOtroSaltadorSeAplicaElDanioDeColision(){
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), saltador);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(luciernaga1.suVidaEsIgualA(2));
        assertTrue(saltador.suVidaEsIgualA(1));
    }

    @Test
    public void testAlEmpujarUnVekContraOtroUnMechElVekNoSeMueve(){
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), mech1);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(tablero.unidadEstaEn(luciernaga1, new Posicion(2,2)));
    }

    @Test
    public void testAlEmpujarUnVekContraOtraUnMechSeAplicaElDañoDeColision(){
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);
        tablero.colocarUnidad(new Posicion(2, 3), mech1);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(luciernaga1.suVidaEsIgualA(2));
        assertTrue(mech1.suVidaEsIgualA(2));
    }

    @Test
    public void testAlEmpujarUnVekContraUnEdificioLaUnidadNoSeMueve(){
        colocarObstaculo(new Posicion(2, 3), new Edificio());
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(tablero.unidadEstaEn(luciernaga1, new Posicion(2,2)));
    }

    @Test
    public void testAlEmpujarUnVekContraUnMontaniaLaUnidadNoSeMueve(){
        colocarObstaculo(new Posicion(2, 3), new Montania());
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(tablero.unidadEstaEn(luciernaga1, new Posicion(2,2)));
    }

    @Test
    public void testAlEmpujarUnVekContraUnEdificioSeAplicaElDanioDeColision(){
        colocarObstaculo(new Posicion(2, 3), new Edificio());
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(luciernaga1.suVidaEsIgualA(2));
    }

    @Test
    public void testAlEmpujarUnVekContraUnMontaniaSeAplicaElDaniooDeColision(){
        colocarObstaculo(new Posicion(2, 3), new Montania());
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga1);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(luciernaga1.suVidaEsIgualA(2));
    }

    @Test
    public void test09AlColocarseUnVekSobreLaCasillaDeEmergenciaBloqueaLaAparicionDelNuevoVek(){
        Posicion posicionEmergencia = new Posicion(4, 4);
        Vek vekBloqueador = new Luciernaga();
        Vek vekEntrante = new Saltador();

        FaseVek faseVekDeterministica = new FaseVek();
        Emergencia faseEmergencia = new Emergencia();

    }


    @Test
    public void test10AlColocarseUnVekSobreLaCasillaDeEmergenciaRecibeUnoDeDaño(){
        //TODO: cuando la ia y fases esten terminadas
    }

    @Test
    public void test11LuciernagaEsEmpujadaAlAguaYSuCasillaSeVacia() {
        posicionAgua = new Posicion(2, 3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertFalse(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
    }

    @Test
    public void test11bLuciernagaEsEmpujadaAlAguaYSeMuere() {
        posicionAgua = new Posicion(2, 3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(luciernaga.suVidaEsIgualA(0));
    }

    @Test
    public void test12SaltadorEsEmpujadaAlAguaYSuCasillaSeVacia() {
        posicionAgua = new Posicion(2, 3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertFalse(tablero.unidadEstaEn(saltador, new Posicion(2, 2)));
    }

    @Test
    public void test12bSaltadorEsEmpujadaAlAguaYSeMuere() {
        posicionAgua = new Posicion(2, 3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(saltador.suVidaEsIgualA(0));
    }

    @Test
    public void test13AvisponEsEmpujadaAlAgua() {
        posicionAgua = new Posicion(2, 3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Avispon avispon = new Avispon();
        tablero.colocarUnidad(new Posicion(2, 2), avispon);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(tablero.unidadEstaEn(avispon, new Posicion(2, 3)));
    }

    @Test
    public void test13bAvisponEsEmpujadaAlAguaYNoSeMuere() {
        posicionAgua = new Posicion(2, 3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Avispon avispon = new Avispon();
        tablero.colocarUnidad(new Posicion(2, 2), avispon);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(avispon.suVidaEsIgualA(2));
    }

    @Test
    public void test14LaIADeLosVekAplicaLasReglasDePrioridadEnOrden() {
        colocarObstaculo(new Posicion(4, 5), new Edificio());

        Vek luciernaga = new Luciernaga();
        Mech mech = new Combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 3), mech);

        faseVek.ejecutar(List.of(new Posicion(2, 2)),
                List.of(luciernaga),
                List.of(new Posicion(2, 3)),
                List.of(mech),
                tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(4, 2)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(4, 5)));
    }

    @Test
    public void test14bSiNoPuedeAtacarEdificioAtacaMechYDesempataPorMenorFilaYColumna() {
        colocarObstaculo(new Posicion(7, 7), new Edificio());

        Vek luciernaga = new Luciernaga();
        Mech mech = new Combate();

        tablero.colocarUnidad(new Posicion(1, 1), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 4), mech);

        faseVek.ejecutar(List.of(new Posicion(1, 1)), List.of(luciernaga),
                List.of(new Posicion(2, 4)), List.of(mech), tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(2, 0)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 4)));
    }

    @Test
    public void test14cSiNoPuedeAtacarSeAcercaAlEdificioMasCercanoSinAnunciarAtaqueYEvitandoAguaYAcido() {
        cambiarTerreno(new Posicion(3, 1), new Agua());
        cambiarTerreno(new Posicion(2, 2), new Acido());
        colocarObstaculo(new Posicion(3, 5), new Edificio());
        colocarObstaculo(new Posicion(7, 4), new Edificio());

        Vek luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(1, 1), luciernaga);

        faseVek.ejecutar(List.of(new Posicion(1, 1)), List.of(luciernaga),
                List.of(), List.of(), tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(1, 3)));
        assertFalse(tablero.hayAtaquesAnunciados());
    }

    @Test
    public void test15AlEmpujarAUnVekSuAtaqueSeDesplazaConEl() {
        Vek luciernaga = new Luciernaga();
        Mech mech = new Combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.ejecutar(List.of(new Posicion(2, 2)), List.of(luciernaga), List.of(new Posicion(2, 5)), List.of(mech), tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));

        tablero.empujarDesde(new Posicion(2, 2), new Derecha());

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(3, 2)));
        assertFalse(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(3, 5)));
    }
        //puede ser un vek cualquiera segun Joaquin

    @Test
    public void test16AlEmpujarAUnVekSuAtaqueSeDesplazaConElApuntandoAOtroMechDañandolo() {
        Vek luciernaga = new Luciernaga();
        Mech mech = new Combate();
        Mech mech2 = new Combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);
        tablero.colocarUnidad(new Posicion(4, 5), mech2);
        faseVek.ejecutar(List.of(new Posicion(2, 2)), List.of(luciernaga), List.of(new Posicion(2, 5), new Posicion(4, 5)), List.of(mech, mech2), tablero);

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));

        tablero.empujarDesde(new Posicion(2, 2), new Derecha());
        tablero.empujarDesde(new Posicion(3, 2), new Derecha());

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(4, 2)));
        assertFalse(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));
        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(4, 5)));

        faseResolucion.ejecutar(List.of(new Posicion(4, 2)), List.of(luciernaga), List.of(new Posicion(2, 5), new Posicion(4, 5)), List.of(mech, mech2), tablero);
        assertTrue(mech2.suVidaEsIgualA(2));
    }
        //puede ser un vek cualquiera segun Joaquin

    @Test
    public void test17LuciernagaAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia(){
        Vek luciernaga = new Luciernaga();
        Mech mech = new Combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.ejecutar(List.of(new Posicion(2, 2)), List.of(luciernaga),
                List.of(new Posicion(2, 5)), List.of(mech), tablero);

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 5)));
        assertTrue(mech.suVidaEsIgualA(3));
    }
    /*
    @Test
    public void test18AvisponAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia(){}

    @Test
    public void test19SaltadorAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia(){}
    */
    @Test
    public void testAtaquesAnunciadosDelaLuciernagaSeEjecutanEnFaseDeResolucion() {
        Vek luciernaga = new Luciernaga();
        Mech mech = new Combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 5), mech);

        faseVek.ejecutar(List.of(new Posicion(2, 2)), List.of(luciernaga),
                List.of(new Posicion(2, 5)), List.of(mech), tablero);

        faseResolucion.ejecutar(List.of(new Posicion(2, 0)), List.of(luciernaga),
                List.of(new Posicion(2, 5)), List.of(mech), tablero);


        assertTrue(mech.suVidaEsIgualA(2));
    }

    /*
    @Test
    public void test21AtaquesAnunciadosDelAvisponSeEjecutanEnFaseDeResolucion(){}

    @Test
    public void test22AtaquesAnunciadosDelSaltadorSeEjecutanEnFaseDeResolucion(){}
    */
    @Test
    public void test23AtaqueDeVekASeEjecutaAntesQueElDeVekBSiguiendoElOrdenAnunciado() {
    }


    @Test
    public void test24LuciernagaAtacaAUnVekYLoDejaContaminadoHaciendoleElDobleDeDanio(){
        //arrange
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        cambiarTerreno(new Posicion(3, 2), new Acido());

        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, luciernaga2);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);
        tablero.atacar(posicionAtacante, posicionAtacada);

        //assert
        assertTrue(luciernaga2.suVidaEsIgualA(0), "El vek atacado por la luciernaga debe recibir el doble de daño al estar contaminado");

    }


    @Test
    public void test25LuciernagaAtacaAUnMechYLoDejaContaminadoHaciendoleElDobleDeDanio() {
        //arrange
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, mech1);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);
        tablero.atacar(posicionAtacante, posicionAtacada);


        //assert
        assertTrue(mech1.suVidaEsIgualA(0), "El mech atacado por la luciernaga debe recibir el doble de daño al estar contaminado");

    }

    @Test
    public void testVekTerrestreTerminaEnAcidoYQuedaContaminadoRecibiendoElDobleDeDanioAlSerAtacado() {
        //arrange
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero.colocarUnidad(posicionAtacante, mech2);
        tablero.colocarUnidad(posicionAtacada, saltador);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);

        //assert
        assertTrue(saltador.suVidaEsIgualA(1), "El vek atacado por la otra unidad debe recibir el doble de daño al estar contaminado");
    }

    @Test
    public void testMechTerminaEnAcidoYQuedaContaminadoRecibiendoElDobleDeDanioAlSerAtacado() {
        //arrange
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        cambiarTerreno(new Posicion(3, 2), new Acido());

        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, mech1);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);


        //assert
        assertTrue(mech1.suVidaEsIgualA(1), "El mech atacado por la luciernaga debe recibir el doble de daño al estar contaminado");
    }

    @Test
    public void test28VekAvisponTerminaEnAcidoYNoQuedaContaminadoRecibiendoElDanioNormalAlSerAtacado() {
        //arrange
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, avispon);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);

        //assert
        assertTrue(avispon.suVidaEsIgualA(1), "El vek atacado por la otra unidad debe recibir el daño normal al no estar contaminado");

    }
}
