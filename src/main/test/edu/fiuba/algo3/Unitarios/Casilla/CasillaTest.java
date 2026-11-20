package edu.fiuba.algo3.Unitarios.Casilla;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.*;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CasillaTest {

    @Test
    public void casillaCalculaSuPosicionSiguienteEnUnaDireccionCorrectamente() {
        Casilla casilla = new Casilla(new Posicion(2, 3));

        assertEquals(new Posicion(3, 3), casilla.siguienteEn(new Derecha()));
    }

    @Test
    public void noSePuedeColocarOcupanteEnUnaCasillaOcupada() {
        Casilla casilla = new Casilla(new Posicion(0, 0));
        casilla.colocarOcupante(new Edificio());

        assertThrows(CasillaInvalidaException.class, () -> casilla.colocarOcupante(new Saltador()));
    }

    @Test
    public void noSePuedeColocarUnidadEnUnaCasillaOcupada() {
        Casilla casilla = new Casilla(new Posicion(0, 0));
        casilla.colocarOcupante(new Saltador());

        assertThrows(CasillaInvalidaException.class, () -> casilla.colocarOcupante(new Combate()));
    }

    @Test
    public void transferirOcupanteADestinoOcupadoLanzaExcepcion() {
        Casilla origen = new Casilla(new Posicion(0, 0));
        Casilla destino = new Casilla(new Posicion(1, 0));
        origen.colocarOcupante(new Saltador());
        destino.colocarOcupante(new Combate());

        assertThrows(CasillaInvalidaException.class, () -> origen.transferirOcupanteA(destino));
    }

    @Test
    public void transferirUnidadADestinoOcupadoLanzaExcepcion() {
        Casilla origen = new Casilla(new Posicion(0, 0));
        Casilla destino = new Casilla(new Posicion(1, 0));
        Combate mech = new Combate();
        origen.colocarOcupante(mech);
        destino.colocarOcupante(new Saltador());

        assertThrows(CasillaInvalidaException.class, () -> origen.transferirOcupanteA(destino));
    }

    @Test
    public void aplicarEfectoDelAguaMataAlVekTerrestre() {
        Casilla agua = new Casilla(new Posicion(0, 0), new Agua());
        Saltador saltador = new Saltador();
        agua.colocarOcupante(saltador);

        assertTrue(saltador.suVidaEsIgualA(0));
    }
}
