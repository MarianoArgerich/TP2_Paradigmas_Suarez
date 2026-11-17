package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.UnidadInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;


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
    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        visitante.visitar(this, terreno, casillaActual, mapa);
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
