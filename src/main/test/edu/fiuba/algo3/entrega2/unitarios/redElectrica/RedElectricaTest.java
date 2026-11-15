package edu.fiuba.algo3.entrega2.unitarios.redElectrica;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.redElectrica.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RedElectricaTest {

    private Tablero tablero;
    private List<Casilla> mapa = new ArrayList<>();
    private Mech combate;

    @BeforeEach
    public void setUp() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                mapa.add( new Casilla(new Posicion(i, j)));
            }
        }
        tablero = new Tablero(mapa);
        combate = new Combate();
    }

    @Test
    public void testAlHacerDañoAUnEdificioLaRedElectricaSeReduceEn1() {
        Posicion posicionMech = new Posicion(2, 2);
        Posicion posicionEdificio = new Posicion(2, 3);

        // Reemplazamos la casilla vacía (2,3) por una con el edificio
        mapa.removeIf(c -> c.estaEn(posicionEdificio));

        Casilla casillaEdificio = new Casilla(posicionEdificio);
        casillaEdificio.colocarOcupante(new Edificio());
        mapa.add(casillaEdificio);

        tablero = new Tablero(mapa);
        tablero.colocarUnidad(posicionMech, combate);


        tablero.atacar(posicionMech, posicionEdificio);
        assertTrue(tablero.tieneEnergia(4));
    }

    @Test
    public void testAlFallarElAtaqueAUnEdificioLaRedElectricaNoPierdeEnergia(){
        Posicion posicionMech = new Posicion(2, 2);
        Posicion posicionEdificio = new Posicion(2, 3);
        Posicion posicionErrada = new Posicion(2, 1);

        // Reemplazamos la casilla vacía (2,3) por una con el edificio
        mapa.removeIf(c -> c.estaEn(posicionEdificio));

        Casilla casillaEdificio = new Casilla(posicionEdificio);
        casillaEdificio.colocarOcupante(new Edificio());
        mapa.add(casillaEdificio);

        tablero = new Tablero(mapa);
        tablero.colocarUnidad(posicionMech, combate);


        tablero.atacar(posicionMech, posicionErrada);
        assertTrue(tablero.tieneEnergia(5));
    }

}





