package edu.fiuba.algo3.entrega2.unitarios.vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VekTest {


    private Tablero tablero;
    private List<Casilla> mapa = new ArrayList<>();
    private Luciernaga luciernaga;
    private Mech mech;
    private Saltador saltador;
    private Avispon avispon;


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
        luciernaga = new Luciernaga();
        mech = new Combate();
        saltador = new Saltador();
        avispon = new Avispon();

    }

    @Test
    public void test25LuciernagaAtacaAUnMechYLoDejaContaminado() {
        //arrange
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero.colocarUnidad(posicionAtacante, luciernaga);
        tablero.colocarUnidad(posicionAtacada, mech);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);

        //assert
        assert (mech.estaContaminado());

    }

    @Test
    public void test24LuciernagaAtacaAUnVekYLoDejaContaminado(){
        //arrange
        Posicion posicionAtacante = new Posicion(2, 2);
        Posicion posicionAtacada = new Posicion(3, 2);

        tablero.colocarUnidad(posicionAtacante, luciernaga);
        tablero.colocarUnidad(posicionAtacada, saltador);

        //act
        tablero.atacar(posicionAtacante, posicionAtacada);

        //assert
        assert (saltador.estaContaminado());
    }


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
    public void test09AlColocarseUnVekSobreLaCasillaDeEmergenciaBloqueaLaAparicionDelNuevoVek(){}

    @Test
    public void test10AlColocarseUnVekSobreLaCasillaDeEmergenciaRecibeUnoDeDaño(){}

    @Test
    public void test11LuciernagaEsEmpujadaAlAguaYSeMuere() {}

    @Test
    public void test12SaltadorEsEmpujadaAlAguaYSeMuere() {}

    @Test
    public void test13AvisponEsEmpujadaAlAguaYNoSeMuere() {}

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
    public void test23AtaqueDeVekASeEjecutaAntesQueElDeVekBSiguiendoElOrdenAnunciado()


    @Test
    public void test26ContaminacionDeLuciernagaNoSeAcumulaAlImpactarVariasVecesAUnVek(){}


    @Test
    public void test27ContaminacionDeLuciernagaNoSeAcumulaAlImpactarVariasVecesAUnMech(){}




*/

