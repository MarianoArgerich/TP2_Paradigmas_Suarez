package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Punio extends Arma {

    public Punio() {
        this.danio = 2;
    }

    public void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero) {
        Direccion direccionGolpe = origen.calcularDireccionHacia(objetivo);
        tablero.aplicarGolpe(objetivo, danio, direccionGolpe);
    }
}
