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

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(tablero.esCoordenadaValida(new Posicion(0, 0)));
        assertTrue(tablero.esCoordenadaValida(new Posicion(7, 7)));

        assertFalse(tablero.esCoordenadaValida(new Posicion(8, 8)));
    }

    @Test
    public void tableroSeCargaConElTerrenoCorrecto() {
        Casilla suelo = new Casilla(new Posicion(0, 0), new Suelo());
        Casilla agua = new Casilla(new Posicion(0, 1), new Agua());
        Casilla acido = new Casilla(new Posicion(1, 0), new Acido());
        Casilla montania = new Casilla(new Posicion(1, 1), new Suelo(), new Montania());

        List<Casilla> casillas = List.of(suelo, agua, acido, montania);
        Tablero tablero = new Tablero(casillas);

        assertTrue(tablero.esCoordenadaValida(new Posicion(0 , 0)));
        assertTrue(tablero.esCoordenadaValida(new Posicion(0 , 1)));
        assertTrue(tablero.esCoordenadaValida(new Posicion(1 , 0)));
        assertTrue(tablero.esCoordenadaValida(new Posicion(1, 1)));

        assertTrue(suelo.esDelTerreno(new Suelo()));
        assertTrue(agua.esDelTerreno(new Agua()));
        assertTrue(acido.esDelTerreno(new Acido()));
        assertTrue(montania.esDelOcupante(new Montania()));
    }
}
