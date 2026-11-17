package edu.fiuba.algo3.modelo.unidades.estadoContaminacion;

import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public interface EstadoContaminacion {

     void contaminar(Unidad unidad);

     void contaminarPorAcido(Unidad unidad);

     void salirDeAcido(Unidad unidad);

     void recibirDanio(Unidad unidad, int danio, Casilla casillaActual);


}
