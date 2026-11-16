package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.redElectrica.RedElectrica;

public class Edificio extends Destruible {

    public Edificio() {
        super(1);
    }

    @Override
    public void recibirDanio(int danio, Casilla casillaActual,Mapa mapa) {
        super.recibirDanio(danio, casillaActual, mapa);
        mapa.reducirRedElectrica();
    }

    @Override
    public void moverDesde(Casilla casilla, Direccion direccion, int distancia, Mapa mapa) {

    }
    @Override
    public void contaminar() {}
}
