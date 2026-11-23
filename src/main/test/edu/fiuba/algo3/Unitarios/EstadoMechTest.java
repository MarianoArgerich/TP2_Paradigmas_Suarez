package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeDeshacerMovimiento;
import edu.fiuba.algo3.modelo.excepciones.YaAtacoException;
import edu.fiuba.algo3.modelo.excepciones.YaSeMovioException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Punio;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.Estado;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.Finalizado;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.InicioTurno;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.Movido;
import edu.fiuba.algo3.modelo.unidades.Mech.Estado.MovimientoDeshecho;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EstadoMechTest {

    @Test
    public void testEstadoInicioTurnoPermiteMover() {
        Casilla origen = new Casilla(new Posicion(0, 0));
        Casilla destino = new Casilla(new Posicion(0, 1));
        Mech mech = combate();
        origen.colocarOcupante(mech);
        Tablero tablero = new Tablero(List.of(origen, destino));
        Estado estado = new InicioTurno();

        estado.mover(mech, origen, new Arriba(), 1, tablero);

        assertTrue(tablero.unidadEstaEn(mech, new Posicion(0, 1)));
    }

    @Test
    public void testEstadoInicioTurnoNoPermiteDeshacerMovimiento() {
        Casilla origen = new Casilla(new Posicion(0, 0));
        Casilla destino = new Casilla(new Posicion(0, 1));
        Mech mech = combate();
        origen.colocarOcupante(mech);
        Tablero tablero = new Tablero(List.of(origen, destino));
        Estado estado = new InicioTurno();

        assertThrows(NoSePuedeDeshacerMovimiento.class,
                () -> estado.deshacerMovimiento(mech, origen, tablero));
    }

    @Test
    public void testEstadoInicioTurnoLuegoDeAtacarNoPermiteAtacarOtraVez() {
        Casilla origen = new Casilla(new Posicion(0, 0));
        Casilla objetivo = new Casilla(new Posicion(0, 1));
        Mech mech = combate();
        Luciernaga vek = new Luciernaga();
        origen.colocarOcupante(mech);
        objetivo.colocarOcupante(vek);
        Tablero tablero = new Tablero(List.of(origen, objetivo));

        new InicioTurno().atacar(mech, new Punio(), new Posicion(0, 0), new Posicion(0, 1), tablero);

        assertThrows(YaAtacoException.class,
                () -> mech.atacar(new Posicion(0, 0), new Posicion(0, 1), tablero));
    }

    @Test
    public void testEstadoMovidoNoPermiteVolverAMoverse() {
        Casilla casilla = new Casilla(new Posicion(0, 1));
        Mech mech = combate();
        Tablero tablero = new Tablero(List.of(casilla));
        Estado movido = new Movido(new Posicion(0, 0));

        assertThrows(YaSeMovioException.class,
                () -> movido.mover(mech, casilla, new Arriba(), 1, tablero));
    }

    @Test
    public void testEstadoMovidoAlDeshacerMovimientoVuelveAPosicionAnterior() {
        Casilla anterior = new Casilla(new Posicion(0, 0));
        Casilla actualConAcido = new Casilla(new Posicion(0, 1), new Acido());
        Mech mech = combate();
        actualConAcido.colocarOcupante(mech);
        Tablero tablero = new Tablero(List.of(anterior, actualConAcido));

        new Movido(new Posicion(0, 0)).deshacerMovimiento(mech, actualConAcido, tablero);

        assertTrue(tablero.unidadEstaEn(mech, new Posicion(0, 0)));
    }

    @Test
    public void testEstadoMovidoAlDeshacerMovimientoQuitaEfectoDelAcido() {
        Casilla anterior = new Casilla(new Posicion(0, 0));
        Casilla actualConAcido = new Casilla(new Posicion(0, 1), new Acido());
        Mech mech = combate();
        actualConAcido.colocarOcupante(mech);
        Tablero tablero = new Tablero(List.of(anterior, actualConAcido));

        new Movido(new Posicion(0, 0)).deshacerMovimiento(mech, actualConAcido, tablero);
        mech.recibirDanio(1, anterior);

        assertTrue(mech.suVidaEsIgualA(2));
    }

    @Test
    public void testEstadoMovimientoDeshechoPermiteMover() {
        Casilla origen = new Casilla(new Posicion(0, 0));
        Casilla destino = new Casilla(new Posicion(0, 1));
        Mech mech = combate();
        origen.colocarOcupante(mech);
        Tablero tablero = new Tablero(List.of(origen, destino));
        Estado estado = new MovimientoDeshecho();

        estado.mover(mech, origen, new Arriba(), 1, tablero);

        assertTrue(tablero.unidadEstaEn(mech, new Posicion(0, 1)));
    }

    @Test
    public void testEstadoMovimientoDeshechoNoPermiteDeshacerMovimientoNuevamente() {
        Casilla origen = new Casilla(new Posicion(0, 0));
        Casilla destino = new Casilla(new Posicion(0, 1));
        Mech mech = combate();
        origen.colocarOcupante(mech);
        Tablero tablero = new Tablero(List.of(origen, destino));
        Estado estado = new MovimientoDeshecho();

        assertThrows(NoSePuedeDeshacerMovimiento.class,
                () -> estado.deshacerMovimiento(mech, origen, tablero));
    }

    @Test
    public void testEstadoFinalizadoRechazaMover() {
        Casilla casilla = new Casilla(new Posicion(0, 0));
        Mech mech = combate();
        Tablero tablero = new Tablero(List.of(casilla));
        Estado finalizado = new Finalizado();

        assertThrows(YaSeMovioException.class,
                () -> finalizado.mover(mech, casilla, new Arriba(), 1, tablero));
    }

    @Test
    public void testEstadoFinalizadoRechazaAtacar() {
        Casilla casilla = new Casilla(new Posicion(0, 0));
        Mech mech = combate();
        Tablero tablero = new Tablero(List.of(casilla));
        Estado finalizado = new Finalizado();

        assertThrows(YaAtacoException.class,
                () -> finalizado.atacar(mech, new Punio(), new Posicion(0, 0), new Posicion(0, 1), tablero));
    }

    @Test
    public void testEstadoFinalizadoRechazaDeshacer() {
        Casilla casilla = new Casilla(new Posicion(0, 0));
        Mech mech = combate();
        Tablero tablero = new Tablero(List.of(casilla));
        Estado finalizado = new Finalizado();

        assertThrows(NoSePuedeDeshacerMovimiento.class,
                () -> finalizado.deshacerMovimiento(mech, casilla, tablero));
    }
}
