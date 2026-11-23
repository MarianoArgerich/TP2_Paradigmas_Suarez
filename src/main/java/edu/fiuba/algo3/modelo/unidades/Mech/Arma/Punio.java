package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Golpe;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Punio extends Arma {

    public Punio() {
        this.danio = 2;
    }

    public void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero) {
        Direccion direccionGolpe = origen.calcularDireccionHacia(objetivo);
        origen.validarAdyacenciaCon(objetivo);
        tablero.aplicarEfecto(objetivo, new Golpe(danio, direccionGolpe));
    }
}
/*
        if (!origen.esAdyacenteA(objetivo)){
            throw new AtaqueInvalidoException("El punio solo puede atacar a casillas adyacentes.");
        }
         */