package edu.fiuba.algo3.modelo.ocupantes;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Edificio extends Casilla{

    private int vida = 1;

    public Edificio(Posicion posicion) {
        super(posicion);
    }

    @Override
    public void validarPaso(){
        throw new RuntimeException("No se puede atravesar un Edificio");
    }

    @Override
    public void ocupar(Unidad unidad){
        throw new RuntimeException("casilla invalida");
    }

    @Override
    public void recibirEmpuje(Casilla casillaOrigen){
        casillaOrigen.recibirDanoPorColision();
    }
}
