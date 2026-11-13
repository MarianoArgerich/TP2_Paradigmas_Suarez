package edu.fiuba.algo3.modelo.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class ImpactoDeEmpuje implements Visitante {
    private final Casilla origen;

    public ImpactoDeEmpuje(Casilla origen) {
        this.origen = origen;
    }

    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla destino, Mapa mapa) {

        origen.aceptar(new DanioPorColision(), mapa);
        destino.aceptar(new DanioPorColision(), mapa);
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla destino, Mapa mapa) {

        origen.transferirOcupanteA(destino);
    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla destino, Mapa mapa) {
        origen.aceptar(new DanioPorColision(), mapa);
        destino.aceptar(new DanioPorColision(), mapa);
    }
}
