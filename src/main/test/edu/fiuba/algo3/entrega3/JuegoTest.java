package edu.fiuba.algo3.entrega3;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Juego.Derrota;
import edu.fiuba.algo3.modelo.Juego.EnCurso;
import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Juego.Victoria;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Fases.Emergencia.GeneradorEmergenciasFijo;
import edu.fiuba.algo3.modelo.Turno.Fases.FaseVek;
import edu.fiuba.algo3.modelo.Turno.Turno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.excepciones.JuegoFinalizadoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.artilleria;
import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.canion;
import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    private void completarTurno(Juego juego) {
        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();
    }

    @Test
    public void testCompletarLosCincoTurnosConRedElectricaMayorACeroEsVictoria() {
        Tablero tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(0, 7), new Edificio());
        tablero.colocarUnidad(new Posicion(0, 0), combate());
        tablero.colocarUnidad(new Posicion(1, 0), canion());
        tablero.colocarUnidad(new Posicion(2, 0), artilleria());

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno);

        for (int i = 1; i < 5; i++) {
            completarTurno(juego);
        }
        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        assertEquals(new Victoria(), juego.getEstado());
    }

    @Test
    public void testCompletarLosCincoTurnosLanzaErrorSiSeAvanzaOtraFaseMas() {
        Tablero tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(0, 7), new Edificio());
        tablero.colocarUnidad(new Posicion(0, 0), combate());
        tablero.colocarUnidad(new Posicion(1, 0), canion());
        tablero.colocarUnidad(new Posicion(2, 0), artilleria());

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno);

        for (int i = 1; i < 5; i++) {
            completarTurno(juego);
        }
        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        assertThrows(JuegoFinalizadoException.class, () -> juego.avanzarFase());
    }

    @Test
    public void testRedElectricaEnCeroAlFinalDeFaseResolucionEsDerrota() {
        Tablero tablero = TableroTestFactory.iniciar()
                .conOcupanteEn(new Posicion(0, 1), new Edificio())
                .conOcupanteEn(new Posicion(1, 1), new Edificio())
                .conOcupanteEn(new Posicion(2, 1), new Edificio())
                .conOcupanteEn(new Posicion(3, 1), new Edificio())
                .conOcupanteEn(new Posicion(4, 1), new Edificio())
                .construir();

        tablero.colocarUnidad(new Posicion(0, 0), combate());
        tablero.colocarUnidad(new Posicion(1, 0), combate());
        tablero.colocarUnidad(new Posicion(2, 0), combate());
        tablero.colocarUnidad(new Posicion(3, 0), combate());
        tablero.colocarUnidad(new Posicion(4, 0), combate());

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno);

        juego.avanzarFase();

        for (int i = 0; i < 5; i++) {
            juego.atacar(new Posicion(i, 0), new Posicion(i, 1));
        }

        juego.avanzarFase();
        juego.avanzarFase();

        assertEquals(new Derrota(), juego.getEstado());
    }

    @Test
    public void testDestruirLosTresMechsEsDerrota() {
        Tablero tablero = TableroTestFactory.crearVacio();
        tablero.colocarUnidad(new Posicion(1, 1), combate());
        tablero.colocarUnidad(new Posicion(3, 1), canion());
        tablero.colocarUnidad(new Posicion(5, 1), artilleria());

        tablero.colocarUnidad(new Posicion(1, 2), new Saltador());
        tablero.colocarUnidad(new Posicion(1, 0), new Saltador());
        tablero.colocarUnidad(new Posicion(3, 2), new Saltador());
        tablero.colocarUnidad(new Posicion(3, 0), new Saltador());
        tablero.colocarUnidad(new Posicion(5, 2), new Saltador());
        tablero.colocarUnidad(new Posicion(5, 0), new Saltador());

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno);

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        assertEquals(new Derrota(), juego.getEstado());
    }

    @Test
    public void testElPrimerTurnoSiempreEstaEnCursoAlIniciarElJuego(){
        Tablero tablero = TableroTestFactory.crearVacio();
        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno);

        assertEquals(new EnCurso(), juego.getEstado());
    }

    @Test
    public void testDerrotaTienePrioridadSobreVictoriaEnElUltimoTurno() {
        Tablero tablero = TableroTestFactory.iniciar()
                .conOcupanteEn(new Posicion(0, 1), new Edificio())
                .conOcupanteEn(new Posicion(1, 1), new Edificio())
                .conOcupanteEn(new Posicion(2, 1), new Edificio())
                .conOcupanteEn(new Posicion(3, 1), new Edificio())
                .conOcupanteEn(new Posicion(4, 1), new Edificio())
                .construir();

        for (int i = 0; i < 5; i++) {
            tablero.colocarUnidad(new Posicion(i, 0), combate());
        }

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno, 1);

        juego.avanzarFase();
        for (int i = 0; i < 5; i++) {
            juego.atacar(new Posicion(i, 0), new Posicion(i, 1));
        }
        juego.avanzarFase();
        juego.avanzarFase();

        assertEquals(new Derrota(), juego.getEstado());
    }

    @Test
    public void testPartidaSigueEnCursoMientrasQuedeAlMenosUnMechYEnergiaPositiva() {
        Tablero tablero = TableroTestFactory.crearConOcupanteEn(new Posicion(5, 2), new Edificio());
        tablero.colocarUnidad(new Posicion(1, 1), combate());
        tablero.colocarUnidad(new Posicion(3, 1), canion());
        tablero.colocarUnidad(new Posicion(5, 1), artilleria());
        tablero.colocarUnidad(new Posicion(1, 2), new Saltador());
        tablero.colocarUnidad(new Posicion(1, 0), new Saltador());
        tablero.colocarUnidad(new Posicion(3, 2), new Saltador());
        tablero.colocarUnidad(new Posicion(3, 0), new Saltador());

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno);

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        assertEquals(new EnCurso(), juego.getEstado());
    }

    @Test
    public void testDerrotaSeEvaluaAlFinalDeFaseResolucionYNoDuranteFaseJugador() {
        Tablero tablero = TableroTestFactory.iniciar()
                .conOcupanteEn(new Posicion(0, 1), new Edificio())
                .conOcupanteEn(new Posicion(1, 1), new Edificio())
                .conOcupanteEn(new Posicion(2, 1), new Edificio())
                .conOcupanteEn(new Posicion(3, 1), new Edificio())
                .conOcupanteEn(new Posicion(4, 1), new Edificio())
                .construir();

        for (int i = 0; i < 5; i++) {
            tablero.colocarUnidad(new Posicion(i, 0), combate());
        }

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno);

        juego.avanzarFase();
        for (int i = 0; i < 5; i++) {
            juego.atacar(new Posicion(i, 0), new Posicion(i, 1));
        }

        juego.avanzarFase();
        juego.avanzarFase();

        assertEquals(new Derrota(), juego.getEstado());
    }

    @Test
    public void testMechNoSePuedeMoverUnaVezFinalizadaLaPartida() {
        Tablero tablero = TableroTestFactory.crearVacio();
        tablero.colocarUnidad(new Posicion(0, 0), combate());

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno, 1);

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        assertThrows(JuegoFinalizadoException.class, () -> juego.mover(new Posicion(0, 0), new Arriba(), 1));
    }

    @Test
    public void testMechNoPuedeAtacarUnaVezFinalizadaLaPartida() {
        Tablero tablero = TableroTestFactory.crearVacio();
        tablero.colocarUnidad(new Posicion(0, 0), combate());

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno, 1);

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        assertThrows(JuegoFinalizadoException.class, () -> juego.atacar(new Posicion(0, 0), new Posicion(0, 1)));
    }

    @Test
    public void testMechNoPuedeDeshacerMovimientoUnaVezFinalizadaLaPartida() {
        Tablero tablero = TableroTestFactory.crearVacio();
        tablero.colocarUnidad(new Posicion(0, 0), combate());

        Turno turno = new Turno(tablero, new FaseVek(new GeneradorEmergenciasFijo(new Posicion(7, 7), new Luciernaga())));
        Juego juego = new Juego(tablero, turno, 1);

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        assertThrows(JuegoFinalizadoException.class, () -> juego.deshacerMovimiento(new Posicion(0, 0)));
    }
}