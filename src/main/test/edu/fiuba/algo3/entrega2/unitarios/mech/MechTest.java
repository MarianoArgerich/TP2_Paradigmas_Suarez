package edu.fiuba.algo3.entrega2.unitarios.mech;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.Arriba;
import edu.fiuba.algo3.modelo.direccion.Derecha;
import edu.fiuba.algo3.modelo.excepciones.AtaqueInvalidoException;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import edu.fiuba.algo3.modelo.unidades.Vek.Avispon;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Vek.Saltador;
import edu.fiuba.algo3.modelo.unidades.Vek.Vek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MechTest {
    private List<Casilla> mapa = new ArrayList<>();
    private Tablero tablero;
    private Mech mech1;
    private Vek vek1;
    private Vek vek2;
    private Posicion posicionAgua;

    @BeforeEach
    public void setUp() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa.add( new Casilla(new Posicion(i, j)));
            }
        }

        tablero = new Tablero(mapa);
        mech1 = new Combate();
        vek1 = new Luciernaga();
        vek2 = new Avispon();
    }

/*

    private Mech mech1;
    @BeforeEach
    public void setUp() {
        mech1 = new Combate();
    }

    private Tablero tableroConAguaEn(Posicion posicionAgua) {
        List<Casilla> casillas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicion posicion = new Posicion(i, j);
                casillas.add(posicion.equals(posicionAgua)
                        ? new Casilla(posicion, new Agua())
                        : new Casilla(posicion));
            }
        }
        return new Tablero(casillas);
    }
*/


    @Test
    public void testMechColocadoEnElAguaNoPuedeAtacar() {
        posicionAgua = new Posicion(2,2);
        mapa.removeIf(c -> c.estaEn(posicionAgua));
        mapa.add(new Casilla(posicionAgua, new Agua()));
        tablero = new Tablero(mapa);
        Luciernaga luciernaga = new Luciernaga();
        tablero.colocarUnidad(new Posicion(2, 2), mech1);
        tablero.colocarUnidad(new Posicion(2, 3), luciernaga);

        assertThrows(AtaqueInvalidoException.class,
                () -> tablero.atacar(new Posicion(2, 2), new Posicion(2, 3)));
        //este ultimo assert mepa que ta demas
        assertTrue(luciernaga.suVidaEsIgualA(3));
    }

}
