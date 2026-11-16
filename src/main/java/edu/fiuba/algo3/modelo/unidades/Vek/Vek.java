package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.ArrayList;
import java.util.List;

public abstract class Vek extends Unidad {
    protected Vek(int vidaMax, int rangoDeMovimiento) {
        super(vidaMax, rangoDeMovimiento);
    }

    @Override
    public void atacar(Posicion origen, Posicion objetivo, Tablero tablero) {}

    @Override
    public boolean esVek() {
        return true;
    }

    public boolean amenazaDesde(Posicion origen, Direccion direccion, Posicion objetivo,Tablero tablero) {
        return origen.siguienteEn(direccion).equals(objetivo);
    }

    public abstract void resolverAtaque(Posicion posicionActual, Direccion direccion, Tablero tablero);

    /**
    public Posicion moverseParaAtacarEdificio(Posicion posicionVek, Tablero tablero){

        boolean encontrado = false;

        Posicion objetivo = buscarEdificioParaAtacar(posicionVek, tablero);

        if(!objetivo.equals(posicionVek)){
            encontrado = true;
        }

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Arriba());
        direcciones.add(new Abajo());
        direcciones.add(new Derecha());
        direcciones.add(new Izquierda());

        int distanciaActual = 1;
        Direccion direccionEncontrada = new SinDireccion();

        while (this.estaDentroDelRango(distanciaActual) && !encontrado){
            for (Direccion direccion : direcciones) {
                Posicion posicionInicial = posicionVek.mover(direccion, distanciaActual);

                objetivo = (buscarEdificioParaAtacar(posicionInicial, tablero));
                if (!objetivo.equals(posicionInicial)){
                    direccionEncontrada = direccion;
                    encontrado = true;
                    break;
                }
            }
            distanciaActual++;
        }

        if (!objetivo.esAdyacenteA(posicionVek)) {
            tablero.mover(posicionVek, direccionEncontrada, distanciaActual);
        }

        return objetivo;
    }
    */

    /**
    private Posicion buscarEdificioParaAtacar(Posicion posicionInicial, Tablero tablero){

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Arriba());
        direcciones.add(new Abajo());
        direcciones.add(new Derecha());
        direcciones.add(new Izquierda());

        for(Direccion candidato : direcciones){
            Posicion posicionCandidato = posicionInicial.siguienteEn(candidato);
            if(tablero.edificioEn(posicionCandidato)){
                return posicionCandidato;
            }
        }
        return posicionInicial;
    }

    private Posicion buscarMechParaAtacar(Posicion posicionInicial, Tablero tablero){

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Arriba());
        direcciones.add(new Abajo());
        direcciones.add(new Derecha());
        direcciones.add(new Izquierda());

        for(Direccion candidato : direcciones){
            Posicion posicionCandidato = posicionInicial.siguienteEn(candidato);
            if(tablero.mechEn(posicionCandidato)){
                return posicionCandidato;
            }
        }
        return posicionInicial;
    }
    */
    /**
    public Posicion moverseParaAtacarMech(Posicion posicionVek, Tablero tablero){

        boolean encontrado = false;

        Posicion objetivo = buscarMechParaAtacar(posicionVek, tablero);

        if(!objetivo.equals(posicionVek)){
            encontrado = true;
        }

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Arriba());
        direcciones.add(new Abajo());
        direcciones.add(new Derecha());
        direcciones.add(new Izquierda());

        int distanciaActual = 1;
        Direccion direccionEncontrada = new SinDireccion();

        while (this.estaDentroDelRango(distanciaActual) && !encontrado){
            for (Direccion direccion : direcciones) {
                Posicion posicionInicial = posicionVek.mover(direccion, distanciaActual);

                objetivo = (buscarMechParaAtacar(posicionInicial, tablero));
                if (!objetivo.equals(posicionInicial)){
                    direccionEncontrada = direccion;
                    encontrado = true;
                    break;
                }
            }
            distanciaActual++;
        }

        if (!objetivo.esAdyacenteA(posicionVek)) {
            tablero.mover(posicionVek, direccionEncontrada, distanciaActual);
        }

        return objetivo;
    }
    */
}
