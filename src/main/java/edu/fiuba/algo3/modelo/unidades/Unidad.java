package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.Objects;

public abstract class Unidad extends Ocupante {

    private final int vidaMax;
    private final int rangoDeMovimiento;
    private int vidaActual;

    protected Unidad(int vidaMax, int rangoDeMovimiento) {
        this.vidaMax = vidaMax;
        this.vidaActual = this.vidaMax;
        this.rangoDeMovimiento = rangoDeMovimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Unidad unidad = (Unidad) o;
        return vidaMax == unidad.vidaMax && rangoDeMovimiento == unidad.rangoDeMovimiento && vidaActual == unidad.vidaActual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vidaMax, rangoDeMovimiento, vidaActual);
    }

    @Override
    public boolean puedeIngresar() {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Mapa mapa) {
        visitante.visitar(this,  terreno, casillaActual, mapa);
    }

    public void recibirDanio(int danio, Casilla casillaActual){
        this.vidaActual -= danio;
        if (this.vidaActual <= 0) {
            casillaActual.vaciar();
        }
    }

    public void verificarRangoDeMovimiento(int distancia){
        if (distancia > rangoDeMovimiento) {
            throw new DistanciaInvalidaException("Fuera de rango");
        }
    }

    public boolean suVidaEsIgualA(int comparacion) {
        return vidaActual == comparacion;
    }

    public void registrarMovimientoDesde(Posicion posicion) {
    }

    @Override
    public void moverDesde(Casilla origen, Direccion direccion, int distancia, Mapa mapa) {
        this.verificarRangoDeMovimiento(distancia);
        mapa.desplazarUnidad(origen, this, direccion, distancia);
    }
}
