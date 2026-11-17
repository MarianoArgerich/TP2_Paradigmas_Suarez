package edu.fiuba.algo3.modelo.unidades.Vek.IA;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class AtaqueAnunciado {

    private final Vek atacante;
    private final Direccion direccion;

    public AtaqueAnunciado(Vek atacante, Direccion direccion) {
        this.atacante = atacante;
        this.direccion = direccion;
    }

    public boolean amenazaA(Posicion posicionObjetivo, Tablero tablero) {
        return tablero.vekAmenazaEnDireccion(atacante, direccion, posicionObjetivo);
    }

    public void resolver(Tablero tablero) {
        tablero.ejecutarAtaqueVek(atacante, direccion);
    }
}
