package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
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

    public void aceptar(Visitante visitante, Mapa mapa){

        this.ocupante.aceptar(visitante, terreno, this, mapa);

    }

    public boolean estaEn(Posicion otraPosicion) {
        return this.posicion.equals(otraPosicion);
    }

    public boolean tieneUnidad(Unidad unidad){
        return this.ocupante.equals(unidad);
    }

    public void colocarOcupante(Ocupante ocupante) {

        if (this.terreno.puedeIngresar() && this.ocupante.puedeIngresar()) {
            this.ocupante = ocupante;
        }
        else {
            throw new CasillaInvalidaException("No se puede colocar la unidad en una casilla ocupada o no transitable");
        }
    }

    public void transferirOcupanteA(Casilla casillaDestino){

        try {
            casillaDestino.colocarOcupante(this.ocupante);
            vaciar();
        }
        catch (CasillaInvalidaException e) {
            throw new CasillaInvalidaException("No se puede colocar la unidad en una casilla ocupada o no transitable");
        }

    }

    public void vaciar(){
        this.ocupante = new SinOcupante();
    }

    public boolean tieneTerreno(Terreno terrenoAComparar) {
        return this.terreno.equals(terrenoAComparar);
    }

    public boolean tieneOcupante(Ocupante ocupanteAComparar) {
        return this.ocupante.equals(ocupanteAComparar);
    }

    public boolean tieneMech() {
        return this.ocupante.esMech();
    }

    public boolean tieneVek() {
        return this.ocupante.esVek();
    }

    public Posicion siguienteEn(Direccion direccion) {
        return this.posicion.siguienteEn(direccion);
    }

    public boolean detieneDisparo(){
        return this.ocupante.detieneDisparo();
    }

    public void notificarMovimientoA(Unidad unidad) {
        unidad.registrarMovimientoDesde(this.posicion);
    }

    public void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        this.ocupante.verificarAtaqueEn(this.terreno);
        this.ocupante.atacar(this.posicion, objetivo, tablero);
    }

    public void deshacerMovimientoOcupante(Mapa mapa) {
        this.ocupante.deshacerMovimientoDesde(this, mapa);
    }

    public void moverOcupante(Direccion direccion, int distancia, Mapa mapa) {
        this.ocupante.moverDesde(this, direccion, distancia, mapa);
    }

    public boolean esSiguienteEn(Direccion direccion, Casilla otraCasilla) {
        return otraCasilla.estaEn(this.posicion.siguienteEn(direccion));
    }

    public boolean puedeTerminarVek(Vek vek) {
        return this.terreno.puedeIngresar() && this.ocupante.puedeIngresar() && vek.puedeTerminarEn(this.terreno);
    }

    public void agregarPosicionSiTieneEdificio(List<Posicion> posiciones) {
        if (this.tieneEdificio()){
            posiciones.add(this.posicion);
        }
    }

    private boolean tieneEdificio() {
        return this.ocupante.esEdificio();
    }

    public boolean amenazaPosicion(Vek atacante, Direccion direccion, Posicion posicionObjetivo, Tablero tablero) {
        if (this.tieneUnidad(atacante)) {
            return atacante.amenazaDesde(this.posicion, direccion, posicionObjetivo, tablero);
        }
        return false;
    }

    public void ejecutarAtaqueVek(Vek atacante, Direccion direccion, Tablero tablero) {
        if (this.tieneUnidad(atacante)) {
            atacante.resolverAtaque(this.posicion, direccion, tablero);
        }
    }

    public void contaminar(){
        this.ocupante.contaminar();
    }

    public void colocarUnidad(Unidad unidad) {
        if (this.terreno.puedeIngresar() && this.ocupante.puedeIngresar()) {
            this.ocupante = unidad;
            this.terreno.reaccionarAlIngresoDe(unidad);
        } else {
            throw new CasillaInvalidaException(
                    "No se puede colocar la unidad en una casilla ocupada o no transitable"
            );
        }
    }

    public void transferirUnidadA(Casilla destino, Unidad unidad) {
        destino.recibirUnidad(unidad);
        this.vaciar();
    }

    protected void recibirUnidad(Unidad unidad) {
        colocarUnidad(unidad);
    }

    public void aplicarEfectoDelTerreno() {
        this.ocupante.reaccionarAlTerreno(this.terreno, this);
    }

    public boolean esAptoParaEmergencia() {
        return this.terreno.esAptoParaEmergencia() && this.ocupante.esAptoParaEmergencia();
    }

    public void agregarPosicionSiEsAptaParaEmergencia(List<Posicion> posiciones) {
        if (esAptoParaEmergencia()) {
            posiciones.add(this.posicion);
        }
    }
}
