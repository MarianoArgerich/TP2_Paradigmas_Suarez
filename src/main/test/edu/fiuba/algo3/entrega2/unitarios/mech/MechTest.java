package edu.fiuba.algo3.entrega2.unitarios.mech;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MechTest {

    private List<Casilla> casillas;
    private Tablero tablero;
    private Mech mech1;
    private Mech mech2;

    @BeforeEach
    public void setUp() {
        casillas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casillas.add(new Casilla(new Posicion(i, j)));
            }
        }
        tablero = new Tablero(casillas);
        mech1 = new Combate();
        mech2 = new Canion();
    }

    @Test
    public void test01AlEmpujarUnMech1ContraOtroMech2ElMech1NoSeMueve() {
        Posicion posicionMech1 = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(new Posicion(2, 3), mech2);

        tablero.empujarDesde(posicionMech1, new Arriba());

        assertTrue(tablero.unidadEstaEn(mech1, posicionMech1));
    }

    @Test
    public void test02AlEmpujarUnMech1ContraOtroMech2SeAplicaElDanioDeColision() {
        Posicion posicionMech1 = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(new Posicion(2, 3), mech2);

        tablero.empujarDesde(posicionMech1, new Arriba());

        assertTrue(mech1.suVidaEsIgualA(2));
        assertTrue(mech2.suVidaEsIgualA(2));
    }
}

/*
    @Test
    public void test03AlEmpujarUnMechContraUnVekElMechNoSeMueve(){}

    @Test
    public void test04AlEmpujarUnMechContraUnVekSeAplicaElDañoDeColision(){}

    @Test
    public void test05AlEmpujarUnMechContraUnObstaculoLaUnidadNoSeMueve(){}

    @Test
    public void test06AlEmpujarUnMechContraUnObstaculoSeAplicaElDañoDeColision(){}


    @Test
    public void test07AlColocarUnMechSobreAcidoQuedaContaminada(){}

    @Test
    public void test08AlAtacarAUnMechContaminadoRecibeElDobleDeDaño


    @Test
    public void test09AlDeshacerElMovimientoDeUnMechQueTerminoSobreAcidoDejaDeEstarContaminado(){}

    @Test
    public void test10MechColocadoEnElAguaNoPuedeAtacar(){}
        //Este test podria dividirse para cada tipo de mech

    @Test
    public void test11AlColocarseUnMechSobreLaCasillaDeEmergenciaBloqueaLaAparicionDelNuevoVek(){}

    @Test
    public void test12AlColocarseUnMechSobreLaCasillaDeEmergenciaRecibeUnoDeDaño(){}





*/

