package edu.fiuba.algo3.Unitarios.Tablero;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.CasillaNula;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableroTest {

    private List<Casilla> casillas;
    private Tablero tablero;

    @BeforeEach
    void setUp(){
        casillas = new ArrayList<>();
    }

    @Test
    public void tableroSeCargaConUnaCoordenadaValida() {

        casillas.add(new Casilla(new Posicion(0, 0), new Suelo()));


        tablero = new Tablero(casillas);
        assertTrue(tablero.esCoordenadaValida(new Posicion(0, 0)));
    }

    @Test
    public void tableroValidaPosicionInexistente() {
        tablero = new Tablero(casillas);
        assertFalse(tablero.esCoordenadaValida(new Posicion(-1, -1)));
    }

}
