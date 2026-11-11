package edu.fiuba.algo3.modelo.Ocupante;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public interface Ocupante {
    boolean puedeIngresar();
    boolean esUnidad(Unidad unidad);
    boolean detieneDisparo();

    void recibirGolpe(int danio, Direccion direccion, Tablero tablero, Casilla casillaActual);
    void recibirDisparo(int danio, Casilla casillaActual);
    void recibirEmpuje(Casilla casillaOrigen, Casilla casillaDestino);
    void recibirDanioPorColision(Casilla casillaActual);

    boolean estaVacio();
}
