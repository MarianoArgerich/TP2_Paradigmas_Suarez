package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class EmergenciaAnunciada{

    private final Posicion posicion;
    private final Vek vek;

    public EmergenciaAnunciada(Posicion posicion,Vek vek){
        this.posicion = posicion;
        this.vek = vek;
    }

    public boolean estaEn(Posicion posicion){
        return (this.posicion == posicion);
    }

}
