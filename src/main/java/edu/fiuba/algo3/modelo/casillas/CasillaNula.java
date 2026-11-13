package edu.fiuba.algo3.modelo.casillas;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class CasillaNula extends Casilla {
    public CasillaNula() {
        super(new Posicion(-1, -1));
    }

    @Override
    public void colocarOcupante(Ocupante ocupante){
        throw new CasillaInvalidaException("No se puede colocar la unidad en una casilla ocupada o no transitable");
    }

    @Override
    public boolean estaEn(Posicion otraPosicion) {
        return false;
    }

    @Override
    public boolean detieneDisparo(){
        return true;
    }
}
