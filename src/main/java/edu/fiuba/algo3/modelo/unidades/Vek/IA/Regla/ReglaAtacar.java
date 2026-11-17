package edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionAtacar;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionIA;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionNula;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public abstract class ReglaAtacar implements ReglaIA {

    @Override
    public AccionIA evaluar(Vek vek, Posicion origen, Tablero tablero){
        List<Desplazamiento> desplazamientos = vek.obtenerDesplazamientosValidos(origen, tablero);

        for (Desplazamiento desplazamiento : desplazamientos) {
            List<Posicion> atacables = vek.obtenerCasillasAtacablesDesde(desplazamiento.destino(), tablero);
            for (Posicion candidataObjetivo : atacables) {
                if (esObjetivoValido(candidataObjetivo, tablero)) {
                    return new AccionAtacar(desplazamiento, candidataObjetivo);
                }
            }
        }
        return new AccionNula();
    }

    protected abstract boolean esObjetivoValido(Posicion objetivo, Tablero tablero);
}
