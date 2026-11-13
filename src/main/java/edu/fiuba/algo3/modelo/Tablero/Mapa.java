package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Visitante.Visitante;
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

    public void aplicarEfecto(Posicion posicion, Visitante visitante){
        casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .ifPresent(casilla -> casilla.aceptar(visitante, this));
        //TODO: revisar si en vez de usar casilla.estaEn(posicion) se puede hacer con un equals
    }

    public boolean esCoordenadaValida(Posicion posicion) {
        return casillas.stream().anyMatch(casilla -> casilla.estaEn(posicion));
        //TODO: lo mismo que arriba
    }

    public Casilla casillaEn(Posicion posicion) {
        return casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .orElse(new CasillaNula());
        //TODO: Idem
    }

    public Casilla casillaDe(Unidad unidad) {
        return casillas.stream()
                .filter(casilla -> casilla.tieneUnidad(unidad))
                //TODO: revisar tambien si se puede sacar el casilla.tieneUnidad(unidad)
                .findFirst()
                .orElseThrow(() -> new UnidadInvalidaException("La unidad no está en el tablero"));
    }

    //TODO: Chequear
    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return esCoordenadaValida(posicion) && casillaEn(posicion).tieneUnidad(unidad);
    }
}
