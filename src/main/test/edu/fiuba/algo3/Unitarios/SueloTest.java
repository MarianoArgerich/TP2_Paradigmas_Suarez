package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SueloTest {

    @Test
    public void testSueloNoAfectaNiContaminaAUnidadQueIngresa() {
        Suelo suelo = new Suelo();
        Casilla casilla = new Casilla(new Posicion(1, 1), suelo);
        Luciernaga luciernaga = new Luciernaga();

        suelo.reaccionarAlIngresoDe(luciernaga, casilla);
        luciernaga.recibirDanio(1, casilla);

        assertTrue(luciernaga.suVidaEsIgualA(2));
    }

    @Test
    public void testSueloPermiteQueMechAtaque() {
        Suelo suelo = new Suelo();
        Mech mech = combate();

        assertDoesNotThrow(() -> suelo.verificarAtaqueDe(mech));
    }

    @Test
    public void testSueloPermiteValidarEmergencia() {
        Suelo suelo = new Suelo();

        assertDoesNotThrow(() -> suelo.validarEmergencia());
    }

    @Test
    public void testSueloAgregaPosicionComoCandidataParaEmergenciaSiElOcupanteLoPermite() {
        Suelo suelo = new Suelo();
        Posicion posicion = new Posicion(2, 2);
        List<Posicion> candidatas = new ArrayList<>();

        suelo.agregarCandidataParaEmergenciaA(new SinOcupante(), posicion, candidatas);

        assertTrue(candidatas.contains(posicion));
    }

    @Test
    public void testSueloNoAgregaPosicionComoCandidataSiTieneUnEdificio() {
        Suelo suelo = new Suelo();
        List<Posicion> candidatas = new ArrayList<>();

        suelo.agregarCandidataParaEmergenciaA(new Edificio(), new Posicion(2, 2), candidatas);

        assertTrue(candidatas.isEmpty());
    }

    @Test
    public void testSueloNoAgregaPosicionComoCandidataSiTieneUnaMontania() {
        Suelo suelo = new Suelo();
        List<Posicion> candidatas = new ArrayList<>();

        suelo.agregarCandidataParaEmergenciaA(new Montania(), new Posicion(2, 2), candidatas);

        assertTrue(candidatas.isEmpty());
    }
}
