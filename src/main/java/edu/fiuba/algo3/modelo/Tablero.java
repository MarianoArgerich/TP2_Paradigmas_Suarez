package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.CoordenadaFueraDelTableroException;
import edu.fiuba.algo3.modelo.excepciones.UnidadInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.List;

public class Tablero {

    private final List<Casilla> casillas;

    public Tablero(List<Casilla> casillas){
        this.casillas = casillas;
    }

    public void colocarUnidad(Posicion coordenadas, Unidad unidad) {
        validarCoordenada(coordenadas);
        boolean colocada = casillaEn(coordenadas).admitirOcupante(unidad);
        if (!colocada){
            throw new CasillaInvalidaException("No se puede colocar la unidad en una casilla ocupada o no transitable");
        }
    }

    public void moverUnidad(Unidad unidad, Direccion direccion, int distancia) {
        unidad.verificarRangoDeMovimiento(distancia);
        Casilla casillaInicial = casillaDe(unidad);


        for (int i=1; i<=distancia; i++) {
            Casilla origen = casillaDe(unidad);
            Posicion siguientePosicion = origen.siguienteEn(direccion);

            if (!esCoordenadaValida(siguientePosicion)) {
                break;
            }

            Casilla destino = casillaEn(siguientePosicion);
            boolean seTransifirio = origen.transferirOcupanteA(destino);
            if (!seTransifirio) {
                break;
            }
        }

        Casilla casillaFinal = casillaDe(unidad);
        if (casillaFinal != casillaInicial) {
            casillaInicial.notificarMovimientoA(unidad);
        }
    }

    public void revertirMovimiento(Unidad unidad, Posicion posicionAnterior){
        Casilla origenActual = casillaDe(unidad);
        Casilla destinoAnterior = casillaEn(posicionAnterior);
        origenActual.transferirOcupanteA(destinoAnterior);
    }

    public void atacarDesdeUnidad(Unidad unidad, Arma arma, Posicion objetivo) {
        casillaDe(unidad).ejecutarAtaque(arma, objetivo, this);
    }

    public void aplicarGolpe(Posicion posicion, int danio, Direccion direccion) {
        casillaEn(posicion).recibirGolpe(danio, direccion, this);
    }

    public void aplicarDisparo(Posicion objetivo,int danio){
        casillaEn(objetivo).recibirDisparo(danio);
    }

    public void empujarDesde(Posicion origen, Direccion direccion) {
        if (!esCoordenadaValida(origen)) {
            return;
        }

        Casilla casillaOrigen = casillaEn(origen);

        if (casillaOrigen.estaVacia()){
            return;
        }

        Posicion destino = casillaOrigen.siguienteEn(direccion);

        if (!esCoordenadaValida(destino)) {
            return;
        }

        Casilla casillaDestino = casillaEn(destino);
        boolean seTransfirio = casillaOrigen.transferirOcupanteA(casillaDestino);

        if (!seTransfirio) {
            casillaOrigen.recibirDanioPorColision();
            casillaDestino.recibirDanioPorColision();
        }
    }

    public void ataqueEnLinea(Posicion origen, int danio, Direccion direccion) {
        if(!esCoordenadaValida(origen)){
            return;
        }

        Posicion actual = casillaEn(origen).siguienteEn(direccion);
        while (esCoordenadaValida(actual)) {
            Casilla casillaActual = casillaEn(actual);
            if (casillaActual.detieneDisparo()) {
                casillaActual.recibirGolpe(danio, direccion, this);
                return;
            }
            actual = casillaActual.siguienteEn(direccion);
        }
    }

    public boolean esCoordenadaValida(Posicion posicion){
        return casillas.stream().anyMatch(casilla -> casilla.estaEn(posicion));
    }

    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return esCoordenadaValida(posicion) && casillaEn(posicion).tieneUnidad(unidad);
    }

    private Casilla casillaDe(Unidad unidad) {
        return casillas.stream()
                .filter(casilla -> casilla.tieneUnidad(unidad))
                .findFirst()
                .orElseThrow(() -> new UnidadInvalidaException("La unidad no está en el tablero"));
    }

    private Casilla casillaEn(Posicion posicion){
        return casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .orElseThrow(() -> new CoordenadaFueraDelTableroException("Coordenada fuera del tablero"));
    }

    private void validarCoordenada(Posicion posicion) {
        if (!esCoordenadaValida(posicion)) {
            throw new CoordenadaFueraDelTableroException("Coordenada fuera del tablero");
        }
    }
}
