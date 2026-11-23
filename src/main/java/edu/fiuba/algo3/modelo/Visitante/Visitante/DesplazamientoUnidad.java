 package edu.fiuba.algo3.modelo.Visitante.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class DesplazamientoUnidad extends Visitante {
    private final Casilla origen;
    private final Unidad unidad;
    private final Direccion direccion;
    private int pasosRestantes;

    public DesplazamientoUnidad(Casilla origen, Unidad unidad, Direccion direccion, int distancia){
        this.origen = origen;
        this.unidad = unidad;
        this.direccion = direccion;
        this.pasosRestantes = distancia;
    }

    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        if (this.pasosRestantes == 1){
            this.origen.transferirOcupanteA(casillaActual);
            this.origen.notificarMovimientoA(this.unidad);
        } else {
            this.pasosRestantes--;
            tablero.aplicarEfecto(casillaActual.siguienteEn(this.direccion), this);
        }
    }
}
