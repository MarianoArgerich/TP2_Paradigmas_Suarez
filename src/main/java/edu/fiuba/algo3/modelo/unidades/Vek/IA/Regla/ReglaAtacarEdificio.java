package edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class ReglaAtacarEdificio extends ReglaAtacar {

    @Override
    public void visitar(Destruible destruible) {
        this.confirmarObjetivo();
    }
}
