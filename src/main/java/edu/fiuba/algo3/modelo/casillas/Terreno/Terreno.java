package edu.fiuba.algo3.modelo.casillas.Terreno;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.ExploradorMovimientoVek;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public abstract class Terreno {

    public abstract void reaccionarAlIngresoDe(Unidad unidad, Casilla casilla);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }

    public void verificarAtaqueDe(Unidad unidad) {}


    public void validarEmergencia() {
        throw new CasillaInvalidaException("No se puede anunciar una emergencia en esa casilla");
    }

    public void agregarSiEsAptoParaEmergencia(Ocupante ocupante, Posicion posicion, List<Posicion> posiciones) {}

    public void verificarPuedeIngresar() {}

    public abstract void evaluarMovimientoVek(Vek vek, ExploradorMovimientoVek exploradorMovimientoVek, Casilla casillaActual, Tablero tablero);
}
