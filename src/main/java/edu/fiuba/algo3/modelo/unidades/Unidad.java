package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import edu.fiuba.algo3.modelo.Visitante.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.capacidades.Contaminable;
import edu.fiuba.algo3.modelo.unidades.capacidades.Movible;
import edu.fiuba.algo3.modelo.unidades.estadoContaminacion.EstadoContaminacion;
import edu.fiuba.algo3.modelo.unidades.estadoContaminacion.NoContaminado;

import java.util.Objects;

public abstract class Unidad extends Ocupante implements Movible, Contaminable {

    private final int vidaMax;
    private final int rangoDeMovimiento;
    protected int vidaActual;
    private EstadoContaminacion estadoContaminacion;

    protected Unidad(int vidaMax, int rangoDeMovimiento) {
        this.vidaMax = vidaMax;
        this.vidaActual = this.vidaMax;
        this.rangoDeMovimiento = rangoDeMovimiento;
        this.estadoContaminacion = new NoContaminado();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
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

    public void aceptar(Visitante visitante, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        visitante.visitar(this,  terreno, casillaActual, tablero);
    }

    public void aplicarDanio(int danio, Casilla casillaActual){
        this.vidaActual = Math.max(0, this.vidaActual - danio);
        if (this.vidaActual <= 0) {
            casillaActual.vaciar();
        }
    }

    public void verificarRangoDeMovimiento(int distancia){
        if (distancia > rangoDeMovimiento) {
            throw new DistanciaInvalidaException("Fuera de rango");
        }
    }

    public boolean estaDentroDelRango(int distancia){
        return(distancia <= rangoDeMovimiento);
    }

    public boolean suVidaEsIgualA(int comparacion) {
        return vidaActual == comparacion;
    }

    public void registrarMovimientoDesde(Posicion posicion) {
    }

    @Override
    public void moverDesde(Casilla origen, Direccion direccion, int distancia, Tablero tablero) {
        this.verificarRangoDeMovimiento(distancia);
        tablero.desplazarUnidad(origen, this, direccion, distancia);
    }

    @Override
    public void contaminar(){
        estadoContaminacion.contaminar(this);
    }
    public void cambiarEstadoContaminacion(EstadoContaminacion estadoContaminacion){
        this.estadoContaminacion = estadoContaminacion;
    }

    public void contaminarPorAcido() {
        this.estadoContaminacion.contaminarPorAcido(this);
    }

    public void salirDeAcido() {
        this.estadoContaminacion.salirDeAcido(this);
    }

    public void recibirDanio(int danio, Casilla casillaActual) {
        estadoContaminacion.recibirDanio(this, danio, casillaActual);
    }



    public void reaccionarAlTerreno(Terreno terreno, Casilla casilla) {
        terreno.reaccionarAlIngresoDe(this,casilla);
    }

    public void caerAlAguaEn(Casilla casilla) {}

    @Override
    public void verificarAtaqueEn(Terreno terreno) {
        terreno.verificarAtaqueDe(this);
    }

    public void verificarAtaqueEnAgua() {}

    @Override
    public boolean esAptoParaEmergencia(){
        return true;
    }
}
