package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableroTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = TableroTestFactory.crearVacio();
    }

    @Test
    public void tableroColocaUnidadEnUnaCoordenadaValida() {
        Posicion posicion = new Posicion(0, 0);
        Luciernaga luciernaga = new Luciernaga();

        tablero.colocarUnidad(posicion, luciernaga);

        assertTrue(tablero.unidadEstaEn(luciernaga, posicion));
    }

    @Test
    public void tableroIgnoraColocarUnidadEnPosicionInexistente() {
        Posicion posicionFuera = new Posicion(8, 8);
        Mech mech = combate();

        tablero.colocarUnidad(posicionFuera, mech);

        assertFalse(tablero.unidadEstaEn(mech, posicionFuera));
    }
}