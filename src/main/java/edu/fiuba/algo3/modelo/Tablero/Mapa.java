package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Efecto.Efecto;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.CasillaNula;
import edu.fiuba.algo3.modelo.excepciones.UnidadInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.List;

public class Mapa {
    private final List<Casilla> casillas;

    public Mapa(List<Casilla> casillas) {
        this.casillas = casillas;
    }

    public void aplicarEfecto(Posicion posicion, Efecto efecto){
        casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .ifPresent(casilla -> casilla.aceptar(efecto, this));
    }

    public boolean esCoordenadaValida(Posicion posicion) {
        return casillas.stream().anyMatch(casilla -> casilla.estaEn(posicion));
    }

    public Casilla casillaEn(Posicion posicion) {
        return casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .orElse(new CasillaNula());
    }

    public Casilla casillaDe(Unidad unidad) {
        return casillas.stream()
                .filter(casilla -> casilla.tieneUnidad(unidad))
                .findFirst()
                .orElseThrow(() -> new UnidadInvalidaException("La unidad no está en el tablero"));
    }

    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return esCoordenadaValida(posicion) && casillaEn(posicion).tieneUnidad(unidad);
    }
}
