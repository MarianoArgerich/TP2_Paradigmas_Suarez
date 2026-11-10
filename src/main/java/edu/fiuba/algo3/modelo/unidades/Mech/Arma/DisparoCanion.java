package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class DisparoCanion implements Arma {

    private final int danio = 1;

    @Override
    public void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero) {
        Direccion direccion = origen.calcularDireccionHacia(objetivo);
        tablero.ataqueEnLinea(origen, danio, direccion);
    }
}