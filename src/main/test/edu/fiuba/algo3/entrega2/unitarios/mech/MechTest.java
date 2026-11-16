package edu.fiuba.algo3.entrega2.unitarios.mech;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MechTest {

    private List<Casilla> mapa = new ArrayList<>();
    private Tablero tablero;
    private Mech mech1;
    private Mech mech2;
    private Vek vek1;
    private Vek vek2;
    private Posicion posicionAgua;

    @BeforeEach
    public void setUp() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa.add( new Casilla(new Posicion(i, j)));
            }
        }
        tablero = new Tablero(mapa);
        mech1 = new Combate();
        mech2 = new Canion();
    }

    @Test
    public void testMechCanionAtacaAMech2YMech2NoSeMuevePorObstaculo(){
        Posicion posicionMech1 = new Posicion(2, 1);
        Posicion posicionMech2 = new Posicion(2, 2);
        Posicion posicionObstaculo = new Posicion(2,0);

        mapa.removeIf(c -> c.estaEn(posicionObstaculo));
        Casilla casillaEdificio = new Casilla(posicionObstaculo);
        casillaEdificio.colocarOcupante(new Edificio());
        mapa.add(casillaEdificio);

        tablero = new Tablero(mapa);
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionMech2, mech2);

        tablero.atacar(posicionMech2, posicionMech1);

        assertTrue(tablero.unidadEstaEn(mech1, posicionMech1));

    }

    @Test
    public void testMechCanionAtacaAMech2YAlChocarConEdificioEsteRecibeDanioPorColision(){
        Posicion posicionMech1 = new Posicion(2, 1);
        Posicion posicionMech2 = new Posicion(2, 2);
        Posicion posicionObstaculo = new Posicion(2,3);

        mapa.removeIf(c -> c.estaEn(posicionObstaculo));
        Casilla casillaEdificio = new Casilla(posicionObstaculo);
        casillaEdificio.colocarOcupante(new Edificio());
        mapa.add(casillaEdificio);

        tablero = new Tablero(mapa);

        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionMech2, mech2);
        tablero.atacar(posicionMech1, posicionMech2);

        assertTrue(casillaEdificio.tieneOcupante(new SinOcupante()));

    }

    @Test
    public void testMechCanionAtacaAMech2YAlChocarConEdificioElMechRecibeDanioPorColision(){
        Posicion posicionMech1 = new Posicion(2, 1);
        Posicion posicionMech2 = new Posicion(2, 2);
        Posicion posicionObstaculo = new Posicion(2,0);

        mapa.removeIf(c -> c.estaEn(posicionObstaculo));
        Casilla casillaEdificio = new Casilla(posicionObstaculo);
        casillaEdificio.colocarOcupante(new Edificio());
        mapa.add(casillaEdificio);

        tablero = new Tablero(mapa);

        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionMech2, mech2);
        tablero.atacar(posicionMech2, posicionMech1);

        assertTrue(mech1.suVidaEsIgualA(1));

    }


    @Test
    public void testAlEmpujarUnMech1ContraOtroMech2ElMech1NoSeMueve(){
        Posicion posicionMech1 = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(new Posicion(2, 3), mech2);

        tablero.empujarDesde(posicionMech1, new Arriba());

        assertTrue(tablero.unidadEstaEn(mech1, posicionMech1));
    }

    @Test
    public void test02AlEmpujarUnMech1ContraOtroMech2SeAplicaElDanioDeColision(){
        Posicion posicionMech1 = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(new Posicion(2, 3), mech2);

        tablero.empujarDesde(posicionMech1, new Arriba());

        assertTrue(mech1.suVidaEsIgualA(2));
        assertTrue(mech2.suVidaEsIgualA(2));
    }

    @Test
    public void test03AlEmpujarUnMechContraUnVekElMechNoSeMueve(){
        Posicion posicionMech1 = new Posicion(2, 2);
        Posicion posicionVek = new Posicion(3,2);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionVek, vek);

        tablero.empujarDesde(posicionMech1, new Derecha());
        assertTrue(tablero.unidadEstaEn(mech1, posicionMech1));

    }

    @Test
    public void test04AlEmpujarUnMechContraUnVekSeAplicaElDañoDeColision(){
        Posicion posicionMech1 = new Posicion(2, 2);
        Posicion posicionVek = new Posicion(3,2);
        Vek vek = new Luciernaga();
        tablero.colocarUnidad(posicionMech1, mech1);
        tablero.colocarUnidad(posicionVek, vek);

        tablero.empujarDesde(posicionMech1, new Derecha());
        assertTrue(mech1.suVidaEsIgualA(2));
    }
  
  @Test
    public void testMechColocadoEnElAguaNoPuedeAtacar() {
        posicionAgua = new Posicion(2,2);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), mech1);
        tablero.colocarUnidad(new Posicion(2, 3), luciernaga);

        assertThrows(AtaqueInvalidoException.class,
                () -> tablero.atacar(new Posicion(2, 2), new Posicion(2, 3)));
        //este ultimo assert mepa que ta demas
        assertTrue(luciernaga.suVidaEsIgualA(3));
    }

}
