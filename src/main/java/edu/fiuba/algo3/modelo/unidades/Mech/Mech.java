package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeDeshacerMovimiento;
import edu.fiuba.algo3.modelo.excepciones.YaAtacoException;
import edu.fiuba.algo3.modelo.excepciones.YaSeMovioException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Mech extends Unidad {

    private Arma arma;
    private int movimientosDeshechos;
    private Posicion posicionAnterior;
    private boolean yaAtaco;
    protected boolean yaSeMovio;


    public Mech(int vidaMax, int movimiento, Arma arma){
        super(vidaMax, movimiento);
        this.posicionAnterior = null;
        this.arma = arma;
        this.yaAtaco = false;
        this.movimientosDeshechos = 0;
    }

    @Override
    public void mover(Direccion direccion, int distancia, Tablero tablero) {
        if (this.yaSeMovio) {
            throw new YaSeMovioException("El Mech no debería poder moverse dos veces en un turno");
        }

        Posicion posicionAux =  posicion;
        tablero.moverUnidad(this, this.posicion, direccion, distancia);
        if (!posicionAux.equals(this.posicion)) {
            this.posicionAnterior = posicionAux;
            this.yaSeMovio = true;
        }
    }

    public void atacar(Posicion posicionObjetivo, Tablero tablero) {
        if (this.yaAtaco) {
            throw new YaAtacoException("El Mech no debería poder atacar dos veces en un turno");
        }

        arma.ejecutarAtaque(this.posicion, posicionObjetivo, tablero);

        this.yaAtaco = true;
        this.posicionAnterior = null;
    }

    public void deshacerMovimiento(Tablero tablero ){
        if (this.yaAtaco){
            throw new NoSePuedeDeshacerMovimiento("No se puede deshacer el movimiento si ya atacó.");
        }
        if (!this.yaSeMovio || this.posicionAnterior == null){
            throw new NoSePuedeDeshacerMovimiento("No se puede deshacer el movimiento si no se movió.");
        }
        if(this.movimientosDeshechos > 0){
            throw new NoSePuedeDeshacerMovimiento("No se puede deshacer mas de un movimiento");
        }
        tablero.revertirMovimiento(this, this.posicion, this.posicionAnterior);
        this.yaSeMovio = false;
        this.posicionAnterior = null;
        this.movimientosDeshechos += 1;

    }



}
