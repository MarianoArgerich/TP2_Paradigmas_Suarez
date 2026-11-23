package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.artilleria;
import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.canion;
import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MechTest {

    @Test
    public void testCombateSeCreaConVidaIgualA3() {
        assertTrue(combate().suVidaEsIgualA(3));
    }

    @Test
    public void testArtilleriaSeCreaConVidaIgualA2() {
        assertTrue(artilleria().suVidaEsIgualA(2));
    }

    @Test
    public void testCanionSeCreaConVidaIgualA3() {
        assertTrue(canion().suVidaEsIgualA(3));
    }

    @Test
    public void testMechPuedeMoverseHasta3Casillas() {
        Mech mech = combate();

        assertDoesNotThrow(() -> mech.verificarRangoDeMovimiento(3));
    }

    @Test
    public void testMechNoPuedeMoverseMasDe3Casillas() {
        Mech mech = combate();

        assertThrows(DistanciaInvalidaException.class, () -> mech.verificarRangoDeMovimiento(4));
    }

    @Test
    public void mechNoPuedeAtacarEnAgua() {
        Mech mech = combate();

        assertThrows(AtaqueInvalidoException.class, () -> mech.verificarAtaqueEnAgua());
    }

    @Test
    public void mechAlRecibirDanioLetalQuedaConVidaCero() {
        Casilla casilla = new Casilla(new Posicion(1, 1));
        Mech mech = combate();
        casilla.colocarOcupante(mech);

        mech.recibirDanio(3, casilla);

        assertTrue(mech.suVidaEsIgualA(0));
    }

    @Test
    public void mechAlMorirVaciaSuCasilla() {
        Casilla casilla = new Casilla(new Posicion(1, 1));
        Mech mech = combate();
        casilla.colocarOcupante(mech);

        mech.recibirDanio(3, casilla);

        assertFalse(casilla.tieneUnidad(mech));
    }
}
