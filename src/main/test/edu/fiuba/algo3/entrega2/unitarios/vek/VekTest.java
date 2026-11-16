package edu.fiuba.algo3.entrega2.unitarios.vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class VekTest {
    private Tablero tableroConAguaEn(Posicion posicionAgua) {
        List<Casilla> casillas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicion posicion = new Posicion(i, j);
                casillas.add(posicion.equals(posicionAgua)
                        ? new Casilla(posicion, new Agua())
                        : new Casilla(posicion));
            }
        }
        return new Tablero(casillas);
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
    public void test07AlColocarUnVekTerrestreSobreAcidoQuedaContaminada(){}

    @Test
    public void test08AlAtacarAUnVekContaminadoRecibeElDobleDeDaño(){}

    @Test
    public void test09AlColocarseUnVekSobreLaCasillaDeEmergenciaBloqueaLaAparicionDelNuevoVek(){}

    @Test
    public void test10AlColocarseUnVekSobreLaCasillaDeEmergenciaRecibeUnoDeDaño(){}
*/
    @Test
    public void testLuciernagaEsEmpujadaAlAguaYSuCasillaSeVacia() {
        Tablero tablero = tableroConAguaEn(new Posicion(2, 3));
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertFalse(tablero.unidadEstaEn(luciernaga, new Posicion(2, 2)));
    }

    @Test
    public void testLuciernagaEsEmpujadaAlAguaYSeMuere() {
        Tablero tablero = tableroConAguaEn(new Posicion(2, 3));
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(luciernaga.suVidaEsIgualA(0));
    }

    @Test
    public void testSaltadorEsEmpujadaAlAguaYSuCasillaSeVacia() {
        Tablero tablero = tableroConAguaEn(new Posicion(2, 3));
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertFalse(tablero.unidadEstaEn(saltador, new Posicion(2, 2)));
    }

    @Test
    public void testSaltadorEsEmpujadaAlAguaYSeMuere() {
        Tablero tablero = tableroConAguaEn(new Posicion(2, 3));
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(saltador.suVidaEsIgualA(0));
    }


    @Test
    public void testAvisponEsEmpujadaAlAgua() {
        Tablero tablero = tableroConAguaEn(new Posicion(2, 3));
        Avispon avispon = new Avispon();
        tablero.colocarUnidad(new Posicion(2, 2), avispon);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(tablero.unidadEstaEn(avispon, new Posicion(2, 3)));
    }

    @Test
    public void testAvisponEsEmpujadaAlAguaYNoSeMuere() {
        Tablero tablero = tableroConAguaEn(new Posicion(2, 3));
        Avispon avispon = new Avispon();
        tablero.colocarUnidad(new Posicion(2, 2), avispon);

        tablero.empujarDesde(new Posicion(2, 2), new Arriba());

        assertTrue(avispon.suVidaEsIgualA(2));
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
    public void test24LuciernagaAtacaAUnVekYLoDejaContaminado(){}


    @Test
    public void test25LuciernagaAtacaAUnMechYLoDejaContaminado(){}


    @Test
    public void test26ContaminacionDeLuciernagaNoSeAcumulaAlImpactarVariasVecesAUnVek(){}


    @Test
    public void test27ContaminacionDeLuciernagaNoSeAcumulaAlImpactarVariasVecesAUnMech(){}


    @Test
    public void test28AvisponTerminaEnSobreAcidoYNoQuedaContaminado(){}

*/
}
