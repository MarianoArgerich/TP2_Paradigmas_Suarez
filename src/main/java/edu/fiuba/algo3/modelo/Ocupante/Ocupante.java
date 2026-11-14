package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Ocupante {

    public abstract boolean puedeIngresar();

    public abstract boolean detieneDisparo();

    public abstract void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Mapa mapa);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }

    public abstract void atacar(Posicion origen, Posicion objetivo, Tablero tablero);

    public abstract void deshacerMovimientoDesde(Casilla casilla, Mapa mapa);

    public abstract void moverDesde(Casilla casilla, Direccion direccion, int distancia, Mapa mapa);
}
