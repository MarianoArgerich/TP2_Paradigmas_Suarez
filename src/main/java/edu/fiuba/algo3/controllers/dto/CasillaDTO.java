package edu.fiuba.algo3.controllers.dto;

public class CasillaDTO {
    public enum TipoTerreno {SUELO, AGUA, ACIDO}
    public enum TipoEntidad {
        VACIO, EDIFICIO, MONTANIA, MECH_COMBATE, MECH_CANION, MECH_ARTILLERIA, VEK_LUCIERNAGA
    }

    public final TipoTerreno tipoTerreno;
    public final TipoEntidad tipoEntidad;
    public final String nombre;
    public final int vidaActual;
    public final int vidaMax;
    public final int movimiento;
    public final String detalle;

    public CasillaDTO(TipoTerreno terreno, TipoEntidad entidad, String nombre, int vida, int vidaMax, int movimiento, String detalle) {
        this.tipoTerreno = terreno;
        this.tipoEntidad = entidad;
        this.nombre = nombre;
        this.vidaActual = vida;
        this.vidaMax = vidaMax;
        this.movimiento = movimiento;
        this.detalle = detalle;
    }
}
