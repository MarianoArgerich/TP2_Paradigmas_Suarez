package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Casilla {

    private final Posicion posicion;
    private Terreno terreno;
    private Ocupante ocupante;

    public Casilla(Posicion posicion) {
        this(posicion, new Suelo(), new SinOcupante());
    }
    public Casilla(Posicion posicion, Terreno terreno) {
        this(posicion, terreno, new SinOcupante());
    }
    public Casilla(Posicion posicion, Terreno terreno, Ocupante ocupante) {
        this.posicion = posicion;
        this.terreno = terreno;
        this.ocupante = ocupante;
    }

    public boolean esDelTerreno(Terreno terrenoAComparar) {
        return terrenoAComparar != null && this.terreno.getClass().equals(terrenoAComparar.getClass());
    }

    public boolean esDelOcupante(Ocupante ocupanteAComparar) {
        return ocupanteAComparar != null && this.ocupante.getClass().equals(ocupanteAComparar.getClass());
    }

    public boolean estaEn(Posicion otraPosicion) {
        return this.posicion.equals(otraPosicion);
    }

    public Posicion siguienteEn(Direccion direccion) {
        return this.posicion.siguienteEn(direccion);
    }

    public boolean admitirOcupante(Ocupante ocupante){
        if (!this.ocupante.puedeIngresar()){
            return false;
        }
        this.ocupante = ocupante;
        return true;
    }

    public boolean transferirOcupanteA(Casilla casillaDestino){
        if (casillaDestino.admitirOcupante(this.ocupante)){
            vaciar();
            return true;
        }
        return false;
    }

    public void vaciar(){
        this.ocupante = new SinOcupante();
    }

    public boolean tieneUnidad(Unidad unidad){
        return this.ocupante.esUnidad(unidad);
    }

    public void recibirGolpe(int danio, Direccion direccion, Tablero tablero){
        this.ocupante.recibirGolpe(danio, direccion, tablero, this);
    }

    public void recibirDisparo(int danio){
        this.ocupante.recibirDisparo(danio, this);
    }

    public void recibirDanioPorColision(){
        this.ocupante.recibirDanioPorColision(this);
    }

    public boolean detieneDisparo(){
        return this.ocupante.detieneDisparo();
    }

    public void notificarMovimientoA(Unidad unidad) {
        unidad.registrarMovimientoDesde(this.posicion);
    }

    public void ejecutarAtaque(Arma arma, Posicion objetivo, Tablero tablero) {
        arma.ejecutarAtaque(this.posicion, objetivo, tablero);
    }

    public void empujarHacia(Direccion direccion, Tablero tablero) {
        tablero.empujarDesde(this.posicion, direccion);
    }

    public boolean estaVacia(){
        return this.ocupante.estaVacio();
    }

}
