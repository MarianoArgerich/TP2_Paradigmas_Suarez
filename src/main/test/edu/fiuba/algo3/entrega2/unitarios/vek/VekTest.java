package edu.fiuba.algo3.entrega2.unitarios.vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import org.junit.jupiter.api.BeforeEach;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
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


    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicion posicion = new Posicion(i, j);

                if (i == 3 && j == 2) {
                    mapa.add(new Casilla(posicion, new Acido()));
                } else {
                    mapa.add(new Casilla(posicion));
                }
            }
        }
        tablero = new Tablero(mapa);
        luciernaga1 = new Luciernaga();
        mech1 = new Combate();
        saltador = new Saltador();
        avispon = new Avispon();
        luciernaga2 = new Luciernaga();
        mech2 = new Canion();

    }


    //verificar si es unitario
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

    //verificar si es unitario
    @Test
    public void test24LuciernagaAtacaAUnVekYLoDejaContaminadoHaciendoleElDobleDeDanio(){
        //arrange
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, luciernaga2);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);
        tablero.atacar(posicionAtacante, posicionAtacada);

        //assert
        assertTrue(luciernaga2.suVidaEsIgualA(0), "El vek atacado por la luciernaga debe recibir el doble de daño al estar contaminado");

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

        tablero.colocarUnidad(posicionAtacante, luciernaga1);
        tablero.colocarUnidad(posicionAtacada, mech1);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);


        //assert
        assertTrue(mech1.suVidaEsIgualA(1), "El mech atacado por la luciernaga debe recibir el doble de daño al estar contaminado");
    }

    @Test
    public void testVekAvisponTerminaEnAcidoYNoQuedaContaminadoRecibiendoElDanioNormalAlSerAtacado() {
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







/*

    @Test
    public void test01AlEmpujarUnVek1ContraOtroVek2ElVek1NoSeMueve(){}

    @Test
    public void test02AlEmpujarUnVekContraOtroVekSeAplicaElDañoDeColision(){}

    @Test
    public void test03AlEmpujarUnVekContraOtroUnMechElVekNoSeMueve(){}

    @Test
    public void test04AlEmpujarUnVekContraOtraUnMechSeAplicaElDañoDeColision(){}

    @Test
    public void test05AlEmpujarUnVekContraUnObstaculoLaUnidadNoSeMueve(){}

    @Test
    public void test06AlEmpujarUnVekContraUnObstaculoSeAplicaElDañoDeColision(){}



    @Test
    public void test09AlColocarseUnVekSobreLaCasillaDeEmergenciaBloqueaLaAparicionDelNuevoVek(){}

    @Test
    public void test10AlColocarseUnVekSobreLaCasillaDeEmergenciaRecibeUnoDeDaño(){}
*/
    @Test
    public void testLuciernagaEsEmpujadaAlAguaYSuCasillaSeVacia() {
        posicionAgua = new Posicion(2,3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertFalse(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
    }

    @Test
    public void testLuciernagaEsEmpujadaAlAguaYSeMuere() {
        posicionAgua = new Posicion(2,3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(luciernaga.suVidaEsIgualA(0));
    }

    @Test
    public void testSaltadorEsEmpujadaAlAguaYSuCasillaSeVacia() {
        posicionAgua = new Posicion(2,3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertFalse(tablero.unidadEstaEn(saltador, new Posicion(2, 2)));
    }

    @Test
    public void testSaltadorEsEmpujadaAlAguaYSeMuere() {
        posicionAgua = new Posicion(2,3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(saltador.suVidaEsIgualA(0));
    }


    @Test
    public void testAvisponEsEmpujadaAlAgua() {
        posicionAgua = new Posicion(2,3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Avispon avispon = new Avispon();
        tablero.colocarUnidad(new Posicion(2, 2), avispon);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(tablero.unidadEstaEn(avispon, new Posicion(2, 3)));
    }

    @Test
    public void testAvisponEsEmpujadaAlAguaYNoSeMuere() {
        posicionAgua = new Posicion(2,3);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Avispon avispon = new Avispon();
        tablero.colocarUnidad(new Posicion(2, 2), avispon);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(avispon.suVidaEsIgualA(2));
    }
}
/*
    @Test
    public void test14LaIADeLosVekAplicaLasReglasDePrioridadEnOrden() {}

    @Test
    public void test15AlEmpujarAUnVekSuAtaqueSeDesplazaConEl(){}
        //puede ser un vek cualquiera segun Joaquin

    @Test
    public void test16AlEmpujarAUnVekSuAtaqueSeDesplazaConElApuntandoAOtroVekDañandolo(){}
        //puede ser un vek cualquiera segun Joaquin

    @Test
    public void test17LuciernagaAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia(){}

    @Test
    public void test18AvisponAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia(){}

    @Test
    public void test19SaltadorAnunciaSuAtaqueEnFaseVekSinAplicarDanioTodavia(){}

    @Test
    public void test20AtaquesAnunciadosDelaLuciernagaSeEjecutanEnFaseDeResolucion(){}

    @Test
    public void test21AtaquesAnunciadosDelAvisponSeEjecutanEnFaseDeResolucion(){}

    @Test
    public void test22AtaquesAnunciadosDelSaltadorSeEjecutanEnFaseDeResolucion(){}

    @Test
    public void test23AtaqueDeVekASeEjecutaAntesQueElDeVekBSiguiendoElOrdenAnunciado(){}


    @Test
    public void test26ContaminacionDeLuciernagaNoSeAcumulaAlImpactarVariasVecesAUnVek(){}


    @Test
    public void test27ContaminacionDeLuciernagaNoSeAcumulaAlImpactarVariasVecesAUnMech(){}




*/

