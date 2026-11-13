package edu.fiuba.algo3.modelo.Tablero;


import edu.fiuba.algo3.modelo.Visitante.Disparo;
import edu.fiuba.algo3.modelo.Visitante.Empuje;
import edu.fiuba.algo3.modelo.Visitante.Golpe;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.List;

public class Tablero {

    private final Mapa mapa;

    public Tablero(List<Casilla> casillas){
        this.mapa = new Mapa(casillas);
    }

    public void colocarUnidad(Posicion coordenadas, Unidad unidad) {
        mapa.colocarUnidad(coordenadas, unidad);
    }

    public void mover(Posicion origen, Direccion direccion, int distancia){
        mapa.mover(origen, direccion, distancia);
    }

    public void aplicarGolpe(Posicion posicion, int danio, Direccion direccion) {
        mapa.aplicarEfecto(posicion, new Golpe(danio, direccion));
    }

    public void aplicarDisparo(Posicion objetivo,int danio){
        mapa.aplicarEfecto(objetivo, new Disparo(danio));
    }

    public void empujarDesde(Posicion origen, Direccion direccion) {
        mapa.aplicarEfecto(origen, new Empuje(direccion));
    }

    public void atacar(Posicion origen, Posicion objetivo) {
        mapa.atacar(origen, objetivo, this);
    }

    public boolean detieneDisparoEn(Posicion posicion) {
        return mapa.detieneDisparoEn(posicion);
    }

    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return mapa.unidadEstaEn(unidad, posicion);
    }

    public void deshacerMovimiento(Posicion posicionActual) {
        mapa.deshacerMovimiento(posicionActual);
    }
}
