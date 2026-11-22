package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Disparo;
import edu.fiuba.algo3.modelo.Visitante.Visitante.ExploradorMovimientoVek;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionIA;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAcercarseAEdificio;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAtacarEdificio;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAtacarMech;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaIA;

import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.unidades.capacidades.Atacante;

import java.util.ArrayList;
import java.util.List;

public abstract class Vek extends Unidad implements Atacante {
    protected int danio;
    private final List<ReglaIA> reglasPrioridad;

    protected Vek(int vidaMax, int rangoDeMovimiento, int danio) {
        super(vidaMax, rangoDeMovimiento);
        this.danio = danio;
        this.reglasPrioridad = List.of(
                new ReglaAtacarEdificio(),
                new ReglaAtacarMech(),
                new ReglaAcercarseAEdificio()
        );
    }

    protected List<Direccion> direccionesOrtogonales() {
        return List.of(new Arriba(), new Abajo(), new Derecha(), new Izquierda());
    }

    public void ejecutarTurnoIA(Posicion posicionActual, Tablero tablero) {
        for (ReglaIA regla : reglasPrioridad) {
            AccionIA accion = regla.evaluar(this, posicionActual, tablero);
            if(accion.ejecutar(this, tablero)){
                return;
            }
        }
    }

    public List<Desplazamiento> obtenerDesplazamientosValidos(Posicion origen, Tablero tablero) {
        List<Desplazamiento> desplazamientos = new ArrayList<>();
        desplazamientos.add(Desplazamiento.sinMover(origen));

        List<Desplazamiento> movimientosRectos = new ArrayList<>();
        for (Direccion direccion : direccionesOrtogonales()) {
            ExploradorMovimientoVek explorador = new ExploradorMovimientoVek(this, origen, direccion, this.rangoDeMovimiento, movimientosRectos);
            explorador.explorarDesde(origen, tablero);
        }

        movimientosRectos.sort((a, b) -> a.esMenorQue(b) ? -1 : (b.esMenorQue(a) ? 1 : 0));
        desplazamientos.addAll(movimientosRectos);
        return desplazamientos;
    }

    public void alExplorarAgua(ExploradorMovimientoVek explorador, Casilla casillaActual, Tablero tablero) {}

    public void alExplorarAcido(ExploradorMovimientoVek explorador, Casilla casillaActual, Tablero tablero) {}

    public List<Posicion> obtenerCasillasAtacablesDesde(Posicion origen, Tablero tablero){
        List<Posicion> atacables = new ArrayList<>();
        for (Direccion direccion : direccionesOrtogonales()) {
            atacables.add(calcularObjetivoEn(origen, direccion, tablero));
        }
        atacables.sort((a, b) -> a.esMenorQue(b) ? -1 : (b.esMenorQue(a) ? 1 : 0));
        return atacables;
    }

    protected Posicion calcularObjetivoEn(Posicion origen, Direccion direccion, Tablero tablero) {
        return origen.siguienteEn(direccion);
    }

    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {

        Direccion direccion = origen.calcularDireccionHacia(objetivo);
        this.resolverAtaque(origen, direccion, tablero);
    }

    public boolean amenazaDesde(Posicion origen, Direccion direccion, Posicion objetivo,Tablero tablero) {
        return calcularObjetivoEn(origen, direccion, tablero).equals(objetivo);
    }

    public void resolverAtaque(Posicion posicionActual, Direccion direccion, Tablero tablero) {
        Posicion objetivo = posicionActual.siguienteEn(direccion);
        tablero.aplicarEfecto(objetivo, new Disparo(danio));
    }

    @Override
    public void caerAlAguaEn(Casilla casilla) {
        casilla.vaciar();
    }

    @Override
    public void aceptar(SegundoVisitante visitante) {
        visitante.visitar(this);
    }


}

