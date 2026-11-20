package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.List;


public class SinOcupante extends Ocupante{


    @Override
    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        visitante.visitar(this, terreno, casillaActual, tablero);
    }

    public void validarEmergencia() {}

    @Override
    public void agregarSiEsAptoParaEmergencia(Posicion posicion, List<Posicion> posiciones) {
        posiciones.add(posicion);
    }

    @Override
    public void aceptar(SegundoVisitante visitante) {
        visitante.visitar(this);
    }

    @Override
    public void verificarPuedeRecibirOcupante() {
    }
}
