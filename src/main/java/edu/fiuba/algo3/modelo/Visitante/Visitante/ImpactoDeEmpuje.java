package edu.fiuba.algo3.modelo.Visitante.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class ImpactoDeEmpuje implements Visitante {
    private final Casilla origen;

    public ImpactoDeEmpuje(Casilla origen) {
        this.origen = origen;
    }

    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla destino, Tablero tablero) {

        origen.aceptar(new DanioPorColision(), tablero);
        destino.aceptar(new DanioPorColision(), tablero);
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla destino, Tablero tablero) {
        try {
            origen.transferirOcupanteA(destino);
            destino.aplicarEfectoDelTerreno();
        }
        catch (CasillaInvalidaException e) {
            return;
        }
    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla destino, Tablero tablero) {
        origen.aceptar(new DanioPorColision(), tablero);
        destino.aceptar(new DanioPorColision(), tablero);
    }

    @Override
    public void visitar(Montania montania, Terreno terreno, Casilla destino, Tablero tablero) {
        origen.aceptar(new DanioPorColision(), tablero);
    }
}
