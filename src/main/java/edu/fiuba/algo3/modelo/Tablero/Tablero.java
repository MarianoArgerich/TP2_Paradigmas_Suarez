package edu.fiuba.algo3.modelo.Tablero;


import edu.fiuba.algo3.modelo.Visitante.Disparo;
import edu.fiuba.algo3.modelo.Visitante.Empuje;
import edu.fiuba.algo3.modelo.Visitante.Golpe;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Ataque.DisparoAcido;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.AtaqueAnunciado;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private final Mapa mapa;
    private final List<AtaqueAnunciado> ataquesAnunciados = new ArrayList<>();

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

    public void aplicarDisparoAcido(Posicion objetivo, int danio) {
        mapa.aplicarEfecto(objetivo, new DisparoAcido(danio));
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

    public boolean edificioEn(Posicion posicion) {return mapa.edificioEn(posicion);}

    public boolean mechEn(Posicion posicion) {return mapa.mechEn(posicion);}

    public boolean vekEn(Posicion posicion) {return mapa.vekEn(posicion);}

    public boolean posicionValida(Posicion posicion) {
        return mapa.casillaEn(posicion).estaEn(posicion);
    }

    public void anunciarAtaque(Vek atacante, Direccion direccion) {
        this.ataquesAnunciados.add(new AtaqueAnunciado(atacante, direccion));

    }

    public void deshacerMovimiento(Posicion posicionActual) {
        mapa.deshacerMovimiento(posicionActual);
    }

    public void contaminar(Posicion posicion) {
        mapa.contaminar(posicion);
    }

    public boolean tieneEnergia(int energia) {
        return mapa.tieneEnergia(energia);
    }

    public boolean puedeTerminarVekEn(Vek vek, Posicion candidata) {
        return mapa.puedeTerminarVekEn(vek, candidata);
    }

    public List<Posicion> obtenerPosicionesDeEdificios() {
        return mapa.obtenerPosicionesDeEdificios();
    }

    //TODO: viola tell don't ask
    public boolean tieneAtaqueAnunciadoEn(Posicion posicion) {
        for (AtaqueAnunciado ataque : ataquesAnunciados) {
            if (ataque.amenazaA(posicion, this)) {
                return true;
            }
        }
        return false;
    }
    //TODO: viola tell don't ask
    public boolean hayAtaquesAnunciados() {
        return !ataquesAnunciados.isEmpty();
    }

    public void resolverAtaques() {
        for (AtaqueAnunciado ataque : ataquesAnunciados) {
            ataque.resolver(this);
        }
        ataquesAnunciados.clear();
    }

    public boolean vekAmenazaEnDireccion(Vek atacante, Direccion direccion, Posicion posicionObjetivo) {
        return mapa.vekAmenazaEnDireccion(atacante, direccion, posicionObjetivo, this);
    }

    public void ejecutarAtaqueVek(Vek atacante, Direccion direccion) {
        mapa.ejecutarAtaqueVek(atacante, direccion, this);
    }

    public void anunciarEmergencia(Posicion posicion, Vek vek) {
        if (!mapa.esAptaParaEmergencia(posicion) || tieneEmergenciaAnunciadaEn(posicion)) {
            throw new CasillaInvalidaException("No se puede anunciar una emergencia en esa casilla");
        }

        emergenciasAnunciadas.add(new EmergenciaAnunciada(posicion, vek));
    }

    public List<Posicion> obtenerPosicionesDisponiblesParaEmergencia(){
        List<Posicion> disponibles = mapa.obtenerPosicionesDeSueloLibre();
        disponibles.removeIf(this::tieneEmergenciaAnunciadaEn);
        return disponibles;
    }
    
}
