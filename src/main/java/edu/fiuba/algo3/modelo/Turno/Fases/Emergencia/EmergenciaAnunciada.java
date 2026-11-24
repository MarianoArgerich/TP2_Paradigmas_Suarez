package edu.fiuba.algo3.modelo.Turno.Fases.Emergencia;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.EmergenciaVek;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class EmergenciaAnunciada implements AparicionVek {
    private final Posicion posicion;

    private final Vek vek;
    public EmergenciaAnunciada(Posicion posicion,Vek vek){
        this.posicion = posicion;
        this.vek = vek;
    }

    @Override
    public boolean estaEn(Posicion posicion){
        return this.posicion.equals(posicion);
    }

    @Override
    public void resolver(Tablero tablero) {
        tablero.aplicarEfecto(this.posicion, new EmergenciaVek(this.vek));
    }

}
