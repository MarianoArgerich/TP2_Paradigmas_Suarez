package edu.fiuba.algo3.entrega_1.tablero_test;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Acido;
import edu.fiuba.algo3.modelo.casillas.Agua;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Suelo;
import edu.fiuba.algo3.modelo.ocupantes.Montania;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class tablero_test_1 {
    @Test
    public void tableroSeCargaConDimensiones8x8() {

        Casilla[][] mapa = new Casilla[8][8];

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa[i][j] = new Suelo();
            }
        }

        Tablero tablero = new Tablero(mapa);

        assertEquals(64, mapa.length * mapa[0].length);
    }

    @Test
    public void tableroSeCargaConElTerrenoCorrecto() {

        Casilla[][] mapa = new Casilla[2][2];
        mapa[0][0] = new Suelo();
        mapa[0][1] = new Agua();
        mapa[1][0] = new Acido();
        mapa[1][1] = new Montania();

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
