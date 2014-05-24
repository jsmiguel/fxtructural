/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxtructural;


import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import static javafx.scene.control.TabPane.STYLE_CLASS_FLOATING;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author jmiguel
 */
public class Fxtructural extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    
    final Font metroFont = Font.loadFont(
      Fxtructural.class.getResource("SEGOEUIL.ttf").toExternalForm(), 22);
    
    @Override
    public void start(Stage primaryStage) {
        setUserAgentStylesheet(STYLESHEET_MODENA);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tab tab1 = new Tab();
        tab1.setText("Respecto a un punto");
        
        Tab tab2 = new Tab();
        tab2.setText("Respecto a un eje");
        
        Tab tab3 = new Tab();
        tab3.setText("Centroides");      
       
        tab1.setContent(new RespectoUnPunto().RespectoUnPunto());      
 
        tab2.setContent(new RespectoUnEje().RespectoUnEje());
           
        tab3.setContent(new Centroide().Centroide());
        
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        tabPane.getStyleClass().add(STYLE_CLASS_FLOATING);
        
        BorderPane contenido = new BorderPane();
        contenido.setCenter(tabPane);
        contenido.setTop(crearEncabezado(primaryStage));
        
        contenido.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        contenido.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        
        Scene scene = new Scene(contenido, 500, 500);
        
        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
        
        scene.getStylesheets().add("css/JMetro.css");
        scene.getStylesheets().add("skin/undecorator.css");
        
        FadeTransition ft = new FadeTransition(Duration.millis(3000), contenido);
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.play(); 

        primaryStage.setTitle("fxtructural App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("img/icon.png"));
        //primaryStage.setHeight(Screen.getPrimary().getBounds().getHeight() / 2);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private GridPane crearEncabezado(Stage stage){
        HBox botones = new HBox();
        HBox tituloHB = new HBox();
        GridPane grid = new GridPane();
        
        Label titulo = new Label("Cálculo de momento y centroides");
        titulo.setFont(metroFont);
        titulo.setId("titulo");
        titulo.setAlignment(Pos.TOP_LEFT);
        
        Image img = new Image("img/icon1.png");
        ImageView imgView = new ImageView(img);
        
        Button min = new Button("");
                min.getStyleClass().add("decoration-button-minimize");
                min.setId("minimize");
                min.setPrefHeight(30.0);
                min.setPrefWidth(34.0);
                min.setFocusTraversable(false);
        
        min.setOnAction((ActionEvent e1) -> { 
            stage.setIconified(true);
        });
        
        Button btnCerrar = new Button("");
                btnCerrar.getStyleClass().add("decoration-button-close");
                btnCerrar.setId("close");
                btnCerrar.setPrefHeight(30.0);
                btnCerrar.setPrefWidth(34.0);
                btnCerrar.setFocusTraversable(false);

        btnCerrar.setOnAction((ActionEvent e1) -> { 
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        
        botones.getChildren().addAll( min, btnCerrar);
        botones.setAlignment(Pos.TOP_RIGHT);
        botones.getStyleClass().add("rigth");
        
        tituloHB.getChildren().addAll(imgView, titulo);
        tituloHB.getStyleClass().add("left");
        tituloHB.setSpacing(6);
        tituloHB.setPadding(new Insets(10, 10, 10, 10));
        
        grid.add(tituloHB, 0, 0);
        grid.add(botones, 1, 0);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(80);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        grid.getColumnConstraints().addAll(column1, column2);
        
        return grid;
    }
}
