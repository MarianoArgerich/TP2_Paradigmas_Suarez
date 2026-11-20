package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.LineaDeTiro;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Proyectil;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Ataque.DisparoAcido;

import java.util.ArrayList;
import java.util.List;

public class Luciernaga extends Vek {
    public Luciernaga() {
        super(3, 2, 1);
    }


    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {
        //TODO: tell don't ask
        if (!origen.estaEnLineaRectaCon(objetivo)) {
            throw new AtaqueInvalidoException("La luciernaga solo puede atacar en linea recta");
        }

        Direccion direccion = origen.calcularDireccionHacia(objetivo);
        this.resolverAtaque(origen, direccion, tablero);
    }

    @Override
    public void resolverAtaque(Posicion posicionActual, Direccion direccion, Tablero tablero) {
        tablero.aplicarEfecto(posicionActual.siguienteEn(direccion), new Proyectil(direccion, new DisparoAcido(danio)));
    }


    @Override
    protected Posicion calcularObjetivoEn(Posicion origen, Direccion direccion, Tablero tablero) {
        LineaDeTiro lineaDeTiro = new LineaDeTiro(origen, direccion);
        lineaDeTiro.explorarEn(tablero);
        return lineaDeTiro.destinoFinal();
    }

    @Override
    public boolean amenazaDesde(Posicion origen, Direccion direccion, Posicion objetivo, Tablero tablero) {
        LineaDeTiro lineaDeTiro = new LineaDeTiro(origen, direccion);
        lineaDeTiro.explorarEn(tablero);
        return lineaDeTiro.alcanzaA(objetivo);
    }

    @Override
    public void caerAlAguaEn(Casilla casilla) {
        this.vidaActual = 0;
        super.caerAlAguaEn(casilla);
    }
}
