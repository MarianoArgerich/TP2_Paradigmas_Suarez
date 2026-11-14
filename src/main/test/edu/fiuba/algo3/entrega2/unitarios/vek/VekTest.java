package edu.fiuba.algo3.entrega2.unitarios.vek;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VekTest {

    private List<Casilla> mapa;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        mapa = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa.add(new Casilla(new Posicion(i, j), new Suelo()));
            }
        }
        tablero = new Tablero(mapa);
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




/*

    @Test
    public void test01AlEmpujarUnVek1ContraOtroVek2ElVek1NoSeMueve(){

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

    @Test
    public void test11LuciernagaEsEmpujadaAlAguaYSeMuere() {}

    @Test
    public void test12SaltadorEsEmpujadaAlAguaYSeMuere() {}

    @Test
    public void test13AvisponEsEmpujadaAlAguaYNoSeMuere() {}
*/


    @Test
    public void test14LaIADeLosVekAplicaLasReglasDePrioridadEnOrden() {
        Edificio edificio = new Edificio();
        colocarObstaculo(new Posicion(4, 5), edificio);

        Vek luciernaga = new Luciernaga();
        Mech mech = new Combate();

        tablero.colocarUnidad(new Posicion(2, 2), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 3), mech);

        tablero.ejecutarFaseVek();

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(4, 2)));
        assertTrue(luciernaga.tieneAtaqueAnunciado());
        assertTrue(luciernaga.estaApuntandoA(new Posicion(4, 5)));
    }

    @Test
    public void test14bSiNoPuedeAtacarEdificioPriorizaAtacarUnMech(){
        colocarObstaculo(new Posicion(7, 7), new Edificio());

        Vek luciernaga = new Luciernaga();
        Mech mech = new Combate();

        tablero.colocarUnidad(new Posicion(1, 1), luciernaga);
        tablero.colocarUnidad(new Posicion(2, 4), mech);

        tablero.ejecutarFaseVek();

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(2, 1)));
        assertTrue(luciernaga.tieneAtaqueAnunciado());
        assertTrue(luciernaga.estaApuntandoA(new Posicion(2, 4)));
    }

    @Test
    public void test14cSiNoPuedeAtacarSeAcercaAlEdificioMasCercanoSinAnunciarAtaqueYEvitandoAguaYAcido(){
        cambiarTerreno(new Posicion(3,1), new Agua());
        cambiarTerreno(new Posicion(2, 2), new Acido());
        colocarObstaculo(new Posicion(3, 5), new Edificio());
        colocarObstaculo(new Posicion(7,7), new Edificio());

        Vek luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(1, 1), luciernaga);

        tablero.ejecutarFaseVek();

        assertTrue(tablero.unidadEstaEn(luciernaga, new Posicion(1, 3)));
        assertFalse(luciernaga.tieneAtaqueAnunciado());

    }


/*
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
    public void test23AtaqueDeVekASeEjecutaAntesQueElDeVekBSiguiendoElOrdenAnunciado()

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
