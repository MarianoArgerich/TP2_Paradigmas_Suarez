package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SinOcupanteTest {

    @Test
    public void testSinOcupantePermiteRecibirOcupante() {
        SinOcupante sinOcupante = new SinOcupante();

        assertDoesNotThrow(() -> sinOcupante.verificarPuedeRecibirOcupante());
    }

    @Test
    public void testSinOcupanteValidaEmergencia() {
        SinOcupante sinOcupante = new SinOcupante();

        assertDoesNotThrow(() -> sinOcupante.validarEmergencia());
    }

    @Test
    public void testSinOcupanteSeAgregaComoCandidataParaEmergencia() {
        SinOcupante sinOcupante = new SinOcupante();
        Posicion posicion = new Posicion(3, 3);
        List<Posicion> candidatas = new ArrayList<>();

        sinOcupante.agregarCandidataParaEmergenciaA(posicion, candidatas);

        assertTrue(candidatas.contains(posicion));
    }

    @Test
    public void testSinOcupanteNoSeAgregaALaListaDeEdificios() {
        SinOcupante sinOcupante = new SinOcupante();
        List<Posicion> edificios = new ArrayList<>();

        sinOcupante.agregarPosicionDeEdificioA(new Posicion(3, 3), edificios);

        assertTrue(edificios.isEmpty());
    }
}
