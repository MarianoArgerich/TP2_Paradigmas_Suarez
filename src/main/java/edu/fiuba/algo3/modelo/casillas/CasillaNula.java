package edu.fiuba.algo3.modelo.casillas;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class CasillaNula extends Casilla {
    public CasillaNula() {
        super(new Posicion(-1, -1));
    }

    @Override
    public void colocarOcupante(Ocupante ocupante){
        throw new CasillaInvalidaException("No se puede colocar la unidad en una casilla ocupada o no transitable");
    }

    @Override
    public void colocarUnidad(Unidad unidad) {
        throw new CasillaInvalidaException("No se puede colocar la unidad en una casilla ocupada o no transitable");
    }

    @Override
    public void transferirUnidadA(Casilla destino, Unidad unidad) {
        throw new CasillaInvalidaException("No se puede mover la unidad fuera del tablero");
    }

    @Override
    protected void recibirUnidad(Unidad unidad) {
        throw new CasillaInvalidaException("No se puede mover la unidad fuera del tablero");
    }

    @Override
    public boolean estaEn(Posicion otraPosicion) {
        return false;
    }

    @Override
    public boolean detieneDisparo(){
        return true;
    }

    @Override
    public boolean puedeTerminarVek(Vek vek) {
        return false;
    }

    @Override
    public boolean esAptoParaEmergencia() {
        return false;
    }
}
