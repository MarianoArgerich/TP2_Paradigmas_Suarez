package edu.fiuba.algo3.modelo.unidades.estadoContaminacion;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class ContaminadoPorAcido implements EstadoContaminacion {


    @Override
    public void contaminar(Unidad unidad) {}

    @Override
    public void contaminarPorAcido(Unidad unidad) {}

    @Override
    public void salirDeAcido(Unidad unidad) {
        unidad.cambiarEstadoContaminacion(new NoContaminado());
    }

    @Override
    public void recibirDanio(Unidad unidad, int danio, Casilla casillaActual) {
        unidad.aplicarDanio(danio * 2, casillaActual);
    }


}
