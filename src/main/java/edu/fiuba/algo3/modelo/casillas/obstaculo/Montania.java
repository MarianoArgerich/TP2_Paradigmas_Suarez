package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;


public class Montania extends Obstaculo {

    @Override
    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        visitante.visitar(this, terreno, casillaActual, mapa);
    }



    @Override
    public void aceptar(SegundoVisitante visitante) {
        visitante.visitar(this);
    }
}
