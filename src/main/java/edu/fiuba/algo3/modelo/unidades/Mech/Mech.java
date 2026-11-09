package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.excepciones.YaAtacoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Mech extends Unidad {

    protected Arma arma;
    protected boolean yaAtaco;


    public Mech(int vidaMax, int movimiento, Arma arma){
        super(vidaMax, movimiento);
        this.arma = arma;
        this.yaAtaco = false;
    }

    public void atacar(Posicion posicionObjetivo, Tablero tablero) {
        if (this.yaAtaco) {
            throw new YaAtacoException("El Mech no debería poder atacar dos veces en un turno");
        }

        arma.ejecutarAtaque(this.posicion, posicionObjetivo, tablero);


        this.yaAtaco = true;
    }



}
