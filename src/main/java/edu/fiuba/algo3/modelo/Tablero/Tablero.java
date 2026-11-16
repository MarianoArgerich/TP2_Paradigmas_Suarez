package edu.fiuba.algo3.modelo.Tablero;


import edu.fiuba.algo3.modelo.Visitante.Disparo;
import edu.fiuba.algo3.modelo.Visitante.Empuje;
import edu.fiuba.algo3.modelo.Visitante.Golpe;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.observador.Observador;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.representacion.TraductorEntidad;
import edu.fiuba.algo3.modelo.representacion.TraductorTerreno;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private final Mapa mapa;
    private final List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador){
        this.observadores.add(observador);
    }

    private void notificarObservadores() {
        observadores.forEach(Observador::actualizar);
    }

    public Tablero(List<Casilla> casillas){
        this.mapa = new Mapa(casillas);
    }

    public void colocarUnidad(Posicion coordenadas, Unidad unidad) {
        mapa.colocarUnidad(coordenadas, unidad);
        notificarObservadores();
    }

    public void mover(Posicion origen, Direccion direccion, int distancia){
        mapa.mover(origen, direccion, distancia);
        notificarObservadores();
    }

    public void atacar(Posicion origen, Posicion objetivo) {
        mapa.atacar(origen, objetivo, this.mapa);
        notificarObservadores();
    }

    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return mapa.unidadEstaEn(unidad, posicion);
    }

    public void deshacerMovimiento(Posicion posicionActual) {
        mapa.deshacerMovimiento(posicionActual);
        notificarObservadores();
    }

    public void traducirCasilla(Posicion posicion, TraductorTerreno traductorTerreno, TraductorEntidad traductorEntidad){
        mapa.traducirCasilla(posicion, traductorTerreno, traductorEntidad);
    }
}
