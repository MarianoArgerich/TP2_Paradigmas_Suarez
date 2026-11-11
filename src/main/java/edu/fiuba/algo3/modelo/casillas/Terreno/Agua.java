package edu.fiuba.algo3.modelo.casillas.Terreno;


import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Agua implements Terreno {


    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public void reaccionarAlIngresoDe(Unidad unidad) {

    }
}
