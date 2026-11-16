package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class Luciernaga extends Vek {
    public Luciernaga() {
        super(3, 2, 1);
    }

    @Override
    public void deshacerMovimientoDesde(Casilla casilla, Mapa mapa) {
    }

    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {

        if (!origen.estaEnLineaRectaCon(objetivo)) {
            throw new AtaqueInvalidoException("La luciernaga solo puede atacar en linea recta");
        }

        Direccion direccion = origen.calcularDireccionHacia(objetivo);
        Posicion actual = origen.siguienteEn(direccion);

        while (!tablero.detieneDisparoEn(actual)){
            actual = actual.siguienteEn(direccion);
        }
        tablero.aplicarDisparo(actual, this.danio);

        tablero.contaminar(objetivo);
    }

}
