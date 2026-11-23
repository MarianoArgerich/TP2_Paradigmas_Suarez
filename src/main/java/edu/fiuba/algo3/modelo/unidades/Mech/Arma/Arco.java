package edu.fiuba.algo3.modelo.unidades.Mech.Arma;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Disparo;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Empuje;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Ataque.DisparoAcido;

import java.util.List;

public class Arco extends Arma{

    public Arco() {
        this.danio = 1;
    }

    @Override
    public void ejecutarAtaque(Posicion origen, Posicion objetivo, Tablero tablero) {
        origen.validarFueraDeAreaDe(objetivo);
        origen.validarAlineacionCon(objetivo);

        tablero.aplicarEfecto(objetivo, new Disparo(danio));

        List<Direccion> direcciones = List.of(new Arriba(), new Abajo(), new Izquierda(), new Derecha());
        for (Direccion direccion : direcciones) {
            tablero.aplicarEfecto(direccion.calcularSiguientePosicion(objetivo), new Empuje(direccion));
        }
    }

}
