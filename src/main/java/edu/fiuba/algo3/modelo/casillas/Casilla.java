package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Casilla {

    private final Posicion posicion;
    private Unidad unidad = null;


    public Casilla(Posicion posicion) {
        this.posicion = posicion;
    }
    public void recibirGolpe(int dano, Direccion direccion, Tablero tablero) {
        if (!estaOcupada()) {
            return;
        }

        this.unidad.recibirGolpe(dano, this.posicion, direccion, tablero, this);
    }

    public void recibirEmpuje(Casilla casilla) {
        if (estaOcupada()) {

            this.unidad.recibirDano(1, this);
            casilla.recibirDanoPorColision();
        } else {
            casilla.transferirUnidadA(this);
        }
    }

    public void recibirDanoPorColision() {
        if (estaOcupada()) {
            this.unidad.recibirDano(1, this);
        }
    }

    private boolean estaOcupada() {
        return unidad != null;
    }

    public void transferirUnidadA(Casilla casillaDestino) {
        casillaDestino.ocupar(this.unidad);
        desocupar();
    }

    public void ocupar(Unidad unidad){
        if(estaOcupada()){
            throw new RuntimeException("casilla ocupada");
        }
        this.unidad = unidad;
        this.unidad.moverA(this.posicion);
    }

    public void desocupar() {
        this.unidad = null;
    }

    public boolean detieneDisparo() {
        return estaOcupada();
    }
}
