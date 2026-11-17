package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneradorEmergenciasAleatorio implements GeneradorEmergencias {
    private final Random random = new Random();


    @Override
    public void generarEn(Tablero tablero) {
        List<Posicion> candidatas = tablero.obtenerPosicionesDisponiblesParaEmergencia();
        if(candidatas.isEmpty()){
            return;
        }
        //Mezcla las posiciones de candidatas
        Collections.shuffle(candidatas, random);
        tablero.anunciarEmergencia(candidatas.get(0), crearVekAleatorio());
    }

    private Vek crearVekAleatorio() {
        int tipo = random.nextInt(3);
        if (tipo == 0) return new  Luciernaga();
        if (tipo == 1) return new Saltador();
        return new Avispon();
    }

}
