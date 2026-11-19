package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;


public class SinOcupante extends Ocupante{

    @Override
    public boolean puedeIngresar() {
        return true;
    }


    @Override
    public boolean detieneDisparo() {
        return false;
    }

    @Override
    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        visitante.visitar(this, terreno, casillaActual, tablero);
    }



    @Override
    public boolean esAptoParaEmergencia() {
        return true;
    }

    @Override
    public void aceptar(SegundoVisitante visitante) {
        visitante.visitar(this);
    }


}
