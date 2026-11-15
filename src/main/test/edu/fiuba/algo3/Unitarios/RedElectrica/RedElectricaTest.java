package edu.fiuba.algo3.Unitarios.RedElectrica;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.excepciones.NoHayMasEnergia;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.redElectrica.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech.Mech;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RedElectricaTest {

    private RedElectrica redElectrica;


    @BeforeEach
    public void setUp(){
        redElectrica = new RedElectrica();
    }


    @Test
    public void testLaRedElectricaSeCreaCon5DeEnergia(){
        assertTrue(redElectrica.suEnergiaEsIgualA(5));
    }

    @Test
    public void testLaEnergiaSeReduceEn1(){
        redElectrica.reducirEnergia();
        assertTrue(redElectrica.suEnergiaEsIgualA(4));
    }

    @Test
    public void testLaEnergiaNoSePuedeReducirMasCuandoSeAcabo(){
        redElectrica.reducirEnergia();
        redElectrica.reducirEnergia();
        redElectrica.reducirEnergia();
        redElectrica.reducirEnergia();
        redElectrica.reducirEnergia();

        assertThrows(NoHayMasEnergia.class, () -> redElectrica.reducirEnergia());
    }

}





