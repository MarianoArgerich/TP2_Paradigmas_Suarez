package edu.fiuba.algo3.modelo.unidades.estadoContaminacion;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class NoContaminado implements EstadoContaminacion{
    @Override
    public void contaminar(Unidad unidad) {
        unidad.cambiarEstadoContaminacion(new ContaminadoPorLuciernaga());
    }

    @Override
    public void contaminarPorAcido(Unidad unidad) {
        unidad.cambiarEstadoContaminacion(new ContaminadoPorAcido());
    }

    @Override
    public void salirDeAcido(Unidad unidad) {}

    @Override
    public void recibirDanio(Unidad unidad, int danio, Casilla casillaActual) {
        unidad.aplicarDanio(danio, casillaActual);
    }

    @Override
    public int modificarDanio(int danio) {
        return danio;
    }
}
