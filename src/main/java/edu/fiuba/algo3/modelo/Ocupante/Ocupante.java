package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.List;

public abstract class Ocupante {

    public abstract void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Tablero tablero);

    public abstract void aceptar(SegundoVisitante visitante);

    public void verificarPuedeRecibirOcupante() {
        throw new CasillaInvalidaException("No se puede colocar la unidad en una casilla ocupada o no transitable");
    }

    public void validarEmergencia() {
        throw new CasillaInvalidaException("No se puede anunciar una emergencia en esa casilla");
    }

    public void agregarCandidataParaEmergenciaA(Posicion posicion, List<Posicion> posiciones) {}



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }


    public void reaccionarAlTerreno(Terreno terreno, Casilla casilla) {}

    public void verificarAtaqueEn(Terreno terreno) {}
    //TODO: esta raro el si tiene
    //Solucionado cambiando el nombre a agregarPosicionDeEdificioA(posiciones)
    public void agregarPosicionDeEdificioA(Posicion posicion, List<Posicion> posiciones) {}
}
