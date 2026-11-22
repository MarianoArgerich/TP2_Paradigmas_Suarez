package edu.fiuba.algo3.entrega2.mech;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arco;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.artilleria;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArcoTest {
    private Tablero tablero;
    private Arco arco;

    @BeforeEach
    public void setUp() {
        List<Casilla> mapa = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa.add(new Casilla(new Posicion(i, j)));
            }
        }

        tablero = new Tablero(mapa);
        arco = new Arco();
    }

    @Test
    public void noPuedeAtacarUnaCasillaAdyacente() {
        Posicion origen = new Posicion(4, 4);
        Posicion objetivo = new Posicion(4, 5);

        assertThrows(AtaqueInvalidoException.class,
                () -> arco.ejecutarAtaque(origen, objetivo, tablero));
    }

    @Test
    public void puedeAtacarEnLineaRectaADosCasillas() {
        Posicion origen = new Posicion(1, 4);
        Posicion posicionObjetivo = new Posicion(3, 4);
        Luciernaga objetivo = new Luciernaga();
        tablero.colocarUnidad(posicionObjetivo, objetivo);

        arco.ejecutarAtaque(origen, posicionObjetivo, tablero);

        assertTrue(objetivo.suVidaEsIgualA(2));
    }

    @Test
    public void noPuedeAtacarUnaCasillaDiagonalFueraDelAreaProhibida() {
        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(4, 4);

        assertThrows(AtaqueInvalidoException.class,
                () -> arco.ejecutarAtaque(origen, objetivo, tablero));
    }

    @Test
    public void puedeAtacarEnLineaRectaAMasDeDosCasillas() {
        Posicion origen = new Posicion(1, 4);
        Posicion posicionObjetivo = new Posicion(5, 4);
        Luciernaga objetivo = new Luciernaga();
        tablero.colocarUnidad(posicionObjetivo, objetivo);

        arco.ejecutarAtaque(origen, posicionObjetivo, tablero);

        assertTrue(objetivo.suVidaEsIgualA(2));
    }

    @Test
    public void puedeVolverAAtacarLuegoDeUnAtaqueInvalido() {
        Posicion origen = new Posicion(4, 4);
        Posicion objetivoAdyacente = new Posicion(4, 5);
        Posicion objetivoValido = new Posicion(4, 6);
        Luciernaga vek = new Luciernaga();
        Mech artillero = artilleria();

        tablero.colocarUnidad(origen, artillero);
        tablero.colocarUnidad(objetivoValido, vek);

        //este tiene que ir si o si, porque sino el test contempla que hubo un error y corta ahi.
        assertThrows(AtaqueInvalidoException.class,
                () -> tablero.atacar(origen, objetivoAdyacente));

        tablero.atacar(origen, objetivoValido);

        assertTrue(vek.suVidaEsIgualA(2),
                "El ataque válido posterior debe ejecutarse");
    }
}
