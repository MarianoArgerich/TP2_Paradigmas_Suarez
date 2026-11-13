package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.Estado;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.InicioTurno;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.Movido;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Mech extends Unidad {

    private final Arma arma;
    private Estado estado;

    protected Mech(int vidaMax, int movimiento, Arma arma) {
        super(vidaMax, movimiento);
        this.arma = arma;
        this.estado = new InicioTurno();
    }

    public void cambiarEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public void registrarMovimientoDesde(Posicion posicion) {
        this.estado = new Movido(posicion);
    }

    @Override
    public void mover(Direccion direccion, int distancia, Tablero tablero) {
        this.estado.mover(this, direccion, distancia, tablero);
    }

    public void atacar(Posicion posicionObjetivo, Tablero tablero) {
        this.estado.atacar(this, this.arma, posicionObjetivo, tablero);
    }

    public void deshacerMovimiento(Tablero tablero ){
        this.estado.deshacerMovimiento(this, tablero);
    }
}
