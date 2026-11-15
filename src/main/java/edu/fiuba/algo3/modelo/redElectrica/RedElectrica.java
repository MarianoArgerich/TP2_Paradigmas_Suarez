package edu.fiuba.algo3.modelo.redElectrica;

import edu.fiuba.algo3.modelo.excepciones.NoHayMasEnergia;

public class RedElectrica {
    private int energia;

    public RedElectrica() {
        this.energia = 5;
    }

    public void reducirEnergia(int cantidad) {
        if (!hayEnergia()){
            throw new NoHayMasEnergia("No se puede reducir la energia si ya no tiene mas");
        }
        //devuelve el maximo entre la resta de mi energia actual y la que me quitan, y 0.
        this.energia = Math.max(0, this.energia - cantidad);
    }

    private boolean hayEnergia() {
        return this.energia > 0;
    }

    public boolean suEnergiaEsIgualA(int energiaEsperada) {
        return this.energia == energiaEsperada;
    }

    
}
