package edu.fiuba.algo3.modelo.Visitante.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class ExploradorMovimientoVek extends Visitante {
    private final Vek vek;
    private final Posicion origen;
    private final Direccion direccion;
    private final int rangoMax;
    private final List<Desplazamiento> movimientos;
    private int distanciaActual = 1;

    public ExploradorMovimientoVek(Vek vek, Posicion origen, Direccion direccion, int rangoMax, List<Desplazamiento> movimientos) {
        this.vek = vek;
        this.origen = origen;
        this.direccion = direccion;
        this.rangoMax = rangoMax;
        this.movimientos = movimientos;
    }

    public void explorarDesde(Posicion posicionActual, Tablero tablero) {
        if (this.distanciaActual <= this.rangoMax){
            tablero.aplicarEfecto(posicionActual.siguienteEn(this.direccion), this);
        }
    }

    public void aceptarPasoYContinuar(Casilla casillaActual, Tablero tablero) {
        this.movimientos.add(new Desplazamiento(this.origen, this.direccion, this. distanciaActual));
        this.distanciaActual++;
        if (this.distanciaActual <= this.rangoMax) {
            tablero.aplicarEfecto(casillaActual.siguienteEn(this.direccion), this);
        }
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        terreno.evaluarMovimientoVek(this.vek, this, casillaActual, tablero);
    }

    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Tablero tablero) {
    }

    @Override
    public void visitar(Montania montania, Terreno terreno, Casilla casillaActual, Tablero tablero) {
    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual, Tablero tablero) {
    }
}
