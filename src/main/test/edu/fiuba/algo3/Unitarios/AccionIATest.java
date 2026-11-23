package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.posicion.Desplazamiento;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionAtacar;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionIA;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccionIATest {

    @Test
    public void testAccionAtacarMueveAlVek() {
        Tablero tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(2, 4), new Edificio());
        Saltador saltador = new Saltador();
        Posicion origen = new Posicion(2, 2);
        tablero.colocarUnidad(origen, saltador);

        AccionIA atacar = new AccionAtacar(
                new Desplazamiento(origen, new Arriba(), 1),
                new Posicion(2, 4)
        );
        atacar.ejecutar(saltador, tablero);

        assertTrue(tablero.unidadEstaEn(saltador, new Posicion(2, 3)));
    }

    @Test
    public void testAccionAtacarAlMoverAlVekAnunciaSuAtaque() {
        Tablero tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(2, 4), new Edificio());
        Saltador saltador = new Saltador();
        Posicion origen = new Posicion(2, 2);
        tablero.colocarUnidad(origen, saltador);

        AccionIA atacar = new AccionAtacar(
                new Desplazamiento(origen, new Arriba(), 1),
                new Posicion(2, 4)
        );
        atacar.ejecutar(saltador, tablero);

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 4)));
    }
}
