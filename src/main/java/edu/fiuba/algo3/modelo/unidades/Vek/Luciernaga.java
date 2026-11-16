package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Ocupante.SinOcupante;
import edu.fiuba.algo3.modelo.Tablero.Mapa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.posicion.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Luciernaga extends Vek {
    public Luciernaga() {
        super(3, 2);
    }

    @Override
    public void deshacerMovimientoDesde(Casilla casilla, Mapa mapa) {

    }


    @Override
    public List<Posicion> obtenerCasillasAtacablesDesde(Posicion origen, Tablero tablero) {
        List<Posicion> atacables = new ArrayList<>();

        for (Direccion direccion : direccionesOrtogonales()) {
            Posicion actual = origen.siguienteEn(direccion);

            while (!tablero.detieneDisparoEn(actual)) {
                actual = actual.siguienteEn(direccion);
            }

            atacables.add(actual);
        }

        ordenarDeterministicamente(atacables);
        return atacables;
    }

    @Override
    public boolean amenazaDesde(Posicion origen, Direccion direccion, Posicion objetivo, Tablero tablero) {
        Posicion impacto = buscarPrimerImpactoEnLinea(origen, direccion , tablero);
        return impacto.equals(objetivo);
    }

    private Posicion buscarPrimerImpactoEnLinea(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion actual = origen.siguienteEn(direccion);

        while (!tablero.detieneDisparoEn(actual)) {
            actual = actual.siguienteEn(direccion);
        }

        return actual;
    }

    @Override
    public void resolverAtaque(Posicion posicionActual, Direccion direccion, Tablero tablero) {
        Posicion objetivo = buscarPrimerImpactoEnLinea(posicionActual, direccion, tablero);
        tablero.aplicarDisparo(objetivo, 1);
    }
    


    /**
    private Posicion buscarEdificioParaAtacar(Posicion posicionInicial, Tablero tablero){

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Arriba());
        direcciones.add(new Abajo());
        direcciones.add(new Derecha());
        direcciones.add(new Izquierda());

        Posicion posicionCandidato = posicionInicial;
        int distancia = 1;
        int bordesRevisados = 0;

        while(!tablero.edificioEn(posicionCandidato)) {
            for (Direccion candidato : direcciones) {
                posicionCandidato = posicionInicial.mover(candidato, distancia);
                if (tablero.edificioEn(posicionCandidato)) {
                    return posicionCandidato;
                }

                if (!tablero.posicionValida(posicionCandidato)) {
                    bordesRevisados++;
                }
                else {
                    bordesRevisados = 0;
                }

                if (bordesRevisados == 4) {
                    return posicionInicial;
                }
            }
            distancia++;
        }
        return posicionInicial;
    }
     */

    /**
    private Posicion buscarEdificioParaAtacarHorizontal(Posicion posicionInicial, Tablero tablero){

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Derecha());
        direcciones.add(new Izquierda());

        Posicion posicionCandidato = posicionInicial;
        int distancia = 1;
        int bordesRevisados = 0;

        while(!tablero.edificioEn(posicionCandidato)) {
            for (Direccion candidato : direcciones) {
                posicionCandidato = posicionInicial.mover(candidato, distancia);
                if (tablero.edificioEn(posicionCandidato)) {
                    return posicionCandidato;
                }

                if (!tablero.posicionValida(posicionCandidato)) {
                    bordesRevisados++;
                }
                else {
                    bordesRevisados = 0;
                }

                if (bordesRevisados == 2) {
                    return posicionInicial;
                }
            }
            distancia++;
        }
        return posicionInicial;
    }
     */

    /**
    private Posicion buscarEdificioParaAtacarVertical(Posicion posicionInicial, Tablero tablero){

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Arriba());
        direcciones.add(new Abajo());

        Posicion posicionCandidato = posicionInicial;
        int distancia = 1;
        int bordesRevisados = 0;

        while(!tablero.edificioEn(posicionCandidato)) {
            for (Direccion candidato : direcciones) {
                posicionCandidato = posicionInicial.mover(candidato, distancia);
                if (tablero.edificioEn(posicionCandidato)) {
                    return posicionCandidato;
                }

                if (!tablero.posicionValida(posicionCandidato)) {
                    bordesRevisados++;
                }
                else {
                    bordesRevisados = 0;
                }

                if (bordesRevisados == 2) {
                    return posicionInicial;
                }
            }
            distancia++;
        }
        return posicionInicial;
    }
     */


    /**
    @Override
    public Posicion moverseParaAtacarEdificio(Posicion posicionVek, Tablero tablero){

        boolean encontrado = false;

        Posicion objetivo = this.buscarEdificioParaAtacar(posicionVek, tablero);

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

                if (direccion.equals(new Arriba()) || direccion.equals(new Abajo())) {
                    objetivo = (buscarEdificioParaAtacarHorizontal(posicionInicial, tablero));
                }
                else {
                    objetivo = (buscarEdificioParaAtacarVertical(posicionInicial, tablero));
                }

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
    private Posicion buscarMechParaAtacar(Posicion posicionInicial, Tablero tablero){

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Arriba());
        direcciones.add(new Abajo());
        direcciones.add(new Derecha());
        direcciones.add(new Izquierda());

        Posicion posicionCandidato = posicionInicial;
        int distancia = 1;
        int bordesRevisados = 0;

        while(!tablero.mechEn(posicionCandidato)) {
            for (Direccion candidato : direcciones) {
                posicionCandidato = posicionInicial.mover(candidato, distancia);
                if (tablero.mechEn(posicionCandidato)) {
                    return posicionCandidato;
                }

                if (!tablero.posicionValida(posicionCandidato)) {
                    bordesRevisados++;
                }
                else {
                    bordesRevisados = 0;
                }

                if (bordesRevisados == 4) {
                    return posicionInicial;
                }
            }
            distancia++;
        }
        return posicionInicial;
    }
     */

    /**
    private Posicion buscarMechParaAtacarHorizontal(Posicion posicionInicial, Tablero tablero){

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Derecha());
        direcciones.add(new Izquierda());

        Posicion posicionCandidato = posicionInicial;
        int distancia = 1;
        int bordesRevisados = 0;

        while(!tablero.mechEn(posicionCandidato)) {
            for (Direccion candidato : direcciones) {
                posicionCandidato = posicionInicial.mover(candidato, distancia);
                if (tablero.mechEn(posicionCandidato)) {
                    return posicionCandidato;
                }

                if (!tablero.posicionValida(posicionCandidato)) {
                    bordesRevisados++;
                }
                else {
                    bordesRevisados = 0;
                }

                if (bordesRevisados == 2) {
                    return posicionInicial;
                }
            }
            distancia++;
        }
        return posicionInicial;
    }
     */

    /**
    private Posicion buscarMechParaAtacarVertical(Posicion posicionInicial, Tablero tablero){

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(new Arriba());
        direcciones.add(new Abajo());

        Posicion posicionCandidato = posicionInicial;
        int distancia = 1;
        int bordesRevisados = 0;

        while(!tablero.mechEn(posicionCandidato)) {
            for (Direccion candidato : direcciones) {
                posicionCandidato = posicionInicial.mover(candidato, distancia);
                if (tablero.edificioEn(posicionCandidato)) {
                    return posicionCandidato;
                }

                if (!tablero.posicionValida(posicionCandidato)) {
                    bordesRevisados++;
                }
                else {
                    bordesRevisados = 0;
                }

                if (bordesRevisados == 2) {
                    return posicionInicial;
                }
            }
            distancia++;
        }
        return posicionInicial;
    }
     */

    /**
    @Override
    public Posicion moverseParaAtacarMech(Posicion posicionVek, Tablero tablero){

        boolean encontrado = false;

        Posicion objetivo = this.buscarMechParaAtacar(posicionVek, tablero);

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

                if (direccion.equals(new Arriba()) || direccion.equals(new Abajo())) {
                    objetivo = (buscarMechParaAtacarHorizontal(posicionInicial, tablero));
                }
                else {
                    objetivo = (buscarMechParaAtacarVertical(posicionInicial, tablero));
                }

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
