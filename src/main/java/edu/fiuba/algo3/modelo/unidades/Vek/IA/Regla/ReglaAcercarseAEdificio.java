package edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionIA;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionMover;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionNula;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class ReglaAcercarseAEdificio implements ReglaIA {

    @Override
    public AccionIA evaluar(Vek vek, Posicion origen, Tablero tablero){
        List<Posicion> edificios = tablero.obtenerPosicionesDeEdificios();
        if(edificios.isEmpty()){
            return new AccionNula();
        }
        List<Desplazamiento> desplazamientos = vek.obtenerDesplazamientosValidos(origen, tablero);
        Desplazamiento mejorDesplazamiento = desplazamientos.get(0);
        int menorDistancia = distanciaAlEdificioMasCercano(origen, edificios);

        for (Desplazamiento candidato : desplazamientos){
            int distancia = distanciaAlEdificioMasCercano(candidato.destino(), edificios);
            if (distancia < menorDistancia){
                mejorDesplazamiento = candidato;
                menorDistancia = distancia;
            }
        }

        if (mejorDesplazamiento.destino().equals(origen)) {
            return new AccionNula();
        }

        return new AccionMover(mejorDesplazamiento);
    }

    private int distanciaAlEdificioMasCercano(Posicion posicion, List<Posicion> edificios){
        int menorDistancia = Integer.MAX_VALUE;
        for (Posicion edificio : edificios){
            int distancia = posicion.distanciaA(edificio);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
            }
        }
        return menorDistancia;
    }



}
