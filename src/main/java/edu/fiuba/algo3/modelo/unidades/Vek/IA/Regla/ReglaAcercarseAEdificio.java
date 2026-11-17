package edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
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
        List<Posicion> destinos = vek.obtenerDestinosValidos(origen, tablero);
        Posicion mejorDestino = origen;
        int menorDistancia = distanciaAlEdificioMasCercano(origen, edificios);

        for (Posicion candidato : destinos){
            int distancia = distanciaAlEdificioMasCercano(candidato, edificios);
            if (distancia < menorDistancia){
                mejorDestino = candidato;
                menorDistancia = distancia;
            }
        }

        if (mejorDestino.equals(origen)) {
            return new AccionNula();
        }

        List<Direccion> camino = vek.calcularCaminoHasta(origen, mejorDestino, tablero);
        return new AccionMover(origen, camino);
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
