package edu.fiuba.algo3.modelo.Visitante.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Empuje implements Visitante {

    private final Direccion direccion;

    public Empuje(Direccion direccion) {
        this.direccion = direccion;
    }


    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Tablero tablero) {

        Posicion destino = casillaActual.siguienteEn(direccion);
        tablero.aplicarEfecto(destino, new ImpactoDeEmpuje(casillaActual));
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Tablero tablero) {
    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual, Tablero tablero) {
    }

    @Override
    public void visitar(Montania montania, Terreno terreno, Casilla casillaActual, Tablero tablero) {
    }
}
