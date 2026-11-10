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


    abstract public void transferirUnidadA(Casilla casilla);

    abstract public void unidadRecibirDanio(int danio, Direccion direccion, Tablero tablero);

    abstract public void recibirEmpuje(Casilla casillaOrigen);

    abstract public boolean detieneDisparo();

    abstract public void recibirDanoPorColision();

    abstract public void desocupar();
}
