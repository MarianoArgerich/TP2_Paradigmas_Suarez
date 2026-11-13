package edu.fiuba.algo3.modelo.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Golpe implements Visitante {

    private final int danio;
    private final Direccion direccion;

    public Golpe(int danio, Direccion direccion){
        this.danio = danio;
        this.direccion = direccion;
    }

    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        unidad.recibirDanio(danio, casillaActual);

        if (unidad.estaViva()){
            casillaActual.aceptar(new Empuje(direccion), mapa);
        }
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Mapa mapa) {

    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        destruible.recibirDanio(danio, casillaActual);
    }
}
