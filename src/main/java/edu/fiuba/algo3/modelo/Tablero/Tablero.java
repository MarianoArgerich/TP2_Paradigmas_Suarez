package edu.fiuba.algo3.modelo.Tablero;


import edu.fiuba.algo3.modelo.Visitante.Disparo;
import edu.fiuba.algo3.modelo.Visitante.Empuje;
import edu.fiuba.algo3.modelo.Visitante.Golpe;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.List;

public class Tablero {

    private final Mapa mapa;

    public Tablero(List<Casilla> casillas){
        this.mapa = new Mapa(casillas);
    }

    public void colocarUnidad(Posicion coordenadas, Unidad unidad) {
        mapa.casillaEn(coordenadas).colocarOcupante(unidad);
    }

    public void moverUnidad(Unidad unidad, Direccion direccion, int distancia) {
        unidad.verificarRangoDeMovimiento(distancia);
        Casilla casillaInicial = mapa.casillaDe(unidad);


        for (int i=1; i<=distancia; i++) {
            Casilla origen = mapa.casillaDe(unidad);
            Casilla destino = mapa.casillaEn(origen.siguienteEn(direccion));

            if (!origen.transferirOcupanteA(destino)) {
                break;
            }
        }

        Casilla casillaFinal = mapa.casillaDe(unidad);
        if (casillaFinal != casillaInicial) {
            casillaInicial.notificarMovimientoA(unidad);
        }
    }

    public void revertirMovimiento(Unidad unidad, Posicion posicionAnterior){
        mapa.casillaDe(unidad).transferirOcupanteA(mapa.casillaEn(posicionAnterior));
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

    public void atacarDesdeUnidad(Unidad unidad, Arma arma, Posicion objetivo) {
        mapa.casillaDe(unidad).ejecutarAtaque(arma, objetivo, this);
    }

    //TODO: Chequear
    public boolean detieneDisparoEn(Posicion posicion) {
        return mapa.esCoordenadaValida(posicion) && mapa.casillaEn(posicion).detieneDisparo();
    }

    //TODO: Chequear
    public boolean esCoordenadaValida(Posicion posicion){
        return mapa.esCoordenadaValida(posicion);
    }

    //TODO: Chequear
    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return mapa.unidadEstaEn(unidad, posicion);
    }
}
