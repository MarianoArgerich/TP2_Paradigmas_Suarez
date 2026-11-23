package edu.fiuba.algo3.Unitarios;

import edu.fiuba.algo3.modelo.excepciones.NoHayMasEnergia;
import edu.fiuba.algo3.modelo.redElectrica.RedElectrica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RedElectricaTest {

    private RedElectrica redElectrica;


    @BeforeEach
    public void setUp(){
        redElectrica = new RedElectrica();
    }


    @Test
    public void testLaRedElectricaSeCreaCon5DeEnergia(){
        assertEquals(5, redElectrica.getEnergia());
    }

    @Test
    public void testLaEnergiaSeReduceEn1(){
        redElectrica.reducirEnergia();
        assertEquals(4, redElectrica.getEnergia());
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





