package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
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
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {

    }

    @Override
    public void deshacerMovimientoDesde(Casilla casilla, Mapa mapa) {

    }

    @Override
    public void moverDesde(Casilla casilla, Direccion direccion, int distancia, Mapa mapa) {
        throw new UnidadInvalidaException("No hay una unidad en la casilla de origen");
    }
}
