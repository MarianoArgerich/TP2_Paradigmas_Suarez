package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.*;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class Casilla {

    private final Posicion posicion;
    private Terreno terreno;
    private Ocupante ocupante;

    public Casilla(Posicion posicion) {
        this(posicion, new Suelo());
    }
    public Casilla(Posicion posicion, Terreno terreno) {
        this.posicion = posicion;
        this.terreno = terreno;
        this.ocupante = new SinOcupante();
    }

    public void aceptar(Visitante visitante, Tablero tablero){
        this.ocupante.aceptar(visitante, terreno, this, tablero);
    }

    public void aceptar(SegundoVisitante visitante){
        this.ocupante.aceptar(visitante);
    }

    public boolean estaEn(Posicion otraPosicion) {
        return this.posicion.equals(otraPosicion);
    }

    //TODO: tell don't ask, hacerlo volar. Primero hay que revisar Tablero
    public boolean tieneUnidad(Unidad unidad){
        return this.ocupante.equals(unidad);
    }

    public void colocarOcupante(Ocupante nuevoOcupante) {
        this.terreno.verificarPuedeIngresar();
        this.ocupante.verificarPuedeRecibirOcupante();
        this.ocupante = nuevoOcupante;
        nuevoOcupante.reaccionarAlTerreno(this.terreno, this);
    }

    public void transferirOcupanteA(Casilla casillaDestino){
        casillaDestino.colocarOcupante(this.ocupante);
        vaciar();
    }

    public void vaciar(){
        this.ocupante = new SinOcupante();
    }

    public Posicion siguienteEn(Direccion direccion) {
        return this.posicion.siguienteEn(direccion);
    }

    public void notificarMovimientoA(Unidad unidad) {
        unidad.registrarMovimientoDesde(this.posicion);
    }

    public void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        SegundoVisitante ataque = new AtaqueDesdeCasilla(
                this.posicion,
                objetivo,
                tablero,
                this.terreno
        );

        this.ocupante.aceptar(ataque);
    }

    public void deshacerMovimientoOcupante(Tablero tablero) {
        SegundoVisitante deshacer = new DeshacerMovimientoDesdeCasilla(
                this,
                tablero
        );

        this.ocupante.aceptar(deshacer);
    }

    public void moverOcupante(Direccion direccion, int distancia, Tablero tablero) {
        SegundoVisitante movimiento = new MoverDesdeCasilla(
                this,
                direccion,
                distancia,
                tablero
        );
        this.ocupante.aceptar(movimiento);
    }

    public void agregarPosicionDeEdificioA(List<Posicion> posiciones) {
        this.ocupante.agregarPosicionDeEdificioA(this.posicion, posiciones);
    }

    public boolean amenazaPosicion(Vek atacante, Direccion direccion, Posicion posicionObjetivo, Tablero tablero) {
        return atacante.amenazaDesde(this.posicion, direccion, posicionObjetivo, tablero);
    }

    public void ejecutarAtaqueVek(Vek atacante, Direccion direccion, Tablero tablero) {
        atacante.resolverAtaque(this.posicion, direccion, tablero);
    }

    public void agregarPosicionCandidataParaEmergenciaA(List<Posicion> posiciones) {
        this.terreno.agregarCandidataParaEmergenciaA(this.ocupante, this.posicion, posiciones);
    }


    public void validarEmergencia() {
        this.terreno.validarEmergencia();
        this.ocupante.validarEmergencia();
    }
}
