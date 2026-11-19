package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;


public abstract class Destruible extends Obstaculo {

    private int vida;

    public Destruible(int vida) {
        this.vida = vida;
    }

    @Override
    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        visitante.visitar(this, terreno, casillaActual, tablero);
    }

    public void recibirDanio(int danio, Casilla casillaActual, Tablero tablero){
        this.vida -= danio;

        if (this.vida <= 0) {
            casillaActual.vaciar();
        }
    }


    @Override
    public void aceptar(SegundoVisitante visitante) {
        visitante.visitar(this);
    }

}
