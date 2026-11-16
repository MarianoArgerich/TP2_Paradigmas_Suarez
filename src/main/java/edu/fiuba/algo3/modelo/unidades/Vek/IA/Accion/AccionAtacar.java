package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class AccionAtacar extends AccionMover {
    private final Posicion objetivoAtaque;

    public AccionAtacar(Posicion origen, List<Direccion> camino, Posicion objetivoAtaque) {
        super(origen, camino);
        this.objetivoAtaque = objetivoAtaque;
    }

    @Override
    public void ejecutar(Vek vek, Tablero tablero){
        super.ejecutar(vek, tablero);
        Posicion posicionFinal =obtenerDestino();
        Direccion direccionAtaque = posicionFinal.calcularDireccionHacia(this.objetivoAtaque);
        tablero.anunciarAtaque(vek, direccionAtaque);
    }
}
