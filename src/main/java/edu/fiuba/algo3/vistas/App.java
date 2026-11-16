package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.SystemInfo;
import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.casillas.Casilla;
import edu.fiuba.algo3.modelo.casillas.Terreno.Acido;
import edu.fiuba.algo3.modelo.casillas.Terreno.Agua;
import edu.fiuba.algo3.modelo.casillas.Terreno.Suelo;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Edificio;
import edu.fiuba.algo3.modelo.casillas.obstaculo.Montania;
import edu.fiuba.algo3.modelo.posicion.Posicion;
import edu.fiuba.algo3.modelo.unidades.Mech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.Mech.Canion;
import edu.fiuba.algo3.modelo.unidades.Mech.Combate;
import edu.fiuba.algo3.modelo.unidades.Vek.Luciernaga;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Tablero tablero = crearTableroDePrueba();
        ControladorJuego controlador = new ControladorJuego(tablero);
        VistaTablero vista = new VistaTablero(controlador);

        tablero.agregarObservador(vista);

        Scene scene = new Scene(vista, 1024, 680);
        stage.setTitle("Into The Breach - Algo3 FIUBA");
        stage.setScene(scene);
        stage.show();
    }

    private Tablero crearTableroDePrueba() {
        List<Casilla> casillas = new ArrayList<>();

        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                Posicion posicion = new Posicion(x, y);
                Casilla casilla;

                if (x == 4 && (y >= 2 && y <= 5)) {
                    casilla = new Casilla(posicion, new Agua());
                } else if (x == 5 && y == 2) {
                    casilla = new Casilla(posicion, new Acido());
                } else {
                    casilla = new Casilla(posicion, new Suelo());
                }
                if ((x == 1 && y == 5) || (x == 3 && y == 5)) {
                    casilla.colocarOcupante(new Edificio());
                } else if ((x == 6 && y == 6) || (x == 2 && y == 2)) {
                    casilla.colocarOcupante(new Montania());
                }

                casillas.add(casilla);
            }
        }

        Tablero tablero = new Tablero(casillas);

        tablero.colocarUnidad(new Posicion(1, 1), new Combate());
        tablero.colocarUnidad(new Posicion(2, 1), new Canion());
        tablero.colocarUnidad(new Posicion(3, 1), new Artilleria());
        tablero.colocarUnidad(new Posicion(3, 6), new Luciernaga());

        return tablero;
    }

    public static void main(String[] args) {
        launch();
    }

}