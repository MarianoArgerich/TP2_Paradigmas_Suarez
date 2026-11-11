package edu.fiuba.algo3.entrega1.tableroTest;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableroTest {
    @Test
    public void tableroSeCargaConDimensiones8x8() {

        List<Casilla> casillas = new ArrayList<>();

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                casillas.add(new Casilla(new Posicion(i, j), new Suelo()));
            }
        }

        Tablero tablero = new Tablero(casillas);

        assertEquals(64, casillas.size());
    }

    @Test
    public void tableroSeCargaConElTerrenoCorrecto() {

        List<Casilla> casillas = new ArrayList<>();


        Casilla suelo = new Casilla(new Posicion(0, 0), new Suelo());
        Casilla agua = new Casilla(new Posicion(0, 1), new Agua());
        Casilla acido = new Casilla(new Posicion(1, 0), new Acido());
        Casilla montania = new Casilla(new Posicion(1, 1), new Suelo(), new Montania());

        casillas.add(suelo);
        casillas.add(agua);
        casillas.add(acido);
        casillas.add(montania);

        Tablero tablero = new Tablero(casillas);

        assertTrue(tablero.casillaIgualQue(new Posicion(0 , 0), suelo));
        assertTrue(tablero.casillaIgualQue(new Posicion(0 , 1), agua));
        assertTrue(tablero.casillaIgualQue(new Posicion(1 , 0), acido));
        assertTrue(tablero.casillaIgualQue(new Posicion(1, 1), montania));
    }
}
