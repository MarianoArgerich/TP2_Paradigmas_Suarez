package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EdificioTest {

    @Test
    public void testEdificioAgregaSuPosicionALaListaDeEdificios() {
        Edificio edificio = new Edificio();
        Posicion posicion = new Posicion(2, 3);
        List<Posicion> posiciones = new ArrayList<>();

        edificio.agregarPosicionDeEdificioA(posicion, posiciones);

        assertTrue(posiciones.contains(posicion));
    }

    @Test
    public void testEdificioNoPermiteRecibirOtroOcupante() {
        Edificio edificio = new Edificio();

        assertThrows(CasillaInvalidaException.class, () -> edificio.verificarPuedeRecibirOcupante());
    }

    @Test
    public void testEdificioAlRecibirDanioSeDestruye() {
        Posicion posicion = new Posicion(2, 3);
        Casilla casilla = new Casilla(posicion);
        Edificio edificio = new Edificio();
        casilla.colocarOcupante(edificio);
        Tablero tablero = new Tablero(List.of(casilla));

        edificio.recibirDanio(1, casilla, tablero);

        assertTrue(tablero.obtenerPosicionesDeEdificios().isEmpty());
    }

    @Test
    public void testEdificioAlRecibirDanioYDestruirseYReduceLaRedElectricaEn1() {
        Posicion posicion = new Posicion(2, 3);
        Casilla casilla = new Casilla(posicion);
        Edificio edificio = new Edificio();
        casilla.colocarOcupante(edificio);
        Tablero tablero = new Tablero(List.of(casilla));

        edificio.recibirDanio(1, casilla, tablero);

        assertEquals(4, tablero.getEnergia());
    }

    @Test
    public void testEdificioAlRecibirDanioYDestruirseAceptaNuevoOcupante() {
        Posicion posicion = new Posicion(2, 3);
        Casilla casilla = new Casilla(posicion);
        Edificio edificio = new Edificio();
        casilla.colocarOcupante(edificio);
        Tablero tablero = new Tablero(List.of(casilla));

        edificio.recibirDanio(1, casilla, tablero);

        assertDoesNotThrow(() -> casilla.colocarOcupante(combate()));
    }
}