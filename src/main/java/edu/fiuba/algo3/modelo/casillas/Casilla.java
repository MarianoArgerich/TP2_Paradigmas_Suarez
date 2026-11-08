package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Casilla {

    private Unidad unidad = null;

    private boolean estaOcupada() {
        return unidad != null;
    }

    public void ocupar(Unidad unidad){
        if(estaOcupada()){
            throw new RuntimeException("casilla ocupada");
        }
        this.unidad = unidad;
    }

    public void desocupar() {
        this.unidad = null;
    }
}
