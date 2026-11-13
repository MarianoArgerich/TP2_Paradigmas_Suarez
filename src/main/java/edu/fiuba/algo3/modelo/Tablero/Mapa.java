package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.CasillaNula;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
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
    }

    public Casilla casillaEn(Posicion posicion) {
        return casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .orElse(new CasillaNula());
    }

    public void colocarUnidad(Posicion posicion, Unidad unidad) {
        casillaEn(posicion).colocarOcupante(unidad);
    }

    public void mover(Posicion origen, Direccion direccion, int distancia){
        casillaEn(origen).moverOcupante(direccion, distancia, this);
    }

    public void deshacerMovimiento(Posicion posicion){
        casillaEn(posicion).deshacerMovimientoOcupante(this);
    }

    public boolean detieneDisparoEn(Posicion posicion){
        return casillaEn(posicion).detieneDisparo();
    }

    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero){
        casillaEn(origen).ejecutarAtaque(objetivo, tablero);
    }

    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return casillaEn(posicion).tieneUnidad(unidad);
    }

    private Casilla siguienteCasilla(Casilla origen, Direccion direccion) {
        return casillas.stream()
                .filter(casilla -> origen.esSiguienteEn(direccion, casilla))
                .findFirst()
                .orElse(new CasillaNula());
    }

    public void desplazarUnidad(Casilla origen, Unidad unidad, Direccion direccion, int distancia){
        Casilla casillaActual = origen;

        for (int i = 0; i < distancia; i++){
            Casilla casillaSiguiente = siguienteCasilla(casillaActual, direccion);
            try {
                casillaActual.transferirOcupanteA(casillaSiguiente);
            } catch (CasillaInvalidaException e) {
                if (!casillaActual.equals(origen)) {
                    casillaActual.transferirOcupanteA(origen);
                }
                return;
            }
            casillaActual = siguienteCasilla(casillaActual, direccion);
        }
        origen.notificarMovimientoA(unidad);
    }
}
