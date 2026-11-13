package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;

public abstract class Destruible extends Obstaculo {

    private int vida;

    public Destruible(int vida) {
        this.vida = vida;
    }

    @Override
    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        visitante.visitar(this, terreno, casillaActual, mapa);
    }

    public void recibirDanio(int danio, Casilla casillaActual){
        this.vida -= danio;

        if (this.vida <= 0) {
            casillaActual.vaciar();
        }
    }
}
