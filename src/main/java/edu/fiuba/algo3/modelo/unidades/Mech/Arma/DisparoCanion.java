package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Golpe;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class DisparoCanion extends Arma {


    public DisparoCanion() {
        this.danio = 1;
    }

    public void ejecutarAtaque(Posicion origen, Posicion objetivo, Mapa mapa) {
        if (!origen.estaEnLineaRectaCon(objetivo)) {
            throw new AtaqueInvalidoException("El canion solo puede atacar en linea recta");
        }

        Direccion direccion = origen.calcularDireccionHacia(objetivo);
        Posicion actual = origen.siguienteEn(direccion);

        while (!mapa.detieneDisparoEn(actual)){
            actual = actual.siguienteEn(direccion);
        }
        mapa.aplicarEfecto(actual,new Golpe( danio, direccion));
    }
}