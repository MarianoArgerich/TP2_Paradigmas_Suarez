package edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class ReglaAtacarEdificio extends ReglaAtacar {

    @Override
    protected boolean esObjetivoValido(Posicion objetivo, Tablero tablero) {
        return tablero.edificioEn(objetivo);
    }
}
