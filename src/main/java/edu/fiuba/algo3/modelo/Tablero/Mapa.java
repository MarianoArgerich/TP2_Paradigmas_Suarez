package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Visitante.Visitante;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.CasillaNula;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.redElectrica.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private final List<Casilla> casillas;
    private final RedElectrica redElectrica;

    public Mapa(List<Casilla> casillas) {
        this.casillas = casillas;
        this.redElectrica= new RedElectrica();
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
        casillaEn(posicion).colocarUnidad(unidad);
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

    public boolean edificioEn(Posicion posicion){return casillaEn(posicion).tieneOcupante(new Edificio());}

    public boolean mechEn(Posicion posicion){return casillaEn(posicion).tieneMech();}

    public boolean vekEn(Posicion posicion){return casillaEn(posicion).tieneVek();}

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
                casillaActual.transferirUnidadA(casillaSiguiente, unidad);
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

    public boolean puedeTerminarVekEn(Vek vek, Posicion candidata) {
        return casillaEn(candidata).puedeTerminarVek(vek);
    }

    public List<Posicion> obtenerPosicionesDeEdificios() {
        List<Posicion> posiciones = new ArrayList<>();
        for (Casilla casilla : casillas) {
            casilla.agregarPosicionSiTieneEdificio(posiciones);
        }
        return posiciones;
    }

    public boolean vekAmenazaEnDireccion(Vek atacante, Direccion direccion, Posicion posicionObjetivo, Tablero tablero) {
        for (Casilla casilla : casillas) {
            if (casilla.amenazaPosicion(atacante, direccion, posicionObjetivo, tablero)) {
                return true;
            }
        }
        return false;
    }

    public void ejecutarAtaqueVek(Vek atacante, Direccion direccion, Tablero tablero) {
        for (Casilla casilla : casillas) {
            casilla.ejecutarAtaqueVek(atacante, direccion, tablero);
        }
    }


    public void contaminar(Posicion objetivo) {
        casillaEn(objetivo).contaminar();
    }

    public void reducirRedElectrica() {
        this.redElectrica.reducirEnergia();
    }

    public boolean tieneEnergia(int energia) {
        return this.redElectrica.suEnergiaEsIgualA(energia);
    }

    public boolean esAptaParaEmergencia(Posicion posicion) {
        return casillaEn(posicion).esAptoParaEmergencia();
    }

    public List<Posicion> obtenerPosicionesDeSueloLibre(){
        List<Posicion> posiciones = new ArrayList<>();
        for (Casilla casilla : casillas) {
            casilla.agregarPosicionSiEsAptaParaEmergencia(posiciones);
        }
        return posiciones;
    }
}
