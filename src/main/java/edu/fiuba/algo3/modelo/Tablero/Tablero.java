package edu.fiuba.algo3.modelo.Tablero;


import edu.fiuba.algo3.modelo.Turno.Fases.EmergenciaAnunciada;
import edu.fiuba.algo3.modelo.Turno.Fases.SinEmergencia;
import edu.fiuba.algo3.modelo.Turno.Fases.AparicionVek;
import edu.fiuba.algo3.modelo.Visitante.Visitante.*;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.CasillaNula;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.redElectrica.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Ataque.DisparoAcido;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.AtaqueAnunciado;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;


import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private final List<Casilla> casillas;
    private final RedElectrica redElectrica;
    private final List<AtaqueAnunciado> ataquesAnunciados = new ArrayList<>();
    private AparicionVek emergenciaActual = new SinEmergencia();

    public Tablero(List<Casilla> casillas){
        this.casillas = casillas;
        this.redElectrica = new RedElectrica();
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
        casillaEn(origen).moverOcupante(direccion, distancia, this);;
    }

    public void aplicarGolpe(Posicion posicion, int danio, Direccion direccion) {
        this.aplicarEfecto(posicion, new Golpe(danio, direccion));
    }

    public void aplicarDisparoAcido(Posicion objetivo, int danio) {
        this.aplicarEfecto(objetivo, new DisparoAcido(danio));
    }

    public void aplicarDisparo(Posicion objetivo,int danio){
        this.aplicarEfecto(objetivo, new Disparo(danio));
    }

    public void empujarDesde(Posicion origen, Direccion direccion) {
        this.aplicarEfecto(origen, new Empuje(direccion));
    }

    public void atacar(Posicion origen, Posicion objetivo) {
        casillaEn(origen).ejecutarAtaque(objetivo, this);;
    }

    public boolean detieneDisparoEn(Posicion posicion) {return casillaEn(posicion).detieneDisparo();
    }


    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        return casillaEn(posicion).tieneUnidad(unidad);
    }

    public boolean edificioEn(Posicion posicion) {return casillaEn(posicion).tieneOcupante(new Edificio());}

    public boolean mechEn(Posicion posicion) {return casillaEn(posicion).tieneMech();}

    public boolean vekEn(Posicion posicion) {return casillaEn(posicion).tieneVek();}

    public boolean posicionValida(Posicion posicion) {
        return casillaEn(posicion).estaEn(posicion);
    }

    public void anunciarAtaque(Vek atacante, Direccion direccion) {
        this.ataquesAnunciados.add(new AtaqueAnunciado(atacante, direccion));

    }

    public void deshacerMovimiento(Posicion posicionActual) {
        casillaEn(posicionActual).deshacerMovimientoOcupante(this);
    }

    public void contaminar(Posicion posicion) {
        casillaEn(posicion).contaminar();
    }

    public boolean tieneEnergia(int energia) {
        return this.redElectrica.suEnergiaEsIgualA(energia);
    }

    public void reducirRedElectrica() {
        this.redElectrica.reducirEnergia();
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


    public boolean tieneAtaqueAnunciadoEn(Posicion posicion) {
        for (AtaqueAnunciado ataque : ataquesAnunciados) {
            if (ataque.amenazaA(posicion, this)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayAtaquesAnunciados() {
        return !ataquesAnunciados.isEmpty();
    }

    public void resolverAtaques() {
        for (AtaqueAnunciado ataque : ataquesAnunciados) {
            ataque.resolver(this);
        }
        ataquesAnunciados.clear();
    }

    public boolean vekAmenazaEnDireccion(Vek atacante, Direccion direccion, Posicion posicionObjetivo) {
        for (Casilla casilla : casillas) {
            if (casilla.amenazaPosicion(atacante, direccion, posicionObjetivo, this)) {
                return true;
            }
        }
        return false;
    }

    public void ejecutarAtaqueVek(Vek atacante, Direccion direccion) {
        for (Casilla casilla : casillas) {
            casilla.ejecutarAtaqueVek(atacante, direccion, this);
        }
    }

    public boolean tieneEmergenciaAnunciadaEn(Posicion posicion){
        return this.emergenciaActual.estaEn(posicion);
    }

    public void intentarEmergenciaEn(Posicion posicion, Vek vek){
        aplicarEfecto(posicion, new EmergenciaVek(vek));
    }

    public void resolverEmergencia() {
        this.emergenciaActual.resolver(this);
        this.emergenciaActual = new SinEmergencia();
    }

    public void anunciarEmergencia(Posicion posicion, Vek vek) {
        if (!casillaEn(posicion).esAptoParaEmergencia()) {
            throw new CasillaInvalidaException("No se puede anunciar una emergencia en esa casilla");
        }

        this.emergenciaActual = new EmergenciaAnunciada(posicion, vek);
    }

    public List<Posicion> obtenerPosicionesDisponiblesParaEmergencia(){
        List<Posicion> posiciones = new ArrayList<>();
        for (Casilla casilla : casillas) {
            casilla.agregarPosicionSiEsAptaParaEmergencia(posiciones);
        }
        return posiciones;
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


    private Casilla siguienteCasilla(Casilla origen, Direccion direccion) {
        return casillas.stream()
                .filter(casilla -> origen.esSiguienteEn(direccion, casilla))
                .findFirst()
                .orElse(new CasillaNula());
    }

}
