package edu.fiuba.algo3.modelo.redElectrica;

import edu.fiuba.algo3.modelo.excepciones.NoHayMasEnergia;

public class RedElectrica {
    private int energia;

    public RedElectrica() {
        this.energia = 5;
    }

    public void reducirEnergia() {
        if (!hayEnergia()){
            throw new NoHayMasEnergia("No se puede reducir la energia si ya no tiene mas");
        }

        this.energia = this.energia - 1;
    }

    private boolean hayEnergia() {
        return this.energia > 0;
    }

    public boolean suEnergiaEsIgualA(int energiaEsperada) {
        return this.energia == energiaEsperada;
    }

    
}
