package edu.fiuba.algo3.modelo.Turno.Fases;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.Emergencia.GeneradorEmergencias;
import edu.fiuba.algo3.modelo.Turno.Fases.Emergencia.GeneradorEmergenciasAleatorio;
import edu.fiuba.algo3.modelo.Turno.Fases.Emergencia.GeneradorEmergenciasFijo;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;


public class FaseVek extends Fase {

    private final GeneradorEmergencias generadorEmergencias;


    public FaseVek() {
        this(new GeneradorEmergenciasAleatorio());
    }

    public FaseVek(Posicion posicion, Vek vek) {
        this(new GeneradorEmergenciasFijo(posicion, vek));
    }

    public FaseVek(GeneradorEmergencias generadorEmergencias) {
        this.generadorEmergencias = generadorEmergencias;
    }

    @Override
    public Fase avanzar(Tablero tablero) {
        tablero.ejecutarTurnoVeks();
        this.generadorEmergencias.generarEn(tablero);
        tablero.reiniciarTurnoMechs();
        return new FaseJugador();
    }
}
