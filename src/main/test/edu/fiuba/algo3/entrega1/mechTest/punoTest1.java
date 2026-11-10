package edu.fiuba.algo3.entrega1.mechTest;


import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.transitable.Casilla;
import edu.fiuba.algo3.modelo.casillas.transitable.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class punoTest1 {

    private Tablero tablero;
    private Casilla[][] mapa;
    private Mech combate;

    @BeforeEach
    public void setUp() {
        mapa = new Casilla[8][8];
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa[i][j] = new Suelo(new Posicion(i, j));
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

        assertEquals(1, vek.obtenerVidaActual());
    }

    @Test
    public void testPunoCausaDanoAUnidadYLaEmpuja(){

        Posicion ubicacionMech = new Posicion(2,2);
        tablero.colocarUnidad(ubicacionMech, combate);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2,3);
        tablero.colocarUnidad(ubicacionVek, vek);

        combate.atacar(ubicacionVek, tablero);

        assertTrue(vek.estaEn(new Posicion(2, 4)));
    }

    @Test
    public void testPunoGolpeaYColisionaContraEdificio(){
        Posicion ubicacionMech = new Posicion(2,2);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2,3);

        mapa[2][4] = new Edificio(new Posicion(2, 4));
        tablero = new Tablero(mapa);
        tablero.colocarUnidad(ubicacionMech, combate);
        tablero.colocarUnidad(ubicacionVek, vek);

        combate.atacar(ubicacionVek, tablero);

        assertTrue(vek.estaEn(ubicacionVek));

    }

    @Test
    public void testPunoGolpeaEnElBordeDelTableroNoEmpujaNiAplicaChoque(){
        Posicion ubicacionMech = new Posicion(2,6);
        tablero.colocarUnidad(ubicacionMech, combate);

        Vek vek = new Luciernaga();
        Posicion ubicacionVek = new Posicion(2,7);
        tablero.colocarUnidad(ubicacionVek, vek);

        combate.atacar(ubicacionVek, tablero);

        assertTrue(vek.estaEn(ubicacionVek));
    }
}

