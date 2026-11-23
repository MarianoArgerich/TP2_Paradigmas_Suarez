package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionIA;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAcercarseAEdificio;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAtacarEdificio;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAtacarMech;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReglasIATest {

    @Test
    public void testReglaAtacarEdificioEncuentraYAnunciaAtaqueAlEdificioAlcanzable() {
        Posicion posEdificio = new Posicion(2, 4);
        Tablero tablero = TableroTestFactory.crearConOcupanteEn(posEdificio, new Edificio());
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);

        AccionIA accion = new ReglaAtacarEdificio().evaluar(saltador, new Posicion(2, 2), tablero);
        accion.ejecutar(saltador, tablero);

        assertTrue(tablero.tieneAtaqueAnunciadoEn(posEdificio));
    }

    @Test
    public void testReglaAtacarEdificioNoMueveSiNoHayEdificiosAlAlcance() {
        Tablero tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(7, 7), new Edificio());
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(0, 0), saltador);

        AccionIA accion = new ReglaAtacarEdificio().evaluar(saltador, new Posicion(0, 0), tablero);
        accion.ejecutar(saltador, tablero);

        assertTrue(tablero.unidadEstaEn(saltador, new Posicion(0, 0)));
    }

    @Test
    public void testReglaAtacarEdificioNoAtacaSiNoHayEdificiosAlAlcance() {
        Tablero tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(7, 7), new Edificio());
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(0, 0), saltador);

        AccionIA accion = new ReglaAtacarEdificio().evaluar(saltador, new Posicion(0, 0), tablero);
        accion.ejecutar(saltador, tablero);

        assertFalse(tablero.tieneAtaqueAnunciadoEn(new Posicion(7, 7)));
    }

    @Test
    public void testReglaAtacarMechEncuentraYAnunciaAtaqueAlMechAlcanzable() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Saltador saltador = new Saltador();
        Mech mech = combate();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);
        tablero.colocarUnidad(new Posicion(2, 4), mech);

        AccionIA accion = new ReglaAtacarMech().evaluar(saltador, new Posicion(2, 2), tablero);
        accion.ejecutar(saltador, tablero);

        assertTrue(tablero.tieneAtaqueAnunciadoEn(new Posicion(2, 4)));
    }

    @Test
    public void testReglaAcercarseAEdificioMueveAlVekHaciaElEdificioMasCercano() {
        Posicion edificioCercano = new Posicion(1, 6);
        Posicion edificioLejano = new Posicion(7, 7);
        Tablero tablero = TableroTestFactory.iniciar()
                .conOcupanteEn(edificioCercano, new Edificio())
                .conOcupanteEn(edificioLejano, new Edificio())
                .construir();

        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(1, 1), saltador);

        AccionIA accion = new ReglaAcercarseAEdificio().evaluar(saltador, new Posicion(1, 1), tablero);
        accion.ejecutar(saltador, tablero);

        assertFalse(tablero.tieneAtaqueAnunciadoEn(edificioCercano));
    }

    @Test
    public void testReglaAcercarseAEdificioMueveAlVekHaciaElEdificioMasCercanoPeroNoAtaca() {
        Posicion edificioCercano = new Posicion(1, 6);
        Posicion edificioLejano = new Posicion(7, 7);
        Tablero tablero = TableroTestFactory.iniciar()
                .conOcupanteEn(edificioCercano, new Edificio())
                .conOcupanteEn(edificioLejano, new Edificio())
                .construir();

        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(1, 1), saltador);

        AccionIA accion = new ReglaAcercarseAEdificio().evaluar(saltador, new Posicion(1, 1), tablero);
        accion.ejecutar(saltador, tablero);

        assertFalse(tablero.tieneAtaqueAnunciadoEn(edificioCercano));
    }

    @Test
    public void testReglaAcercarseAEdificioNoMueveAlVekSiNoHayEdificiosEnElTablero() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Saltador saltador = new Saltador();
        tablero.colocarUnidad(new Posicion(2, 2), saltador);

        AccionIA accion = new ReglaAcercarseAEdificio().evaluar(saltador, new Posicion(2, 2), tablero);
        accion.ejecutar(saltador, tablero);

        assertTrue(tablero.unidadEstaEn(saltador, new Posicion(2, 2)));
    }
}