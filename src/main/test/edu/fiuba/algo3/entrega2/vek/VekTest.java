package edu.fiuba.algo3.entrega2.vek;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.FaseVek;
import edu.fiuba.algo3.modelo.Turno.Fases.Resolucion;
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
    private FaseVek faseVek;
    private Resolucion faseResolucion;


    @BeforeEach
    public void setUp() {
        mapa = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa.add(new Casilla(new Posicion(i, j), new Suelo()));
            }
        }
        tablero = new Tablero(mapa);
        faseVek = new FaseVek();
        faseResolucion = new Resolucion();
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
    public void test15AlEmpujarAUnVekSuAtaqueSeDesplazaConEl(){
    }
        //puede ser un vek cualquiera segun Joaquin

    @Test
    public void test16AlEmpujarAUnVekSuAtaqueSeDesplazaConElApuntandoAOtroVekDañandolo(){
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
    public void test20AtaquesAnunciadosDelaLuciernagaSeEjecutanEnFaseDeResolucion(){
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

    /*
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
