package edu.fiuba.algo3.modelo.casillas.obstaculo;

import edu.fiuba.algo3.modelo.Efecto.Efecto;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;

public class Destruible extends Obstaculo {
    private int vida;

    public Destruible(int vida) {
        this.vida = vida;
    }

    @Override
    public void aceptar(Efecto efecto, Terreno terreno, Casilla casillaActual, Mapa mapa) {
    }
}
