package edu.fiuba.algo3.entrega1.tableroTest;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
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

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                int I = i;
                int J = j;
                assertDoesNotThrow(() -> tablero.colocarUnidad(new Posicion(I, J), new Combate()));
            }
        }
    }

    @Test
    public void tableroSeCargaConSuelo() {
        Casilla suelo = new Casilla(new Posicion(0, 0), new Suelo());

        List<Casilla> casillas = List.of(suelo);
        Tablero tablero = new Tablero(casillas);

        assertDoesNotThrow(() -> tablero.colocarUnidad(new Posicion(0 , 0), new Combate()));
    }
}
