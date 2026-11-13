package edu.fiuba.algo3.entrega1.mechTest;


import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
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

public class PunioTest {

    private Tablero tablero;
    private List<Casilla> mapa = new ArrayList<>();
    private Mech combate;

    @BeforeEach
    public void setUp() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa.add( new Casilla(new Posicion(i, j)));
            }
        }
        tablero = new Tablero(mapa);
        combate = new Combate();
    }

    @Test
    public void testPunoCausa2DeDanoAUnidad(){

        Posicion ubicacionMech = new Posicion(2,2);
        tablero.colocarUnidad(ubicacionMech, combate);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2,3);
        tablero.colocarUnidad(ubicacionVek, vek);

        combate.atacar(ubicacionVek, tablero);

        assertTrue(vek.suVidaEsIgualA(1));
    }

    @Test
    public void testPunoCausaDanoAUnidadYLaEmpuja(){

        Posicion ubicacionMech = new Posicion(2,2);
        tablero.colocarUnidad(ubicacionMech, combate);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2,3);
        tablero.colocarUnidad(ubicacionVek, vek);

        combate.atacar(ubicacionVek, tablero);

        assertTrue(tablero.unidadEstaEn(vek, new Posicion(2, 4)));
    }

    @Test
    public void testPunioGolpeaYColisionaContraEdificio(){
        Posicion ubicacionMech = new Posicion(2,2);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2,3);

        mapa.removeIf(casilla -> casilla.estaEn(new Posicion(2, 4)));
        Casilla casilla  = new Casilla(new Posicion(2, 4));
        casilla.colocarOcupante(new Edificio());

        mapa.add(casilla);
        tablero = new Tablero(mapa);
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        combate.atacar(ubicacionVek, tablero);

        assertFalse(tablero.unidadEstaEn(vek, ubicacionVek));
    }

    @Test
    public void testPunoGolpeaEnElBordeDelTableroNoEmpujaNiAplicaChoque(){
        Posicion ubicacionMech = new Posicion(2,6);
        tablero.colocarUnidad(ubicacionMech, combate);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2,7);
        tablero.colocarUnidad(ubicacionVek, vek);

        combate.atacar(ubicacionVek, tablero);

        assertTrue(tablero.unidadEstaEn(vek, ubicacionVek));
    }
}

