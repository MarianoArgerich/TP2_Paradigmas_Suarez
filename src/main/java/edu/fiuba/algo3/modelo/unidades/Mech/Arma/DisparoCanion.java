package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Golpe;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Proyectil;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class DisparoCanion extends Arma {


    public DisparoCanion() {
        this.danio = 1;
    }
    //TODO: tell don't ask
    public void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero) {
        if (!origen.estaEnLineaRectaCon(objetivo)) {
            throw new AtaqueInvalidoException("El canion solo puede atacar en linea recta");
        }

        Direccion direccion = origen.calcularDireccionHacia(objetivo);
        tablero.aplicarEfecto(origen.siguienteEn(direccion), new Proyectil(direccion, new Golpe(danio, direccion)));
    }
}