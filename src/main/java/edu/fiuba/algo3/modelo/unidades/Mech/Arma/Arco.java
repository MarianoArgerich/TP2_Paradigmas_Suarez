package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Arco implements Arma{

    private int danio = 1;

    @Override
    public void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero) {
        if (estaDentroDelRangoProhibido3x3(origen, objetivo)) {
            throw new AtaqueInvalidoException("No se puede atacar a una casilla que este dentro del rango prohibido 3x3");
        }
        if (!estaEnLineaRecta(origen, objetivo)){
            throw new RuntimeException("No se puede atacar a una casilla que no este en linea recta");
        }
        tablero.aplicarDisparo(objetivo, danio);

        Direccion arriba = new Arriba();
        Direccion abajo = new Abajo();
        Direccion izquierda = new Izquierda();
        Direccion derecha = new Derecha();

        tablero.empujarDesde(arriba.calcularSiguientePosicion(objetivo), arriba);
        tablero.empujarDesde(abajo.calcularSiguientePosicion(objetivo), abajo);
        tablero.empujarDesde(izquierda.calcularSiguientePosicion(objetivo), izquierda);
        tablero.empujarDesde(derecha.calcularSiguientePosicion(objetivo), derecha);

    }

    private boolean estaDentroDelRangoProhibido3x3(Posicion origen, Posicion objetivo) {
        int diferenciaX = Math.abs(origen.getX() - objetivo.getX());
        int diferenciaY = Math.abs(origen.getY() - objetivo.getY());

        return (diferenciaX <= 1 && diferenciaY <= 1);
    }

    private boolean estaEnLineaRecta(Posicion origen, Posicion objetivo) {
        return (origen.getX() == objetivo.getX() && origen.getY() != objetivo.getY() || origen.getY() == objetivo.getY() && origen.getX() != objetivo.getX());
    }

}
