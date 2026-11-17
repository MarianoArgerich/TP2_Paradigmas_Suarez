package edu.fiuba.algo3.modelo.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.UnidadInvalidaException;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import edu.fiuba.algo3.modelo.unidades.capacidades.Movible;

public class MoverDesdeCasilla implements SegundoVisitante {

    private final Casilla origen;
    private final Direccion direccion;
    private final int distancia;
    private final Mapa mapa;

    public MoverDesdeCasilla(
            Casilla origen,
            Direccion direccion,
            int distancia,
            Mapa mapa
    ) {
        this.origen = origen;
        this.direccion = direccion;
        this.distancia = distancia;
        this.mapa = mapa;
    }

    private void mover(Movible movible) {
        movible.moverDesde(
                this.origen,
                this.direccion,
                this.distancia,
                this.mapa
        );
    }

    @Override
    public void visitar(Mech mech) {
        this.mover(mech);
    }

    @Override
    public void visitar(Vek vek) {
        this.mover(vek);
    }

    @Override
    public void visitar(SinOcupante sinOcupante) {
        throw new UnidadInvalidaException(
                "No hay una unidad en la casilla de origen"
        );
    }

    @Override
    public void visitar(Destruible destruible) {
        throw new UnidadInvalidaException(
                "Un obstaculo no puede moverse"
        );
    }

    @Override
    public void visitar(Montania montania) {
        throw new UnidadInvalidaException(
                "Una montania no puede moverse"
        );
    }
}
