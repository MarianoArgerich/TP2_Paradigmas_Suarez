package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.controllers.dto.CasillaDTO;
import edu.fiuba.algo3.modelo.observador.Observador;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.EnumMap;
import java.util.Map;

public class VistaTablero extends BorderPane implements Observador {
    private final ControladorJuego controlador;
    private final GridPane grilla = new GridPane();
    private final Label plantillaInfoUnidad = new Label("Selecciona una casilla.");
    private final Label plantillaMensaje = new Label("Listo.");

    private final Map<CasillaDTO.TipoTerreno, Color> coloresTerreno = new EnumMap<>(CasillaDTO.TipoTerreno.class);
    private final Map<CasillaDTO.TipoEntidad, String> etiquetasEntidad = new EnumMap<>(CasillaDTO.TipoEntidad.class);
    private final Map<CasillaDTO.TipoEntidad, Color> coloresEntidad = new EnumMap<>(CasillaDTO.TipoEntidad.class);

    public VistaTablero(ControladorJuego controlador){
        this.controlador = controlador;
        inicializarEstilos();
        construirLayout();
        actualizar();
    }

    private void inicializarEstilos(){
        coloresTerreno.put(CasillaDTO.TipoTerreno.SUELO, Color.web("#718096"));
        coloresTerreno.put(CasillaDTO.TipoTerreno.AGUA, Color.web("#2B6CB0"));
        coloresTerreno.put(CasillaDTO.TipoTerreno.ACIDO, Color.web("#48BB78"));

        etiquetasEntidad.put(CasillaDTO.TipoEntidad.VACIO, "");
        etiquetasEntidad.put(CasillaDTO.TipoEntidad.EDIFICIO, "EDIF");
        etiquetasEntidad.put(CasillaDTO.TipoEntidad.MONTANIA, "MONT");
        etiquetasEntidad.put(CasillaDTO.TipoEntidad.MECH_COMBATE, "COMB");
        etiquetasEntidad.put(CasillaDTO.TipoEntidad.MECH_CANION, "CAÑÓN");
        etiquetasEntidad.put(CasillaDTO.TipoEntidad.MECH_ARTILLERIA, "ARTIL");
        etiquetasEntidad.put(CasillaDTO.TipoEntidad.VEK_LUCIERNAGA, "LUCIÉR");

        coloresEntidad.put(CasillaDTO.TipoEntidad.VACIO, Color.TRANSPARENT);
        coloresEntidad.put(CasillaDTO.TipoEntidad.EDIFICIO, Color.web("#F6E05E"));
        coloresEntidad.put(CasillaDTO.TipoEntidad.MONTANIA, Color.web("#CBD5E0"));
        coloresEntidad.put(CasillaDTO.TipoEntidad.MECH_COMBATE, Color.web("#90CDF4"));
        coloresEntidad.put(CasillaDTO.TipoEntidad.MECH_CANION, Color.web("#90CDF4"));
        coloresEntidad.put(CasillaDTO.TipoEntidad.MECH_ARTILLERIA, Color.web("#90CDF4"));
        coloresEntidad.put(CasillaDTO.TipoEntidad.VEK_LUCIERNAGA, Color.web("#FEB2B2"));
    }

    private void construirLayout(){
        this.setStyle("-fx-background-color: #1A202C;");
        this.setPadding(new Insets(12));

        Button btnDeshacer = new Button("Deshacer Movimiento");
        btnDeshacer.setOnAction(e -> {
            plantillaMensaje.setText(controlador.deshacerMovimiento());
            actualizar();
        });

        HBox top = new HBox(15, new Label("⚡ RED ELÉCTRICA: 5/5"), new Label("TURNO 1"), btnDeshacer);
        top.setPadding(new Insets(10));
        top.setStyle("-fx-background-color: #2D3748;");
        top.getChildren().filtered(n -> n instanceof Label).forEach(n -> ((Label) n).setTextFill(Color.WHITE));
        this.setTop(top);

        grilla.setAlignment(Pos.CENTER);
        grilla.setHgap(2);
        grilla.setVgap(2);
        this.setCenter(grilla);

        VBox panelDerecho = new VBox(10, new Label("INFO DE UNIDAD"), plantillaInfoUnidad);
        panelDerecho.setPrefWidth(220);
        panelDerecho.setPadding(new Insets(12));
        panelDerecho.setStyle("-fx-background-color: #2D3748;");
        panelDerecho.getChildren().filtered(n -> n instanceof Label).forEach(n -> ((Label) n).setTextFill(Color.WHITE));
        plantillaInfoUnidad.setWrapText(true);
        this.setRight(panelDerecho);

        plantillaMensaje.setTextFill(Color.web("#A0AEC0"));
        this.setBottom(plantillaMensaje);
    }

    @Override
    public void actualizar() {
        grilla.getChildren().clear();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                CasillaDTO dto = controlador.obtenerInfoCasilla(x, y);
                StackPane celda = crearCeldaVisual(x, y, dto);
                grilla.add(celda, x, 7 - y);
            }
        }
    }
    private StackPane crearCeldaVisual(int x, int y, CasillaDTO dto) {
        StackPane celda = new StackPane();
        celda.setPrefSize(64, 64);

        Rectangle fondo = new Rectangle(62, 62, coloresTerreno.get(dto.tipoTerreno));
        fondo.setStroke(controlador.estaSeleccionada(x, y) ? Color.web("#63B3ED") : Color.web("#1A202C"));
        fondo.setStrokeWidth(controlador.estaSeleccionada(x, y) ? 3 : 1);

        String texto = etiquetasEntidad.get(dto.tipoEntidad);
        if (dto.vidaMax > 0) {
            texto += "\n♥ " + dto.vidaActual + "/" + dto.vidaMax;
        }

        Label lbl = new Label(texto);
        lbl.setTextFill(coloresEntidad.get(dto.tipoEntidad));
        lbl.setFont(Font.font("System", FontWeight.BOLD, 11));

        celda.getChildren().addAll(fondo, lbl);

        celda.setOnMouseClicked(e -> {
            plantillaInfoUnidad.setText(formatearInfo(dto));
            plantillaMensaje.setText(controlador.seleccionarOCambiarCasilla(x, y));
            actualizar();
        });

        return celda;
    }

    private String formatearInfo(CasillaDTO dto) {
        String info = dto.nombre + "\n" + dto.detalle;
        if (dto.vidaMax > 0) {
            info += "\nVida: " + dto.vidaActual + "/" + dto.vidaMax;
        }
        if (dto.movimiento > 0) {
            info += "\nMovimiento: " + dto.movimiento;
        }
        return info;
    }

}
