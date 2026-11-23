package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AguaTest {

    @Test
    public void testAguaMataAUnVekTerrestre() {
        Agua agua = new Agua();
        Casilla casilla = new Casilla(new Posicion(1, 1), agua);
        Luciernaga luciernaga = new Luciernaga();
        casilla.colocarOcupante(luciernaga);

        assertTrue(luciernaga.suVidaEsIgualA(0));
    }

    @Test
    public void testAguaAlMatarAUnVekTerrestreVaciaSuCasilla() {
        Agua agua = new Agua();
        Casilla casilla = new Casilla(new Posicion(1, 1), agua);
        Luciernaga luciernaga = new Luciernaga();
        casilla.colocarOcupante(luciernaga);

        assertFalse(casilla.tieneUnidad(luciernaga));
    }

    @Test
    public void testAguaNoMataAUnVekVolador() {
        Agua agua = new Agua();
        Casilla casilla = new Casilla(new Posicion(1, 1), agua);
        Avispon avispon = new Avispon();

        agua.reaccionarAlIngresoDe(avispon, casilla);

        assertTrue(avispon.suVidaEsIgualA(2));
    }

    @Test
    public void testAguaNoDejaEjecutarAtaqueAUnMech() {
        Agua agua = new Agua();
        Mech mech = combate();

        assertThrows(AtaqueInvalidoException.class, () -> agua.verificarAtaqueDe(mech));
    }


    @Test
    public void testAguaDejaEjecutarAtaqueAUnVekVolador() {
        Agua agua = new Agua();
        Avispon avispon = new Avispon();

        assertDoesNotThrow(() -> agua.verificarAtaqueDe(avispon));
    }



    @Test
    public void testAguaNoPermiteAnunciarEmergencia() {
        Agua agua = new Agua();

        assertThrows(CasillaInvalidaException.class, () -> agua.validarEmergencia());
    }

    @Test
    public void testAguaNoSeAgregaComoCandidataParaEmergencia() {
        Agua agua = new Agua();
        List<Posicion> candidatas = new ArrayList<>();

        agua.agregarCandidataParaEmergenciaA(new SinOcupante(), new Posicion(1, 1), candidatas);

        assertTrue(candidatas.isEmpty());
    }
}
