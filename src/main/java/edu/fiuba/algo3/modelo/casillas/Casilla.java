package edu.fiuba.algo3.modelo.casillas;
import edu.fiuba.algo3.modelo.Efecto.Efecto;
import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
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

    public void aceptar(Efecto efecto, Mapa mapa){
        this.ocupante.aceptar(efecto, terreno, this, mapa);
    }

    public boolean estaEn(Posicion otraPosicion) {
        return this.posicion.equals(otraPosicion);
    }

    public boolean tieneUnidad(Unidad unidad){
        return this.ocupante.esUnidad(unidad);
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

    public boolean tieneTerreno(Terreno terrenoAComparar) {
        return this.terreno.equals(terrenoAComparar);
    }

    public boolean tieneOcupante(Ocupante ocupanteAComparar) {
        return this.ocupante.equals(ocupanteAComparar);
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

    public void ejecutarAtaque(Arma arma, Posicion objetivo, Tablero tablero) {
        arma.ejecutarAtaque(this.posicion, objetivo, tablero);
    }
}
