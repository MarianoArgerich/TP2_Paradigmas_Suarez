package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.casillas.transitable.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private Casilla[][] casillas;
    private List<Unidad> unidades = new ArrayList<>();

    public Tablero(Casilla[][] mapa){
        casillas = mapa;
    }

    public Boolean casillaIgualQue(int x, int y, Casilla comparar){
        return casillas[x][y] == comparar;
    }

    public void colocarUnidad(Posicion coordenadas, Unidad unidad) {
        esCoordenadaValida(coordenadas);
        casillaEn(coordenadas).ocupar(unidad);
        if (!unidades.contains(unidad)) {
            unidades.add(unidad);
        }
        unidad.moverA(coordenadas);
    }

    public void moverUnidad(Unidad unidad, Posicion origen, Direccion direccion, int distancia) {
        if (!unidades.contains(unidad)) {
            throw new RuntimeException("Unidad no existe");
        }

        unidad.verificarRangoDeMovimiento(distancia);

        Posicion actual = origen;

        for (int i=1; i<=distancia; i++){
            Posicion siguiente = actual.siguienteEn(direccion);

            try {
                Casilla casillaSiguiente = casillaEn(siguiente);
                casillaEn(actual).transferirUnidadA(casillaSiguiente);
                actual = siguiente;
            } catch (RuntimeException e){
                break;
            }
        }
    }

    private void esCoordenadaValida(Posicion posicion){
        int x = posicion.getX();
        int y = posicion.getY();
        if (x < 0 || x > casillas.length - 1 || y < 0 || y > casillas[0].length - 1){
            throw new RuntimeException("Coordenada fuera del tablero");
        }
    }

    private Casilla casillaEn(Posicion posicion){
        esCoordenadaValida(posicion);
        return casillas[posicion.getX()][posicion.getY()];
    }

    public void aplicarGolpe(Posicion posicion, int dano, Direccion direccion) {
        Casilla casilla = casillaEn(posicion);
        casilla.unidadRecibirGolpe(dano, direccion, this);
    }

    public void empujarDesde(Posicion origen, Direccion direccion) {
        Posicion destino = direccion.calcularSiguientePosicion(origen);

        try {
            Casilla casillaOrigen = casillaEn(origen);
            Casilla casillaDestino = casillaEn(destino);
            casillaDestino.recibirEmpuje(casillaOrigen);

        } catch (RuntimeException e){

        }
    }

    private boolean estaDentro(Posicion posicion) {
        return posicion.getX() >= 0 && posicion.getX() < casillas.length
                && posicion.getY() >= 0 && posicion.getY() < casillas[0].length;
    }

    public void ataqueEnLinea(Posicion origen, int danio, Direccion direccion) {
        Posicion actual = direccion.calcularSiguientePosicion(origen);
        while (estaDentro(actual)) {
            if (casillaEn(actual).detieneDisparo()) {
                aplicarGolpe(actual, danio, direccion);
                return;
            }
            actual = direccion.calcularSiguientePosicion(actual);
        }
    }

    public void revertirMovimiento(Unidad unidad, Posicion posicionActual, Posicion posicionAnterior){
        if (!unidades.contains(unidad)) {
            throw new RuntimeException("Unidad no existe");
        }
        casillaEn(posicionActual).transferirUnidadA(casillaEn(posicionAnterior));
    }

    public void aplicarDisparo(Posicion objetivo,int danio){
        Casilla casilla = casillaEn(objetivo);
        casilla.unidadGolpeada(danio);

    }
}
