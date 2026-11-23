package edu.fiuba.algo3.modelo.posicion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.direccion.SinDireccion;

public class Desplazamiento {
    private final Posicion origen;
    private final Direccion direccion;
    private final int distancia;

    public Desplazamiento(Posicion origen, Direccion direccion, int distancia) {
        this.origen = origen;
        this.direccion = direccion;
        this.distancia = distancia;
    }

    public static Desplazamiento sinMover(Posicion origen) {
        return new Desplazamiento(origen, new SinDireccion(), 0);
    }

    public Posicion destino(){
        Posicion actual = origen;
        for (int i = 0; i< distancia; i++){
            actual = actual.siguienteEn(direccion);
        }
        return actual;
    }

    public void aplicarEn(Tablero tablero) {
        if (distancia > 0){
            tablero.mover(origen, direccion, distancia);
        }
    }
    public boolean esMenorQue(Desplazamiento otro){
        return destino().esMenorQue(otro.destino());
    }
}
