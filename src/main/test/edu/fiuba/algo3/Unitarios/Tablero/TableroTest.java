package edu.fiuba.algo3.Unitarios.Tablero;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.CasillaNula;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.excepciones.CoordenadaFueraDelTableroException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertDoesNotThrow(() -> tablero.colocarUnidad(new Posicion(0, 0), new Luciernaga()));
    }

    @Test
    public void tableroValidaPosicionInexistente() {
        tablero = new Tablero(casillas);
        assertThrows(CoordenadaFueraDelTableroException.class, () -> tablero.colocarUnidad(new Posicion(0, 1), new Combate()));
    }

}
