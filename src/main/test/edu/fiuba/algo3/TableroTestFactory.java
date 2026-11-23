package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Ocupante.Ocupante;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.ArrayList;
import java.util.List;

public class TableroTestFactory {

    private static final int TAMANIO_POR_DEFECTO = 8;
    private final List<Casilla> casillas;

    private TableroTestFactory(int filas, int columnas) {
        this.casillas = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.casillas.add(new Casilla(new Posicion(i, j)));
            }
        }
    }

    // --- Atajos de 1 línea para los casos más comunes ---

    public static Tablero crearVacio() {
        return iniciar().construir();
    }

    public static Tablero crearVacio(int filas, int columnas) {
        return iniciar(filas, columnas).construir();
    }

    public static Tablero crearConTerrenoEn(Posicion posicion, Terreno terreno) {
        return iniciar()
                .conTerrenoEn(posicion, terreno)
                .construir();
    }

    public static Tablero crearConOcupanteEn(Posicion posicion, Ocupante ocupante) {
        return iniciar()
                .conOcupanteEn(posicion, ocupante)
                .construir();
    }

    // --- Construcción encadenada para escenarios más complejos ---

    public static TableroTestFactory iniciar() {
        return new TableroTestFactory(TAMANIO_POR_DEFECTO, TAMANIO_POR_DEFECTO);
    }

    public static TableroTestFactory iniciar(int filas, int columnas) {
        return new TableroTestFactory(filas, columnas);
    }

    public TableroTestFactory conTerrenoEn(Posicion posicion, Terreno terreno) {
        this.casillas.removeIf(casilla -> casilla.estaEn(posicion));
        this.casillas.add(new Casilla(posicion, terreno));
        return this;
    }

    public TableroTestFactory conOcupanteEn(Posicion posicion, Ocupante ocupante) {
        this.casillas.stream()
                .filter(casilla -> casilla.estaEn(posicion))
                .findFirst()
                .ifPresent(casilla -> casilla.colocarOcupante(ocupante));
        return this;
    }

    public Tablero construir() {
        return new Tablero(this.casillas);
    }
}