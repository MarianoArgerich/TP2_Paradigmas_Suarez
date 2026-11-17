package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;

public abstract class Ocupante {

    public abstract boolean puedeIngresar();

    public abstract boolean detieneDisparo();

    public abstract void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Mapa mapa);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }


    public boolean esMech() {
        return false;
    }

    public boolean esVek() {
        return false;
    }

    public boolean esEdificio() {
        return false;
    }



    public void reaccionarAlTerreno(Terreno terreno, Casilla casilla) {}
    public void verificarAtaqueEn(Terreno terreno) {}

    public boolean esAptoParaEmergencia(){
        return false;
    }

    public abstract void aceptar(SegundoVisitante visitante);
}
