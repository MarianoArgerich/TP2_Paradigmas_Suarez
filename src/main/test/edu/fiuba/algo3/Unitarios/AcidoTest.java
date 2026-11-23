package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcidoTest {

    @Test
    public void testAcidoAlContaminarAUnVekTerrestreDuplicaElDanioQueRecibe() {
        Acido acido = new Acido();
        Casilla casilla = new Casilla(new Posicion(1, 1), acido);
        Luciernaga luciernaga = new Luciernaga();

        acido.reaccionarAlIngresoDe(luciernaga, casilla);
        luciernaga.recibirDanio(1, casilla);

        assertTrue(luciernaga.suVidaEsIgualA(1));
    }

    @Test
    public void testAcidoAlContaminarAUnMechDuplicaElDanioQueRecibe() {
        Acido acido = new Acido();
        Casilla casilla = new Casilla(new Posicion(1, 1), acido);
        Mech mech = combate();

        acido.reaccionarAlIngresoDe(mech, casilla);
        mech.recibirDanio(1, casilla);

        assertTrue(mech.suVidaEsIgualA(1));
    }

    @Test
    public void testAcidoNoContaminaAUnVekVolador() {
        Acido acido = new Acido();
        Casilla casilla = new Casilla(new Posicion(1, 1), acido);
        Avispon avispon = new Avispon();

        acido.reaccionarAlIngresoDe(avispon, casilla);
        avispon.recibirDanio(1, casilla);

        assertTrue(avispon.suVidaEsIgualA(1));
    }

    @Test
    public void testAcidoDejaEjecutarAtaqueAUnMech() {
        Acido acido = new Acido();
        Mech mech = combate();

        assertDoesNotThrow(() -> acido.verificarAtaqueDe(mech));
    }

    @Test
    public void testAcidoDejaEjecutarAtaqueAUnVekTerrestre() {
        Acido acido = new Acido();
        Luciernaga luciernaga = new Luciernaga();

        assertDoesNotThrow(() -> acido.verificarAtaqueDe(luciernaga));
    }

    @Test
    public void testAcidoDejaEjecutarAtaqueAUnVekVolador() {
        Acido acido = new Acido();
        Avispon avispon = new Avispon();

        assertDoesNotThrow(() -> acido.verificarAtaqueDe(avispon));
    }

    @Test
    public void testAcidoNoPermiteAnunciarEmergencia() {
        Acido acido = new Acido();

        assertThrows(CasillaInvalidaException.class, acido::validarEmergencia);
    }

    @Test
    public void testAcidoNoSeAgregaComoCandidataParaEmergencia() {
        Acido acido = new Acido();
        List<Posicion> candidatas = new ArrayList<>();

        acido.agregarCandidataParaEmergenciaA(new SinOcupante(), new Posicion(1, 1), candidatas);

        assertTrue(candidatas.isEmpty());
    }
}