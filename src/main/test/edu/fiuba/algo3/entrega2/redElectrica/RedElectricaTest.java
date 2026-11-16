package edu.fiuba.algo3.entrega2.redElectrica;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.redElectrica.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RedElectricaTest {

    private Tablero tablero;
    private List<Casilla> mapa = new ArrayList<>();
    private Mech combate;
    private Posicion posicionMech;
    private Posicion posicionEdificio;

    @BeforeEach
    public void setUp() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa.add( new Casilla(new Posicion(i, j)));
            }
        }
        tablero = new Tablero(mapa);
        combate = new Combate();
        posicionMech = new Posicion(2, 2);
        posicionEdificio = new Posicion(2, 3);

        mapa.removeIf(c -> c.estaEn(posicionEdificio));

        Casilla casillaEdificio = new Casilla(posicionEdificio);
        casillaEdificio.colocarOcupante(new Edificio());
        mapa.add(casillaEdificio);
    }

    @Test
    public void testAlHacerDañoAUnEdificioLaRedElectricaSeReduceEn1() {

        tablero = new Tablero(mapa);
        tablero.colocarUnidad(posicionMech, combate);


        tablero.atacar(posicionMech, posicionEdificio);
        assertTrue(tablero.tieneEnergia(4));
    }

    @Test
    public void testAlFallarElAtaqueAUnEdificioLaRedElectricaNoPierdeEnergia(){

        Posicion posicionErrada = new Posicion(2, 1);

        tablero = new Tablero(mapa);
        tablero.colocarUnidad(posicionMech, combate);


        tablero.atacar(posicionMech, posicionErrada);
        assertTrue(tablero.tieneEnergia(5));
    }

    @Test
    public void testUnVekEmpujadoContraUnEdificioReduceLaRedElectricaEn1(){
        Vek vek = new Luciernaga();
        Posicion posicionVek = new Posicion(2, 1);
        Posicion posicionEdificioChocable = new Posicion(2,0);

        mapa.removeIf(c -> c.estaEn(posicionEdificioChocable));

        Casilla edificioChocable = new Casilla(posicionEdificioChocable);
        edificioChocable.colocarOcupante(new Edificio());
        mapa.add(edificioChocable);

        tablero = new Tablero(mapa);
        tablero.colocarUnidad(posicionVek, vek);
        tablero.colocarUnidad(posicionMech, combate);

        tablero.atacar(posicionMech,posicionVek);

        assertTrue(tablero.tieneEnergia(4));

    }


    @Test
    public void testAtacarUnEdificioPreviamenteDestruidoNoReduceLaEnergia(){
        Mech mech2 = new Combate();
        Posicion posicionMech2 = new Posicion(3,3);
        tablero = new Tablero(mapa);
        tablero.colocarUnidad(posicionMech, combate);
        tablero.colocarUnidad(posicionMech2, mech2);

        tablero.atacar(posicionMech2, posicionEdificio);
        tablero.atacar(posicionMech, posicionEdificio);

        assertTrue(tablero.tieneEnergia(4));

    }

    @Test
    public void testAtacarDosEdificiosEnElMismoTurnoReduceLaEnergiaEn2(){
        Posicion posicionEdificio2 = new Posicion(3,2);

        mapa.removeIf(c -> c.estaEn(posicionEdificio2));

        Casilla edificio2 = new Casilla(posicionEdificio2);
        edificio2.colocarOcupante(new Edificio());
        mapa.add(edificio2);

        Mech mech2 = new Combate();
        Posicion posicionMech2 = new Posicion(3,3);
        tablero = new Tablero(mapa);
        tablero.colocarUnidad(posicionMech, combate);
        tablero.colocarUnidad(posicionMech2, mech2);

        tablero.atacar(posicionMech,posicionEdificio);
        tablero.atacar(posicionMech2,posicionEdificio2);

        assertTrue(tablero.tieneEnergia(3));

    }
    /*
    // realizar cuando Vek este implementado
    @Test
    public void testUnAtaqueDeVekAUnEdificioReduceLaRedElectrica(){
    }

    //Realizar cuando la Derrota este implementada
    @Test
    public void testDestruirCincoEdificiosLanzaLaExcepcionDeDerrota(){
    }
     */
}





