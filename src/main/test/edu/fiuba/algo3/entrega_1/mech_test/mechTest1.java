package edu.fiuba.algo3.entrega_1.mech_test;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Suelo;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.ocupantes.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class mechTest1 {

    private Tablero tablero;
    private Unidad mech;

    @BeforeEach
    public void setUp() {
        Casilla[][] mapa = new Casilla[8][8];
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa[i][j] = new Suelo();
            }
        }
        tablero = new Tablero(mapa);
        mech = new Combate();
    }

    @Test
    public void testMechNoPuedeMoverseMasAllaDeSuRango(){
        // Mech en (0, 0). Intenta moverse a (4, 0) solo tiene 3 pasos
        Posicion coordenadas = new Posicion(0,0);
        tablero.colocarUnidad(coordenadas, mech);

        assertThrows(RuntimeException.class, () -> tablero.moverUnidad(new Arriba(), 4, coordenadas, mech));
    }

    @Test
    public void testMechNoPuedeMoverseFueraDelTablero(){
        Posicion coordenadas = new Posicion(7,7);
        tablero.colocarUnidad(coordenadas, mech);

        tablero.moverUnidad(new Arriba(), 3, coordenadas, mech);

        assertEquals(coordenadas, mech.obtenerPosicionActual());

    }

    @Test
    public void testMechNoPuedeAtravesarUnidades(){
        tablero.colocarUnidad(new Posicion(1,1), mech);
        tablero.colocarUnidad(new Posicion(1, 2), new Combate());

        tablero.moverUnidad(new Arriba(), 2, mech.obtenerPosicionActual(), mech);
        assertEquals(new Posicion(1, 1), mech.obtenerPosicionActual());
    }

    @Test
    public void testMechNoPuedeAtravesarObstaculos(){

        Casilla[][] mapa = new Casilla[8][8];
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa[i][j] = new Suelo();
            }
        }
        mapa[1][2] = new Edificio();
        tablero = new Tablero(mapa);

        tablero.colocarUnidad(new Posicion(1,1), mech);

        tablero.moverUnidad(new Arriba(), 2, mech.obtenerPosicionActual(), mech);
        assertEquals(new Posicion(1, 1), mech.obtenerPosicionActual());
    }

}
