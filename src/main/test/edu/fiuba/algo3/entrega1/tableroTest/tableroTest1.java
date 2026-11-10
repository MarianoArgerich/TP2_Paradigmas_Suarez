package edu.fiuba.algo3.entrega1.tableroTest;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.transitable.Acido;
import edu.fiuba.algo3.modelo.casillas.transitable.Agua;
import edu.fiuba.algo3.modelo.casillas.transitable.Casilla;
import edu.fiuba.algo3.modelo.casillas.transitable.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class tableroTest1 {
    @Test
    public void tableroSeCargaConDimensiones8x8() {

        Casilla[][] mapa = new Casilla[8][8];

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa[i][j] = new Suelo(new Posicion(i, j));
            }
        }

        Tablero tablero = new Tablero(mapa);

        assertEquals(64, mapa.length * mapa[0].length);
    }

    @Test
    public void tableroSeCargaConElTerrenoCorrecto() {

        Casilla[][] mapa = new Casilla[2][2];
        mapa[0][0] = new Suelo(new Posicion(0, 0));
        mapa[0][1] = new Agua(new Posicion(0, 1));
        mapa[1][0] = new Acido(new Posicion(1, 0));
        mapa[1][1] = new Montania(new Posicion(1, 1));

        Tablero tablero = new Tablero(mapa);

        boolean coincide = true;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (!tablero.casillaIgualQue(i,j,mapa[i][j])){
                        coincide = false;
                }
            }
        }

        assertTrue(coincide);
    }
}
