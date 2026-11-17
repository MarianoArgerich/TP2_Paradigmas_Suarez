package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Luciernaga extends Vek {
    public Luciernaga() {
        super(3, 2, 1);
    }


    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {

        if (!origen.estaEnLineaRectaCon(objetivo)) {
            throw new AtaqueInvalidoException("La luciernaga solo puede atacar en linea recta");
        }

        Direccion direccion = origen.calcularDireccionHacia(objetivo);
        Posicion actual = origen.siguienteEn(direccion);

        while (!tablero.detieneDisparoEn(actual)){
            actual = actual.siguienteEn(direccion);
        }
        tablero.aplicarDisparoAcido(actual, this.danio);

        tablero.contaminar(objetivo);
    }

    @Override
    public void caerAlAguaEn(Casilla casilla) {
        this.vidaActual = 0;
        super.caerAlAguaEn(casilla);
    }


    @Override
    public List<Posicion> obtenerCasillasAtacablesDesde(Posicion origen, Tablero tablero) {
        List<Posicion> atacables = new ArrayList<>();

        for (Direccion direccion : direccionesOrtogonales()) {
            Posicion actual = origen.siguienteEn(direccion);

            while (!tablero.detieneDisparoEn(actual)) {
                actual = actual.siguienteEn(direccion);
            }

            atacables.add(actual);
        }

        ordenarDeterministicamente(atacables);
        return atacables;
    }

    @Override
    public boolean amenazaDesde(Posicion origen, Direccion direccion, Posicion objetivo, Tablero tablero) {
        Posicion actual = origen.siguienteEn(direccion);
        while (!tablero.detieneDisparoEn(actual)) {
            if (actual.equals(objetivo)) {
                return true;
            }
            actual = actual.siguienteEn(direccion);
        }
        return tablero.posicionValida(actual) && actual.equals(objetivo);
    }

    private Posicion buscarPrimerImpactoEnLinea(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion actual = origen.siguienteEn(direccion);

        while (!tablero.detieneDisparoEn(actual)) {
            actual = actual.siguienteEn(direccion);
        }

        return actual;
    }

    @Override
    public void resolverAtaque(Posicion posicionActual, Direccion direccion, Tablero tablero) {
        Posicion objetivo = buscarPrimerImpactoEnLinea(posicionActual, direccion, tablero);
        tablero.aplicarDisparoAcido(objetivo, this.danio);
    }
}
