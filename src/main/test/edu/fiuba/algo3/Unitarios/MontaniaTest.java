package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Disparo;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MontaniaTest {

    @Test
    public void testMontaniaNoSeAgregaALaListaDeEdificios() {
        Montania montania = new Montania();
        List<Posicion> posiciones = new ArrayList<>();

        montania.agregarPosicionDeEdificioA(new Posicion(1, 1), posiciones);

        assertTrue(posiciones.isEmpty());
    }

    @Test
    public void testMontaniaNoPermiteRecibirOtroOcupante() {
        Montania montania = new Montania();

        assertThrows(CasillaInvalidaException.class, () -> montania.verificarPuedeRecibirOcupante());
    }

    @Test
    public void testMontaniaEsIndestructibleAnteUnDisparoNoPuedoOcuparSuLugarConUnaUnidad() {
        Casilla casilla = new Casilla(new Posicion(1, 1));
        Montania montania = new Montania();
        casilla.colocarOcupante(montania);
        Tablero tablero = new Tablero(List.of(casilla));

        montania.aceptar(new Disparo(5), new Suelo(), casilla, tablero);

        assertThrows(CasillaInvalidaException.class, () -> casilla.colocarOcupante(combate()));
    }

    @Test
    public void testMontaniaRecibeUnDisparoYNoAfectaLaRedElectrica() {
        Casilla casilla = new Casilla(new Posicion(1, 1));
        Montania montania = new Montania();
        casilla.colocarOcupante(montania);
        Tablero tablero = new Tablero(List.of(casilla));

        montania.aceptar(new Disparo(5), new Suelo(), casilla, tablero);

        assertEquals(5, tablero.getEnergia());
    }

}
