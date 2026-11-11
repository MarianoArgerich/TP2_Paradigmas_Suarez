package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
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

    public boolean estaEn(Posicion otraPosicion) {
        return this.posicion.equals(otraPosicion);
    }

    public boolean admitirOcupante(Ocupante ocupante){
        return this.terreno.puedeIngresar() && this.ocupante.puedeIngresar();
    }

    public void colocarOcupante(Ocupante ocupante) {
        if (!admitirOcupante(ocupante)) {
            throw new CasillaInvalidaException("No se puede colocar la unidad en una casilla ocupada o no transitable");
        }
        this.ocupante = ocupante;
    }

    public boolean transferirOcupanteA(Casilla casillaDestino){
        if (casillaDestino.admitirOcupante(this.ocupante)){
            casillaDestino.colocarOcupante(this.ocupante);
            vaciar();
            return true;
        }
        return false;
    }

    public void vaciar(){
        this.ocupante = new SinOcupante();
    }

    public boolean esDelTerreno(Terreno terrenoAComparar) {
        return terrenoAComparar != null && this.terreno.getClass().equals(terrenoAComparar.getClass());
    }

    public boolean esDelOcupante(Ocupante ocupanteAComparar) {
        return ocupanteAComparar != null && this.ocupante.getClass().equals(ocupanteAComparar.getClass());
    }

    public Posicion siguienteEn(Direccion direccion) {
        return this.posicion.siguienteEn(direccion);
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

    public void empujarOcupanteA(Casilla casillaDestino){
        this.ocupante.empujarA(this, casillaDestino);
    }

    public void empujarHacia(Direccion direccion, Tablero tablero) {
        tablero.empujarDesde(this.posicion, direccion);
    }

    public void procesarColisionCon(Casilla origen) {
        origen.recibirDanioPorColision();
        this.recibirDanioPorColision();
    }
}
