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
    public AccionIA evaluar(Vek vek, Posicion origen, Tablero tablero){
        this.accionEncontrada =new AccionNula();
        List<Desplazamiento> desplazamientos = vek.obtenerDesplazamientosValidos(origen, tablero);
        //TODO: VIola tell don't ask esValida
        for (Desplazamiento desplazamiento : desplazamientos) {
            List<Posicion> atacables = vek.obtenerCasillasAtacablesDesde(desplazamiento.destino(), tablero);
            for (Posicion candidataObjetivo : atacables) {
                this.desplazamientoActual = desplazamiento;
                this.objetivoActual = candidataObjetivo;
                tablero.aplicarEnOcupante(candidataObjetivo, this);
                //lo comento de momento porque no se bien como arreglarlo
                //if (this.accionEncontrada.esValida()) {
                    return new AccionAtacar(desplazamiento, candidataObjetivo);
                //}
            }
        }
        return new AccionNula();
    }

    protected void confirmarObjetivo() {
        this.accionEncontrada = new AccionAtacar(this.desplazamientoActual, this.objetivoActual);
    }
}
