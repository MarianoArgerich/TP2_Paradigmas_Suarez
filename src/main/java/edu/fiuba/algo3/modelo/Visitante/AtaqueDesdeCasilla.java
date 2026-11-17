package edu.fiuba.algo3.modelo.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class AtaqueDesdeCasilla implements SegundoVisitante {
    private final Posicion origen;
    private final Posicion objetivo;
    private final Tablero tablero;
    private final Terreno terreno;

    public AtaqueDesdeCasilla(
            Posicion origen,
            Posicion objetivo,
            Tablero tablero,
            Terreno terreno
    ) {
        this.origen = origen;
        this.objetivo = objetivo;
        this.tablero = tablero;
        this.terreno = terreno;
    }

    @Override
    public void visitar(Mech mech) {
        mech.verificarAtaqueEn(this.terreno);
        mech.atacar(this.origen, this.objetivo, this.tablero);
    }

    @Override
    public void visitar(Vek vek) {
        vek.verificarAtaqueEn(this.terreno);
        vek.atacar(this.origen, this.objetivo, this.tablero);
    }

    @Override
    public void visitar(SinOcupante sinOcupante) {
        throw new AtaqueInvalidoException(
                "No se puede atacar desde una casilla vacia"
        );
    }

    @Override
    public void visitar(Destruible destruible) {
        throw new AtaqueInvalidoException(
                "Un obstaculo no puede atacar"
        );
    }

    @Override
    public void visitar(Montania montania) {
        throw new AtaqueInvalidoException(
                "Una montania no puede atacar"
        );
    }
}
