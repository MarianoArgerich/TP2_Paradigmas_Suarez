package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.controllers.dto.CasillaDTO;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.representacion.TraductorEntidad;
import edu.fiuba.algo3.modelo.representacion.TraductorTerreno;

public class TraductorDeCasillas implements TraductorTerreno, TraductorEntidad {

    private CasillaDTO.TipoTerreno terreno;
    private CasillaDTO.TipoEntidad entidad;
    private String nombre;
    private int vidaActual;
    private int vidaMax;
    private int movimiento;
    private String detalle;

    public CasillaDTO traducir(Tablero tablero, Posicion posicion){
        tablero.traducirCasilla(posicion, this, this);
        return new CasillaDTO(terreno, entidad, nombre, vidaActual, vidaMax, movimiento, detalle);
    }


    @Override
    public void traducirVacio() {
        entidad = CasillaDTO.TipoEntidad.VACIO;
        nombre = "Terreno libre";
        vidaActual = vidaMax = movimiento = 0;
        detalle = "Sin ocupante (" + terreno.name() + ")";
    }

    @Override
    public void traducirEdificio() {
        entidad = CasillaDTO.TipoEntidad.EDIFICIO;
        nombre = "Edificio Civil";
        vidaActual = vidaMax = 1;
        movimiento = 0;
        detalle = "Conectado a la Red Eléctrica";

    }

    @Override
    public void traducirMontania() {
        entidad = CasillaDTO.TipoEntidad.MONTANIA;
        nombre = "Montaña";
        vidaActual = vidaMax = movimiento = 0;
        detalle = "Obstáculo indestructible";

    }

    @Override
    public void traducirMechCombate(int vidaActual, int vidaMax, int movimiento) {
        entidad = CasillaDTO.TipoEntidad.MECH_COMBATE;
        nombre = "Mech de Combate";
        this.vidaActual = vidaActual;
        this.vidaMax = vidaMax;
        this.movimiento = movimiento;
        detalle = "Arma: Puño (2 daño + empuje)";
    }

    @Override
    public void traducirMechCanion(int vidaActual, int vidaMax, int movimiento) {
        entidad = CasillaDTO.TipoEntidad.MECH_CANION;
        nombre = "Mech Cañón";
        this.vidaActual = vidaActual;
        this.vidaMax = vidaMax;
        this.movimiento = movimiento;
        detalle = "Arma: Cañón lineal (1 daño + empuje)";
    }

    @Override
    public void traducirMechArtilleria(int vidaActual, int vidaMax, int movimiento) {
        entidad = CasillaDTO.TipoEntidad.MECH_ARTILLERIA;
        nombre = "Mech Artillería";
        this.vidaActual = vidaActual;
        this.vidaMax = vidaMax;
        this.movimiento = movimiento;
        detalle = "Arma: Arco (1 daño + empuje en cruz)";
    }

    @Override
    public void traducirLuciernaga(int vidaActual, int vidaMax, int movimiento) {
        entidad = CasillaDTO.TipoEntidad.VEK_LUCIERNAGA;
        nombre = "Vek: Luciérnaga";
        this.vidaActual = vidaActual;
        this.vidaMax = vidaMax;
        this.movimiento = movimiento;
        detalle = "Enemigo terrestre (evita Agua y Ácido)";
    }

    @Override
    public void traducirSuelo() {
        this.terreno = CasillaDTO.TipoTerreno.SUELO;
    }

    @Override
    public void traducirAgua() {
        this.terreno = CasillaDTO.TipoTerreno.AGUA;
    }

    @Override
    public void traducirAcido() {
        this.terreno = CasillaDTO.TipoTerreno.ACIDO;
    }
}
