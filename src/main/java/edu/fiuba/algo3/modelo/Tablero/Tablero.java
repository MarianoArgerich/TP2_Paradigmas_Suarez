package edu.fiuba.algo3.modelo.Tablero;


import edu.fiuba.algo3.modelo.Turno.Fases.EmergenciaAnunciada;
import edu.fiuba.algo3.modelo.Turno.Fases.SinEmergencia;
import edu.fiuba.algo3.modelo.Turno.Fases.AparicionVek;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.Visitante.Visitante.*;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.CasillaInvalidaException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.redElectrica.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.AtaqueAnunciado;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        try {
            casillaEn(posicion).aceptar(visitante, this);
        } catch (CasillaInvalidaException e){
        }
    }

    private Casilla casillaEn(Posicion posicion) {
        return casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .orElseThrow(() -> new CasillaInvalidaException("No se encontro la casilla con esa posicion"));
    }

    public void transferirOcupanteA(Casilla origen, Posicion destino){
        try {
            origen.transferirOcupanteA(casillaEn(destino));
        }
        catch (CasillaInvalidaException e){}
    }

    public void colocarUnidad(Posicion posicion, Unidad unidad) {
        try {
            casillaEn(posicion).colocarOcupante(unidad);
        }catch (CasillaInvalidaException e){
        }
    }

    public void mover(Posicion origen, Direccion direccion, int distancia){
        try {
            casillaEn(origen).moverOcupante(direccion, distancia, this);
        }
        catch (CasillaInvalidaException e){
        }
    }

    public void atacar(Posicion origen, Posicion objetivo) {
        try {
            casillaEn(origen).ejecutarAtaque(objetivo, this);
        }
        catch (CasillaInvalidaException e){
        }
    }

    public boolean unidadEstaEn(Unidad unidad, Posicion posicion) {
        try {
            return casillaEn(posicion).tieneUnidad(unidad);
        } catch (CasillaInvalidaException e){
            return false;
        }
    }

    public void anunciarAtaque(Vek atacante, Direccion direccion) {
        this.ataquesAnunciados.add(new AtaqueAnunciado(atacante, direccion));

    }

    public void deshacerMovimiento(Posicion posicionActual) {
        try {
            casillaEn(posicionActual).deshacerMovimientoOcupante(this);
        }
        catch (CasillaInvalidaException e){
        }
    }

    public void reducirRedElectrica() {
        this.redElectrica.reducirEnergia();
    }

    public List<Posicion> obtenerPosicionesDeEdificios() {
        List<Posicion> posiciones = new ArrayList<>();
        for (Casilla casilla : casillas) {
            casilla.agregarPosicionDeEdificioA(posiciones);
        }
        return posiciones;
    }

    public void resolverAtaques() {
        for (AtaqueAnunciado ataque : ataquesAnunciados) {
            ataque.resolver(this);
        }
        ataquesAnunciados.clear();
    }

    private Casilla casillaDe(Unidad unidad) {
        return casillas.stream()
                .filter(casilla -> casilla.tieneUnidad(unidad))
                .findFirst()
                .orElseThrow(() -> new CasillaInvalidaException("No se encontro la casilla con esa unidad"));
    }

    public boolean vekAmenazaEnDireccion(Vek atacante, Direccion direccion, Posicion posicionObjetivo) {
        try {
            return casillaDe(atacante).amenazaPosicion(atacante, direccion, posicionObjetivo, this);
        }
        catch (CasillaInvalidaException e){
            return false;
        }
    }

    public void ejecutarAtaqueVek(Vek atacante, Direccion direccion) {
        try {
            casillaDe(atacante).ejecutarAtaqueVek(atacante, direccion, this);
        }
        catch (CasillaInvalidaException e){
        }
    }

    public void resolverEmergencia() {
        this.emergenciaActual.resolver(this);
        this.emergenciaActual = new SinEmergencia();
    }

    public void anunciarEmergencia(Vek vek) {
        List<Posicion> candidatas = obtenerPosicionesDisponiblesParaEmergencia();
        if(candidatas.isEmpty()){
            return;
        }
        Collections.shuffle(candidatas, new Random());

        try {
            casillaEn(candidatas.get(0)).validarEmergencia();
            this.emergenciaActual = new EmergenciaAnunciada(candidatas.get(0), vek);
        }
        catch (CasillaInvalidaException e){
        }
    }

    public void anunciarEmergencia(Posicion posicion, Vek vek) {
        try {
            casillaEn(posicion).validarEmergencia();
            this.emergenciaActual = new EmergenciaAnunciada(posicion, vek);
        } catch (CasillaInvalidaException e) {}
    }

    public List<Posicion> obtenerPosicionesDisponiblesParaEmergencia(){
        List<Posicion> posiciones = new ArrayList<>();
        for (Casilla casilla : casillas) {
            casilla.agregarPosicionCandidataParaEmergenciaA(posiciones);
        }
        return posiciones;
    }

    public void aplicarEnOcupante(Posicion posicion, SegundoVisitante visitante) {
        try {
            casillaEn(posicion).aceptar(visitante);
        }catch (CasillaInvalidaException e){
        }
    }
}
