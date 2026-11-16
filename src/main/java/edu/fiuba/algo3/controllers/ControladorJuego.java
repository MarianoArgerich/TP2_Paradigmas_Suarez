package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.controllers.dto.CasillaDTO;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.posicion.Posicion;

public class ControladorJuego {
    private final Tablero tablero;
    private final TraductorDeCasillas traductor = new TraductorDeCasillas();
    private Posicion origenSeleccionado = null;

    public ControladorJuego(Tablero tablero){
        this.tablero = tablero;
    }

    public CasillaDTO obtenerInfoCasilla(int x, int y){
        return traductor.traducir(tablero, new Posicion(x, y));
    }

    public boolean estaSeleccionada(int x, int y){
        return origenSeleccionado != null && origenSeleccionado.equals(new Posicion(x, y));
    }

    public String seleccionarOCambiarCasilla(int x, int y){
        Posicion destino = new Posicion(x, y);

        if (origenSeleccionado == null){
            origenSeleccionado = destino;
            return "Casilla (" + x + ", " + y + ") seleccionada";
        }
        if (origenSeleccionado.equals(destino)){
            origenSeleccionado = null;
            return "Casilla (" + x + ", " + y + ") seleccionada";
        }

        try {
            if (!origenSeleccionado.estaEnLineaRectaCon(destino)){
                origenSeleccionado = null;
                return "Movimiento invalido: debe ser en linea recta.";
            }
            Direccion direccion = origenSeleccionado.calcularDireccionHacia(destino);
            int distancia = calcularDistanciaOrtogonal(origenSeleccionado, destino);
            tablero.mover(origenSeleccionado, direccion, distancia);
            origenSeleccionado = null;
            return "Movimiento realizado.";
        } catch (RuntimeException e){
            origenSeleccionado = null;
            return "Error: " + e.getMessage();
        }
    }

    public String deshacerMovimiento(){
        if (origenSeleccionado == null){
            return "Seleccioná primero el Mech a deshacer.";
        }
        try {
            tablero.deshacerMovimiento(origenSeleccionado);
            origenSeleccionado = null;
            return "Movimiento deshecho.";
        } catch (RuntimeException e){
            return "No se puede deshacer: " + e.getMessage();
        }
    }

    private int calcularDistanciaOrtogonal(Posicion origen, Posicion destino){
        int distancia = 0;
        Posicion actual = origen;
        Direccion direccion = origen.calcularDireccionHacia(destino);
        while (!actual.equals(destino) && distancia < 8){
            actual = actual.siguienteEn(direccion);
            distancia++;
        }
        return distancia;
    }

}
