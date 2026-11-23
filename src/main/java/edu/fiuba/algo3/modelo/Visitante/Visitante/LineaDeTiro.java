package edu.fiuba.algo3.modelo.Visitante.Visitante;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Destruible;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.ArrayList;
import java.util.List;

public class LineaDeTiro extends Visitante {
    private final Direccion direccion;
    private Posicion posicionActual;
    private final List<Posicion> casillasValidas = new ArrayList<>();

    public LineaDeTiro(Posicion origen, Direccion direccion) {
        this.direccion = direccion;
        this.posicionActual = origen.siguienteEn(direccion);
    }

    public void explorarEn(Tablero tablero) {
        tablero.aplicarEfecto(this.posicionActual, this);
    }

    public Posicion destinoFinal() {
        return this.posicionActual;
    }


    //No borrar, se usa para los tests
    public boolean alcanzaA(Posicion objetivo) {
        return this.casillasValidas.contains(objetivo);
    }


    @Override
    public void visitar(SinOcupante sinOcupante, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        this.casillasValidas.add(this.posicionActual);
        this.posicionActual = this.posicionActual.siguienteEn(this.direccion);
        tablero.aplicarEfecto(this.posicionActual, this);
    }

    @Override
    public void visitar(Unidad unidad, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        this.casillasValidas.add(this.posicionActual);
    }

    @Override
    public void visitar(Destruible destruible, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        this.casillasValidas.add(this.posicionActual);
    }

    @Override
    public void visitar(Montania montania, Terreno terreno, Casilla casillaActual, Tablero tablero) {
        this.casillasValidas.add(this.posicionActual);
    }
}
