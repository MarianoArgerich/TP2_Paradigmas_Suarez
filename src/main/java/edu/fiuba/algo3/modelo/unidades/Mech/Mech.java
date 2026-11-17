package edu.fiuba.algo3.modelo.unidades.Mech;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.Estado;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.InicioTurno;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.Movido;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.capacidades.Atacante;
import edu.fiuba.algo3.modelo.unidades.capacidades.DeshaceMovimiento;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante;

public abstract class Mech extends Unidad implements DeshaceMovimiento, Atacante {

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
    public void atacar(Posicion origen, Posicion posicionObjetivo, Tablero tablero) {
        this.estado.atacar(this, this.arma, origen, posicionObjetivo, tablero);
    }

    public void deshacerMovimientoDesde(Casilla casillaActual, Mapa mapa){
        this.estado.deshacerMovimiento(this, casillaActual, mapa);
    }

    @Override
    public void moverDesde(Casilla origen, Direccion direccion, int distancia, Mapa mapa) {
        this.estado.mover(this, origen, direccion, distancia, mapa);
    }

    @Override
    public boolean esMech() {
        return true;
    }

    @Override
    public void verificarAtaqueEnAgua() {
        throw new AtaqueInvalidoException("Un Mech en el Agua no puede atacar");
    }

    @Override
    public void aceptar(SegundoVisitante visitante) {
        visitante.visitar(this);
    }

}
