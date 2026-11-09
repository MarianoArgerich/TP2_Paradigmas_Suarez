package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.casillas.Casilla;
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
        if (!esCoordenadaValida(coordenadas)){
            throw new RuntimeException("Coordenada fuera del tablero");
        }
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

        if (distancia < 0 || distancia > 3){
            throw new RuntimeException("Fuera de rango");
        }

        Posicion destinoFinal = origen;

        for (int i=1; i<=distancia; i++){
            Posicion siguiente = destinoFinal.siguienteEn(direccion);
            Casilla casillaSiguiente = casillaEn(siguiente);
            if(casillaSiguiente == null){
                break;
            }

            try {
                casillaSiguiente.validarPaso();
                destinoFinal = siguiente;
            } catch (RuntimeException e){
                break;
            }
        }

        if (!destinoFinal.equals(origen)) {
            casillaEn(origen).transferirUnidadA(casillaEn(destinoFinal));
            unidad.moverA(destinoFinal);
        }
    }

    private boolean esCoordenadaValida(Posicion posicion){
        return posicion.getX() >= 0 && posicion.getX() < casillas.length &&
                posicion.getY() >= 0 && posicion.getY() < casillas.length;
    }

    private Casilla casillaEn(Posicion posicion){
        if (!esCoordenadaValida(posicion)){
            return null;
        }
        return casillas[posicion.getX()][posicion.getY()];
    }

    public void aplicarGolpe(Posicion posicion, int dano, Direccion direccion) {
        Casilla casilla = casillaEn(posicion);
        if (casilla != null) {
            casilla.recibirGolpe(dano, direccion, this);
        }
    }

    public void empujarDesde(Posicion origen, Direccion direccion) {
        Posicion destino = direccion.calcularSiguientePosicion(origen);

        Casilla casillaOrigen = casillaEn(origen);
        Casilla casillaDestino = casillaEn(destino);

        if (casillaDestino != null) {
            casillaDestino.recibirEmpuje(casillaOrigen);
        }
    }
}
