package edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.SegundoVisitante.SegundoVisitante;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionAtacar;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionIA;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionNula;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public abstract class ReglaAtacar extends SegundoVisitante implements ReglaIA {

    private Desplazamiento desplazamientoActual;
    private Posicion objetivoActual;
    private AccionIA accionEncontrada;

    @Override
    public AccionIA evaluar(Vek vek, Posicion origen, Tablero tablero) {
        this.accionEncontrada = new AccionNula();
        List<Desplazamiento> desplazamientos = vek.obtenerDesplazamientosValidos(origen, tablero);

        for (Desplazamiento desplazamiento : desplazamientos) {
            List<Posicion> atacables = vek.obtenerCasillasAtacablesDesde(desplazamiento.destino(), tablero);
            for (Posicion candidataObjetivo : atacables) {
                this.desplazamientoActual = desplazamiento;
                this.objetivoActual = candidataObjetivo;
                tablero.aplicarEnOcupante(candidataObjetivo, this);
            }
        }
        return this.accionEncontrada;
    }

    protected void confirmarObjetivo () {
        AccionIA cantidada = new AccionAtacar(this.desplazamientoActual, this.objetivoActual);
        this.accionEncontrada = this.accionEncontrada.aceptarCandidata(cantidada);
    }
}
