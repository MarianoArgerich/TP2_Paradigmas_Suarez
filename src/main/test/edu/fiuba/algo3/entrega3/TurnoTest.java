package edu.fiuba.algo3.entrega3;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.FaseVek;
import edu.fiuba.algo3.modelo.Turno.Fases.Emergencia.GeneradorEmergenciasFijo;
import edu.fiuba.algo3.modelo.Turno.Turno;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaEnFaseException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurnoTest {

    @Test
    public void testMechNoPuedeMoverseDuranteFaseVek() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.mover(posicionMech, new Arriba(), 1));
    }

    @Test
    public void testMechNoPuedeAtacarDuranteFaseVek() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.atacar(posicionMech, new Posicion(2, 3)));
    }

    @Test
    public void testMechNoPuedeDeshacerMovimientoDuranteFaseVek() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.deshacerMovimiento(posicionMech));
    }

    @Test
    public void testMechNoSePuedeMoverseEnFaseResolucion() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        //pasamos a FaseJugador
        turno.avanzarFase();
        //pasamos a FaseResolucion
        turno.avanzarFase();

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.mover(posicionMech, new Arriba(), 1));
    }

    @Test
    public void testMechNoPuedeAtacarEnFaseResolucion() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        //pasamos a FaseJugador
        turno.avanzarFase();
        //pasamos a FaseResolucion
        turno.avanzarFase();

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.atacar(posicionMech, new Posicion(2, 3)));
    }

    @Test
    public void testMechNoPuedeDeshacerMovimientoEnFaseResolucion() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        //pasamos a FaseJugador
        turno.avanzarFase();
        //pasamos a FaseResolucion
        turno.avanzarFase();

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.deshacerMovimiento(posicionMech));
    }

    @Test
    public void testMechNoSePuedeMoverseEnFaseEmergencia() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        //pasamos a FaseJugador
        turno.avanzarFase();
        //pasamos a FaseResolucion
        turno.avanzarFase();
        //pasamos a FaseEmergencia
        turno.avanzarFase();

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.mover(posicionMech, new Arriba(), 1));
    }

    @Test
    public void testMechNoPuedeAtacarEnFaseEmergencia() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        //pasamos a FaseJugador
        turno.avanzarFase();
        //pasamos a FaseResolucion
        turno.avanzarFase();
        //pasamos a FaseEmergencia
        turno.avanzarFase();

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.atacar(posicionMech, new Posicion(2, 3)));
    }

    @Test
    public void testMechNoPuedeDeshacerMovimientoEnFaseEmergencia() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        tablero.colocarUnidad(posicionMech, combate());
        Turno turno = new Turno(tablero);

        //pasamos a FaseJugador
        turno.avanzarFase();
        //pasamos a FaseResolucion
        turno.avanzarFase();
        //pasamos a FaseEmergencia
        turno.avanzarFase();

        assertThrows(AccionInvalidaEnFaseException.class,
                () -> turno.deshacerMovimiento(posicionMech));
    }

    @Test
    public void testUnNuevoTurnoReiniciaElEstadoDeLosMechsDejandolosActuarNuevamente() {
        Tablero tablero = TableroTestFactory.crearVacio();
        Posicion posicionMech = new Posicion(2, 2);
        Mech mech = combate();
        tablero.colocarUnidad(posicionMech, mech);

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));

        //pasamos a FaseMech
        turno.avanzarFase();
        turno.mover(posicionMech, new Derecha(), 1);
        turno.atacar(new Posicion(3, 2), new Posicion(3, 3));

        //pasamos a FaseResolucion
        turno.avanzarFase();
        //pasamos a FaseEmergencia
        turno.avanzarFase();
        //cambio de turno, pasamos a FaseVek
        turno.avanzarFase();
        //pasamos a FaseJugador
        turno.avanzarFase();

        turno.mover(new Posicion(3, 2), new Derecha(), 1);

        assertTrue(tablero.unidadEstaEn(mech, new Posicion(4, 2)));
    }

}