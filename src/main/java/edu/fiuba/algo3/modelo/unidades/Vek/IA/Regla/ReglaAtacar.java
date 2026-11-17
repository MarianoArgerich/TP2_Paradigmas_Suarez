package edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionAtacar;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionIA;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionNula;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public abstract class ReglaAtacar implements ReglaIA {

    @Override
    public AccionIA evaluar(Vek vek, Posicion origen, Tablero tablero){
        List<Posicion> destinos = vek.obtenerDestinosValidos(origen, tablero);

        for (Posicion destino : destinos) {
            List<Posicion> atacables = vek.obtenerCasillasAtacablesDesde(destino, tablero);
            for (Posicion candidataObjetivo : atacables) {
                if (esObjetivoValido(candidataObjetivo, tablero)) {
                    List<Direccion> camino = vek.calcularCaminoHasta(origen, destino, tablero);
                    return new AccionAtacar(origen, camino, candidataObjetivo);
                }
            }
        }
        return new AccionNula();
    }

    protected abstract boolean esObjetivoValido(Posicion objetivo, Tablero tablero);
}
