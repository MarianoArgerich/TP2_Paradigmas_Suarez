package edu.fiuba.algo3.modelo.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Empuje implements Visitante {

    private final Direccion direccion;

    public Empuje(Direccion direccion) {
        this.direccion = direccion;
    }


    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Mapa mapa) {

        Posicion destino = casillaActual.siguienteEn(direccion);
        mapa.aplicarEfecto(destino, new ImpactoDeEmpuje(casillaActual));
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Mapa mapa) {
    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual, Mapa mapa) {
    }
}
