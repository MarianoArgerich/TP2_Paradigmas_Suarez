package edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;

import java.util.List;

public class AccionAtacar extends AccionMover {

    private final Posicion objetivoAtaque;

    public AccionAtacar(Desplazamiento desplazamiento, Posicion objetivoAtaque) {
        super(desplazamiento);
        this.objetivoAtaque = objetivoAtaque;
    }

    @Override
    public boolean ejecutar(Vek vek, Tablero tablero){
        super.ejecutar(vek, tablero);
        Direccion direccionAtaque = desplazamiento.destino().calcularDireccionHacia(this.objetivoAtaque);
        tablero.anunciarAtaque(vek, direccionAtaque);
        return true;
    }
}
