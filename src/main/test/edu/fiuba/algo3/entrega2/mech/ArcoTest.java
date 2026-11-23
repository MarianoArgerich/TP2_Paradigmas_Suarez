package edu.fiuba.algo3.entrega2.mech;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.DistanciaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.ObjetivoDesalineadoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Arma.Arco;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.artilleria;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArcoTest {

    private Tablero tablero;
    private Arco arco;

    @BeforeEach
    public void setUp() {
        tablero = TableroTestFactory.crearVacio();
        arco = new Arco();
    }

    @Test
    public void noPuedeAtacarUnaCasillaAdyacente() {
        Posicion origen = new Posicion(4, 4);
        Posicion objetivo = new Posicion(4, 5);

        assertThrows(DistanciaInvalidaException.class,
                () -> arco.ejecutarAtaque(origen, objetivo, tablero));
    }
    //medio gaga pero por si acaso
    @Test
    public void noPuedeAtacarSuPropiaCasilla() {
        Posicion origen = new Posicion(4, 4);

        assertThrows(DistanciaInvalidaException.class,
                () -> arco.ejecutarAtaque(origen, origen, tablero));
    }

    @Test
    public void noPuedeAtacarUnaCasillaDiagonalFueraDelAreaProhibida() {
        Posicion origen = new Posicion(2, 2);
        Posicion objetivo = new Posicion(4, 4);

        assertThrows(ObjetivoDesalineadoException.class,
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
    public void puedeAtacarEnLineaRectaAMasDeDosCasillas() {
        Posicion origen = new Posicion(1, 4);
        Posicion posicionObjetivo = new Posicion(5, 4);
        Luciernaga objetivo = new Luciernaga();
        tablero.colocarUnidad(posicionObjetivo, objetivo);

        arco.ejecutarAtaque(origen, posicionObjetivo, tablero);

        assertTrue(objetivo.suVidaEsIgualA(2));
    }

    @Test
    public void empujaHaciaAfueraAUnidadesAdyacentes() {
        Posicion origen = new Posicion(1, 4);
        Posicion posicionObjetivo = new Posicion(4, 4);
        Posicion posicionAdyacente = new Posicion(5, 4);
        Luciernaga objetivo = new Luciernaga();
        Luciernaga adyacente = new Luciernaga();
        tablero.colocarUnidad(posicionObjetivo, objetivo);
        tablero.colocarUnidad(posicionAdyacente, adyacente);

        arco.ejecutarAtaque(origen, posicionObjetivo, tablero);

        assertTrue(tablero.unidadEstaEn(adyacente, new Posicion(6, 4)));
    }


    @Test
    public void empujaHaciaAfueraAUnidadesAdyacentesSinEmpujarAlObjetivo() {
        Posicion origen = new Posicion(1, 4);
        Posicion posicionObjetivo = new Posicion(4, 4);
        Posicion posicionAdyacente = new Posicion(5, 4);
        Luciernaga objetivo = new Luciernaga();
        Luciernaga adyacente = new Luciernaga();
        tablero.colocarUnidad(posicionObjetivo, objetivo);
        tablero.colocarUnidad(posicionAdyacente, adyacente);

        arco.ejecutarAtaque(origen, posicionObjetivo, tablero);

        assertTrue(tablero.unidadEstaEn(objetivo, posicionObjetivo));
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
        assertThrows(DistanciaInvalidaException.class,
                () -> tablero.atacar(origen, objetivoAdyacente));

        tablero.atacar(origen, objetivoValido);

        assertTrue(vek.suVidaEsIgualA(2));
    }
}