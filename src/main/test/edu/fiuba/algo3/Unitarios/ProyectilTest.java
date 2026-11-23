package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.TableroTestFactory;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Disparo;
import edu.fiuba.algo3.modelo.Visitante.Visitante.Proyectil;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.fiuba.algo3.modelo.unidades.Mech.Mech.combate;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProyectilTest {

    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = TableroTestFactory.crearVacio();
    }

    @Test
    public void proyectilAtraviesaCasillaVaciaEImpactaEnLaSiguiente() {
        Mech mech = combate();
        tablero.colocarUnidad(new Posicion(2, 0), mech);

        tablero.aplicarEfecto(new Posicion(1, 0), new Proyectil(new Derecha(), new Disparo(1)));

        assertTrue(mech.suVidaEsIgualA(2));
    }

    @Test
    public void proyectilSeDetieneEnCasillaConUnidadYLaDania() {
        Mech mechAdelante = combate();
        Mech mechAtras = combate();
        tablero.colocarUnidad(new Posicion(1, 0), mechAdelante);
        tablero.colocarUnidad(new Posicion(2, 0), mechAtras);

        tablero.aplicarEfecto(new Posicion(1, 0), new Proyectil(new Derecha(), new Disparo(1)));

        assertTrue(mechAdelante.suVidaEsIgualA(2));
    }

    @Test
    public void proyectilDetenidoEnCasillaConUnidadNoDaniaALaSiguienteUnidadEnEsaDireccion() {
        Mech mechAdelante = combate();
        Mech mechAtras = combate();
        tablero.colocarUnidad(new Posicion(1, 0), mechAdelante);
        tablero.colocarUnidad(new Posicion(2, 0), mechAtras);

        tablero.aplicarEfecto(new Posicion(1, 0), new Proyectil(new Derecha(), new Disparo(1)));

        assertTrue(mechAtras.suVidaEsIgualA(3));
    }
}