package edu.fiuba.algo3.Unitarios.Casilla;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasillaTest {

    @Test
    public void casillaSeCreaCorrectamente() {
        Casilla suelo = new Casilla(new Posicion(0, 0), new Suelo());

        assertTrue(suelo.tieneTerreno(new Suelo()));
        assertTrue(suelo.tieneOcupante(new SinOcupante()));
    }

}
