package edu.fiuba.algo3.modelo.Visitante;


import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeDeshacerMovimiento;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

public class DeshacerMovimientoDesdeCasilla implements SegundoVisitante {

    private final Casilla casillaActual;
    private final Mapa mapa;

    public DeshacerMovimientoDesdeCasilla(
            Casilla casillaActual,
            Mapa mapa
    ) {
        this.casillaActual = casillaActual;
        this.mapa = mapa;
    }

    @Override
    public void visitar(Mech mech) {
        mech.deshacerMovimientoDesde(this.casillaActual, this.mapa);
    }

    @Override
    public void visitar(Vek vek) {
        throw new NoSePuedeDeshacerMovimiento(
                "Un Vek no puede deshacer su movimiento"
        );
    }

    @Override
    public void visitar(SinOcupante sinOcupante) {
        throw new NoSePuedeDeshacerMovimiento(
                "No hay un Mech en la casilla"
        );
    }

    @Override
    public void visitar(Destruible destruible) {
        throw new NoSePuedeDeshacerMovimiento(
                "Un obstaculo no puede deshacer un movimiento"
        );
    }

    @Override
    public void visitar(Montania montania) {
        throw new NoSePuedeDeshacerMovimiento(
                "Una montania no puede deshacer un movimiento"
        );
    }
}
