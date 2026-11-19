package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
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
            if (accion.esValida()) {
                accion.ejecutar(this, tablero);
                return;
            }
        }
    }

    public List<Desplazamiento> obtenerDesplazamientosValidos(Posicion origen, Tablero tablero) {
        List<Desplazamiento> desplazamientos = new ArrayList<>();
        desplazamientos.add(Desplazamiento.sinMover(origen));

        List<Desplazamiento> movimientosRectos = new ArrayList<>();
        for (Direccion direccion : direccionesOrtogonales()) {
            Posicion actual = origen;
            int distancia = 1;

            while (estaDentroDelRango(distancia)) {
                actual = actual.siguienteEn(direccion);
                if (!tablero.puedeTerminarVekEn(this, actual)) {
                    break;
                }
                movimientosRectos.add(new Desplazamiento(origen, direccion, distancia));
                distancia++;
            }
        }

        ordenarDesplazamientosDeterministicamente(movimientosRectos);
        desplazamientos.addAll(movimientosRectos);
        return desplazamientos;
    }

    private void ordenarDesplazamientosDeterministicamente(List<Desplazamiento> movimientos) {
        for (int i = 0; i < movimientos.size() - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < movimientos.size(); j++) {
                if (movimientos.get(j).esMenorQue(movimientos.get(indiceMenor))) {
                    indiceMenor = j;
                }
            }
            Desplazamiento temporal = movimientos.get(i);
            movimientos.set(i, movimientos.get(indiceMenor));
            movimientos.set(indiceMenor, temporal);
        }
    }

    protected void ordenarDeterministicamente(List<Posicion> posiciones) {
        for (int i = 0; i < posiciones.size() - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < posiciones.size(); j++) {
                if (posiciones.get(j).esMenorQue(posiciones.get(indiceMenor))) {
                    indiceMenor = j;
                }
            }
            Posicion temporal = posiciones.get(i);
            posiciones.set(i, posiciones.get(indiceMenor));
            posiciones.set(indiceMenor, temporal);
        }
    }

    public List<Posicion> obtenerCasillasAtacablesDesde(Posicion origen, Tablero tablero){
        List<Posicion> atacables = new ArrayList<>();
        for (Direccion direccion : direccionesOrtogonales()) {
            atacables.add(origen.siguienteEn(direccion));
        }
        ordenarDeterministicamente(atacables);
        return atacables;
    }


    public boolean puedeTerminarEn(Terreno terreno){
        return terreno.esSeguroParaTerrestre();
    }


    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {
        if (!origen.esAdyacenteA(objetivo)) {
            throw new AtaqueInvalidoException(
                    "El objetivo debe estar en una casilla adyacente"
            );
        }

        Direccion direccion = origen.calcularDireccionHacia(objetivo);
        this.resolverAtaque(origen, direccion, tablero);
    }

    @Override
    public boolean esVek() {
        return true;
    }

    public boolean amenazaDesde(Posicion origen, Direccion direccion, Posicion objetivo,Tablero tablero) {
        return origen.siguienteEn(direccion).equals(objetivo);
    }

    public void resolverAtaque(Posicion posicionActual, Direccion direccion, Tablero tablero) {
        Posicion objetivo = posicionActual.siguienteEn(direccion);
        tablero.aplicarDisparo(objetivo, this.danio);
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

