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
        int x = coordenadas.getX();
        int y = coordenadas.getY();

        casillas[x][y].ocupar(unidad);
        unidades.add(unidad);
        unidad.setPosicion(coordenadas);
    }

    public void moverUnidad(Direccion direccion, int distancia, Posicion coordenadaActual, Unidad unidad) {

        if (!unidades.contains(unidad)) {
            throw new RuntimeException("Unidad no existe");
        }

        if (distancia < 0 || distancia > 3){
            throw new RuntimeException("Fuera de rango");
        }

        Posicion coordenadaAux = coordenadaActual;

        for (int i=1; i<=distancia; i++){
            Posicion nuevaCoord = coordenadaAux.siguienteEn(direccion);
            coordenadaValida(nuevaCoord);
            try{
                removerUnidad(coordenadaAux, unidad);
                colocarUnidad(nuevaCoord, unidad);
            }
            catch (RuntimeException e){
                colocarUnidad(coordenadaActual, unidad);
                break;
            }

            coordenadaAux = nuevaCoord;
        }

    }

    private void removerUnidad(Posicion coordenadas, Unidad unidad) {
        int x = coordenadas.getX();
        int y = coordenadas.getY();

        casillas[x][y].desocupar();
        unidades.remove(unidad);
    }

    private void coordenadaValida(Posicion coordenadas){
        int x = coordenadas.getX();
        int y = coordenadas.getY();

        if (x > 8 || x < 0 || y > 8 || y < 0){
            throw new RuntimeException("Coordenada no valida");
        }
    }
}
