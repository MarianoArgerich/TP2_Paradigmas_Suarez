package edu.fiuba.algo3.modelo.casillas.Terreno;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.ExploradorMovimientoVek;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class Suelo extends Terreno {

    @Override
    public void reaccionarAlIngresoDe(Unidad unidad, Casilla casilla) {
    }

    @Override
    public void validarEmergencia() {}

    @Override
    public void agregarCandidataParaEmergenciaA(Ocupante ocupante, Posicion posicion, List<Posicion> posiciones) {
        ocupante.agregarCandidataParaEmergenciaA(posicion, posiciones);
    }

    @Override
    public void evaluarMovimientoVek(Vek vek, ExploradorMovimientoVek exploradorMovimientoVek, Casilla casillaActual, Tablero tablero) {
        exploradorMovimientoVek.aceptarPasoYContinuar(casillaActual, tablero);
    }
}
