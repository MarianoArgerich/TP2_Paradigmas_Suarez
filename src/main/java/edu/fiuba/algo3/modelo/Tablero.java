package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arma;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private final List<Casilla> casillas;
    private List<Unidad> unidades = new ArrayList<>();

    public Tablero(List<Casilla> casillas){
        this.casillas = casillas;
    }

    public boolean casillaIgualQue(Posicion posicion, Casilla comparar){
        return casillaEn(posicion) == comparar;
    }

    public void colocarUnidad(Posicion coordenadas, Unidad unidad) {
        validarCoordenada(coordenadas);
        boolean colocada = casillaEn(coordenadas).admitirUnidad(unidad);
        if (!colocada){
            throw new RuntimeException("No se puede colocar la unidad en una casilla ocupada o no transitable");
        }
        if (!unidades.contains(unidad)) {
            unidades.add(unidad);
        }
    }

    public void moverUnidad(Unidad unidad, Direccion direccion, int distancia) {
        validarExistenciaUnidad(unidad);
        unidad.verificarRangoDeMovimiento(distancia);
        Casilla casillaInicial = casillaDe(unidad);


        for (int i=1; i<=distancia; i++) {
            Casilla origen = casillaDe(unidad);
            Posicion siguientePosicion = origen.siguienteEn(direccion);

            if (!esCoordenadaValida(siguientePosicion)) {
                break;
            }

            Casilla destino = casillaEn(siguientePosicion);
            boolean seTransifirio = origen.transferirUnidadA(destino);
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
        validarExistenciaUnidad(unidad);
        Casilla origenActual = casillaDe(unidad);
        Casilla destinoAnterior = casillaEn(posicionAnterior);
        origenActual.transferirUnidadA(destinoAnterior);
    }

    public void atacarDesdeUnidad(Unidad unidad, Arma arma, Posicion objetivo) {
        validarExistenciaUnidad(unidad);
        casillaDe(unidad).ejecutarAtaque(arma, objetivo, this);
    }

    public void aplicarGolpe(Posicion posicion, int danio, Direccion direccion) {
        casillaEn(posicion).recibirGolpe(danio, direccion, this);
    }

    public void aplicarDisparo(Posicion objetivo,int danio){
        casillaEn(objetivo).recibirDisparo(danio);
    }

    public void empujarDesde(Posicion origen, Direccion direccion) {
        Posicion destino = direccion.calcularSiguientePosicion(origen);

        if (esCoordenadaValida(origen) && esCoordenadaValida(destino)) {
            Casilla casillaOrigen = casillaEn(origen);
            Casilla casillaDestino = casillaEn(destino);
            casillaDestino.recibirEmpuje(casillaOrigen);
        }
    }

    public void ataqueEnLinea(Posicion origen, int danio, Direccion direccion) {
        Posicion actual = direccion.calcularSiguientePosicion(origen);
        while (esCoordenadaValida(actual)) {
            Casilla casillaActual = casillaEn(actual);
            if (casillaActual.detieneDisparo()) {
                casillaActual.recibirGolpe(danio, direccion, this);
                return;
            }
            actual = direccion.calcularSiguientePosicion(actual);
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
                .orElseThrow(() -> new RuntimeException("La unidad no está en el tablero"));
    }

    private Casilla casillaEn(Posicion posicion){
        return casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Coordenada fuera del tablero"));
    }

    private void validarExistenciaUnidad(Unidad unidad) {
        if (!unidades.contains(unidad)) {
            throw new RuntimeException("Unidad no existe");
        }
    }

    private void validarCoordenada(Posicion posicion) {
        if (!esCoordenadaValida(posicion)) {
            throw new RuntimeException("Coordenada fuera del tablero");
        }
    }
}
