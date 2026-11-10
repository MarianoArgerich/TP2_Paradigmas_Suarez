package edu.fiuba.algo3.modelo.casillas.transitable;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Casilla {

    protected final Posicion posicion;

    public Casilla(Posicion posicion) {
        this.posicion = posicion;
    }

    abstract public void ocupar(Unidad unidad);


    public abstract void transferirUnidadA(Casilla casilla);

    public abstract void unidadRecibirGolpe(int danio, Direccion direccion, Tablero tablero);

    public abstract void recibirEmpuje(Casilla casillaOrigen);

    public abstract boolean detieneDisparo();

    public abstract void recibirDanoPorColision();

    public abstract void desocupar();

    public abstract void unidadGolpeada(int danio);
}
