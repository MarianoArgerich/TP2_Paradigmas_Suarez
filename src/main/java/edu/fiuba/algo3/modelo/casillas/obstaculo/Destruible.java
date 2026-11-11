package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.casillas.Casilla;

public class Destruible extends Obstaculo {
    private int vida;

    public Destruible(int vida) {
        this.vida = vida;
    }

    @Override
    public void recibirDisparo(int danio, Casilla casillaActual){
        recibirDanio(danio, casillaActual);
    }

    @Override
    public void recibirDanioPorColision(Casilla casillaActual) {
        recibirDanio(1, casillaActual);
    }

    private void recibirDanio(int danio, Casilla casillaActual) {
        this.vida -= danio;
        if (vida <= 0) {
            casillaActual.vaciar();
        }
    }
}
