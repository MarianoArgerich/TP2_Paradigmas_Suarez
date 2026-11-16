package edu.fiuba.algo3.modelo.unidades.Vek;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Terreno.Terreno;
import edu.fiuba.algo3.modelo.direccion.*;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.*;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Accion.AccionIA;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAcercarseAEdificio;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAtacarEdificio;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaAtacarMech;
import edu.fiuba.algo3.modelo.unidades.Vek.IA.Regla.ReglaIA;

import java.util.ArrayList;
import java.util.List;

public abstract class Vek extends Unidad {
    
    private final List<ReglaIA> reglasPrioridad;
    
    protected Vek(int vidaMax, int rangoDeMovimiento) {
        super(vidaMax, rangoDeMovimiento);
        this.reglasPrioridad = List.of(
                new ReglaAtacarEdificio(),
                new ReglaAtacarMech(),
                new ReglaAcercarseAEdificio()
        );
    }

    protected List<Direccion> direccionesOrtogonales() {
        return List.of(new Arriba(), new Abajo(), new Derecha(), new Izquierda());
    }

    public void ejecutarTurnoIA(Posicion posicionActual, Tablero tablero) {
        for (ReglaIA regla : reglasPrioridad) {
            AccionIA accion = regla.evaluar(this, posicionActual, tablero);
            if (accion.esValida()) {
                accion.ejecutar(this, tablero);
                return;
            }
        }
    }

    public List<Posicion> obtenerDestinosValidos(Posicion origen, Tablero tablero) {
        List<Posicion> visitadas = new ArrayList<>();
        List<Integer> distancias = new ArrayList<>();
        visitadas.add(origen);
        distancias.add(0);

        int indice = 0;
        while (indice < visitadas.size()) {
            Posicion actual = visitadas.get(indice);
            int distanciaActual = distancias.get(indice);
            indice++;

            if (estaDentroDelRango(distanciaActual + 1)) {
                for (Direccion direccion : direccionesOrtogonales()) {
                    Posicion vecina = actual.siguienteEn(direccion);
                    if (!visitadas.contains(vecina)){
                        visitadas.add(vecina);
                        distancias.add(distanciaActual + 1);
                    }
                }
            }
        }

        List<Posicion> destinosValidos = new ArrayList<>();
        for (Posicion candidata : visitadas) {
            if (candidata.equals(origen) || tablero.puedeTerminarVekEn(this, candidata)) {
                destinosValidos.add(candidata);
            }
        }

        ordenarDeterministicamente(destinosValidos);
        return destinosValidos;
    }

    protected void ordenarDeterministicamente(List<Posicion> posiciones) {
        for (int i = 0; i < posiciones.size() - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < posiciones.size(); j++) {
                if (posiciones.get(j).esMenorQue(posiciones.get(indiceMenor))) {
                    indiceMenor = j;
                }
            }
            Posicion temporal = posiciones.get(i);
            posiciones.set(i, posiciones.get(indiceMenor));
            posiciones.set(indiceMenor, temporal);
        }
    }

    public List<Direccion> calcularCaminoHasta(Posicion origen, Posicion destino, Tablero tablero) {
        List<Posicion> visitadas = new ArrayList<>();
        List<List<Direccion>> caminos = new ArrayList<>();
        visitadas.add(origen);
        caminos.add(new ArrayList<>());

        int indice = 0;
        while (indice < visitadas.size()) {
            Posicion actual = visitadas.get(indice);
            List<Direccion> caminoActual = caminos.get(indice);
            indice++;

            if (actual.equals(destino)) {
                return caminoActual;
            }

            if (estaDentroDelRango(caminoActual.size() + 1)) {
                for (Direccion direccion: direccionesOrtogonales()) {
                    Posicion vecina = actual.siguienteEn(direccion);
                    if (!visitadas.contains(vecina)) {
                        List<Direccion> nuevoCamino = new ArrayList<>(caminoActual);
                        nuevoCamino.add(direccion);
                        visitadas.add(vecina);
                        caminos.add(nuevoCamino);
                    }
                }
            }

        }
        return new ArrayList<>();
    }

    public List<Posicion> obtenerCasillasAtacablesDesde(Posicion origen, Tablero tablero){
        List<Posicion> atacables = new ArrayList<>();
        for (Direccion direccion : direccionesOrtogonales()) {
            atacables.add(origen.siguienteEn(direccion));
        }
        ordenarDeterministicamente(atacables);
        return atacables;
    }


    public boolean puedeTerminarEn(Terreno terreno){
        return terreno.esSeguroParaTerrestre();
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
