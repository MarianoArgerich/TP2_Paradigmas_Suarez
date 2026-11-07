package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.ocupantes.Ocupante;

public abstract class Casilla {

    private Ocupante ocupante = null;

    public boolean estaOcupada() {
        return ocupante != null;
    }

    public void ocupar(Ocupante ocupante){
        this.ocupante = ocupante;
    }
}
