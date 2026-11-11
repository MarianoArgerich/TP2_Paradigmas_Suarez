package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.List;

public class Arco extends Arma{

    public Arco() {
        this.danio = 1;
    }

    @Override
    public void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero) {
        if(origen.estaEnArea(objetivo)){
            throw new AtaqueInvalidoException("No se puede atacar a una casilla que este dentro del rango prohibido 3x3");
        }
        if (!origen.estaEnLineaRectaCon(objetivo)) {
            throw new AtaqueInvalidoException("No se puede atacar a una casilla que no este en linea recta");
        }

        tablero.aplicarDisparo(objetivo, danio);

        List<Direccion> direcciones = List.of(new Arriba(), new Abajo(), new Izquierda(), new Derecha());

        for (Direccion direccion : direcciones) {
            tablero.empujarDesde(direccion.calcularSiguientePosicion(objetivo), direccion);
        }

    }

}
