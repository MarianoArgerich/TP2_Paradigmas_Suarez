package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Casilla {

    protected final Posicion posicion;

    public Casilla(Posicion posicion) {
        this.posicion = posicion;
    }

    public boolean estaEn(Posicion otraPosicion) {
        return this.posicion.equals(otraPosicion);
    }

    public Posicion siguienteEn(Direccion direccion) {
        return this.posicion.siguienteEn(direccion);
    }

    public abstract boolean admitirUnidad(Unidad unidad);
    public abstract boolean transferirUnidadA(Casilla casillaDestino);


    public abstract void recibirGolpe(int danio, Direccion direccion, Tablero tablero);
    public abstract void recibirEmpuje(Casilla casillaOrigen);
    public abstract void recibirDisparo(int danio);

    public abstract void recibirDanoPorColision();

    public abstract boolean detieneDisparo();

    public abstract boolean tieneUnidad(Unidad unidad);

    public abstract void desocupar();

    public void notificarMovimientoA(Unidad unidad) {
        unidad.registrarMovimientoDesde(this.posicion);
    }

    public void ejecutarAtaque(Arma arma, Posicion objetivo, Tablero tablero) {
        arma.ejecutarAtaque(this.posicion, objetivo, tablero);
    }

}
