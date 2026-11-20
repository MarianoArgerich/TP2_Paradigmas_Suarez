package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.List;

public class Edificio extends Destruible {

    public Edificio() {
        super(1);
    }


    @Override
    public void aceptar(SegundoVisitante visitante) {
        visitante.visitar(this);
    }

    @Override
    public void recibirDanio(int danio, Casilla casillaActual, Tablero tablero) {
        super.recibirDanio(danio, casillaActual, tablero);
        tablero.reducirRedElectrica();
    }

    @Override
    public void agregarPosicionSiTieneEdificio(Posicion posicion, List<Posicion> posiciones) {
        posiciones.add(posicion);
    }
}
