package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.casillas.Casilla;

public class Tablero {

    private Casilla[][] casillas = null;

    public Tablero(Casilla[][] mapa){
        casillas = mapa;
    }

    public Boolean casillaIgualQue(int x, int y, Casilla comparar){
        return casillas[x][y] == comparar;
    }
}
