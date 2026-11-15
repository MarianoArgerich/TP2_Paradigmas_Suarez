package edu.fiuba.algo3.entrega2.unitarios.mech;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MechTest {
    private Mech mech1;

    @BeforeEach
    public void setUp() {
        mech1 = new Combate();
    }

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
    public void test01AlEmpujarUnMech1ContraOtraMech2ElMech1NoSeMueve(){}

    @Test
    public void test02AlEmpujarUnMech1ContraOtraOtroMech2SeAplicaElDañoDeColision(){}

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
*/
    @Test
    public void test10MechColocadoEnElAguaNoPuedeAtacar() {
        Tablero tablero = tableroConAguaEn(new Posicion(2, 2));
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), mech1);
        tablero.colocarUnidad(new Posicion(2, 3), luciernaga);

        assertThrows(AtaqueInvalidoException.class,
                () -> tablero.atacar(new Posicion(2, 2), new Posicion(2, 3)));
        assertTrue(luciernaga.suVidaEsIgualA(3));
    }
/*
    @Test
    public void test11AlColocarseUnMechSobreLaCasillaDeEmergenciaBloqueaLaAparicionDelNuevoVek(){}

    @Test
    public void test12AlColocarseUnMechSobreLaCasillaDeEmergenciaRecibeUnoDeDaño(){}

*/
}
