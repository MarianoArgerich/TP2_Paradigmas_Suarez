package edu.fiuba.algo3.modelo.representacion;

public interface TraductorEntidad {
    void traducirVacio();
    void traducirEdificio();
    void traducirMontania();
    void traducirMechCombate(int vidaActual, int vidaMax, int movimiento);
    void traducirMechCanion(int vidaActual, int vidaMax, int movimiento);
    void traducirMechArtilleria(int vidaActual, int vidaMax, int movimiento);
    void traducirLuciernaga(int vidaActual, int vidaMax, int movimiento);
}
