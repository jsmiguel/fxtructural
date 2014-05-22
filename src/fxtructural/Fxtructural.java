/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxtructural;


import static java.lang.Double.parseDouble;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author jmiguel
 */
public class Fxtructural extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void start(Stage primaryStage) {
        setUserAgentStylesheet(STYLESHEET_MODENA);
        
       final Font metroFont = Font.loadFont(
      Fxtructural.class.getResource("SEGOEUIL.ttf").toExternalForm(), 22);
        
        HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        VBox vBox = new VBox();
        HBox botones = new HBox();
        HBox tituloHB = new HBox();
        HBox header = new HBox();
        GridPane grid = new GridPane();
        
        Label titulo = new Label("Cálculo de momento");
        titulo.setFont(metroFont);
        titulo.setId("titulo");
        titulo.setAlignment(Pos.TOP_LEFT);
        
        Image img = new Image("img/icon1.png");
        ImageView imgView = new ImageView(img);
        
        Label label1 = new Label("Punto de Inicio:");        
               
        TextField txtPx = new TextField();
        txtPx.setPromptText("x");
        txtPx.setFocusTraversable(false);
        txtPx.getStyleClass().add("min");
        
        TextField txtPy = new TextField();
        txtPy.setPromptText("y");
        txtPy.setFocusTraversable(false);
        txtPy.getStyleClass().add("min");
        
        TextField txtPz = new TextField();
        txtPz.setPromptText("z");
        txtPz.setFocusTraversable(false);
        txtPz.getStyleClass().add("min");
        
        //Punto final
        Label label2 = new Label("Punto de Fin:"); 
        
        TextField txtPx2 = new TextField();
        txtPx2.setPromptText("x");
        txtPx2.setFocusTraversable(false);
        txtPx2.getStyleClass().add("min");
        
        TextField txtPy2 = new TextField();
        txtPy2.setPromptText("y");
        txtPy2.setFocusTraversable(false);
        txtPy2.getStyleClass().add("min");
        
        TextField txtPz2 = new TextField();
        txtPz2.setPromptText("z");
        txtPz2.setFocusTraversable(false);
        txtPz2.getStyleClass().add("min");
        
        //Fuerza
        TextField txtFuerza = new TextField();
        txtFuerza.setPromptText("Fuerza");
        txtFuerza.setFocusTraversable(false);
        
        Button min = new Button("");
            min.getStyleClass().add("decoration-button-minimize");
            min.setId("minimize");
            min.setPrefHeight(30.0);
            min.setPrefWidth(34.0);
            min.setFocusTraversable(false);
        
        min.setOnAction((ActionEvent e1) -> { 
            primaryStage.setIconified(true);
        });
        
        Button off = new Button("");
            off.getStyleClass().add("decoration-button-close");
            off.setId("close");
            off.setPrefHeight(30.0);
            off.setPrefWidth(34.0);
            off.setFocusTraversable(false);

        off.setOnAction((ActionEvent e1) -> { 
            primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        
        botones.getChildren().addAll( min, off);
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
        
        
        Button btn = new Button("Calcular");
        btn.setFont(metroFont);
        btn.getStyleClass().add("btn");
        btn.setOnAction((ActionEvent e) -> {
        
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("#.000", simbolo);
        
        double producto[] = new double[7];
        
        double x1 = parseDouble(txtPx.getText());
        producto[1] = x1;
        double y1 = parseDouble(txtPy.getText());
        producto[2] = y1;
        double z1 = parseDouble(txtPz.getText());
        producto[3] = z1;
        
        double x2 = parseDouble(txtPx2.getText());
        double y2 = parseDouble(txtPy2.getText());
        double z2 = parseDouble(txtPz2.getText());
        
        double brazoi = parseDouble(df.format((x1-x2)));
        double brazoj = parseDouble(df.format((y1-y2)));
        double brazok = parseDouble(df.format((z1-z2)));
        
        double fuerza = parseDouble(txtFuerza.getText());
        
        double modulo = Math.sqrt(Math.pow(brazoi,2)+Math.pow(brazoj,2)+Math.pow(brazok,2));
        
        double modulof = parseDouble(df.format(modulo));
        
        double lambdax = fuerza*(brazoi/modulof);
        producto[4] = lambdax;
        double lambday = fuerza*(brazoj/modulof);
        producto[5] = lambday;
        double lambdaz = fuerza*(brazok/modulof);
        producto[6] = lambdaz;
        
        double rProducto1i = (producto[2]*producto[6]);
        double rProducto1j = (producto[3]*producto[4]);
        double rProducto1k = (producto[1]*producto[5]);
        
        double rProducto2i = (producto[3]*producto[5]);
        double rProducto2j = (producto[1]*producto[6]);
        double rProducto2k = (producto[2]*producto[4]);
        
        double momentoi = (rProducto1i-rProducto2i);
        double momentoj = (rProducto1j-rProducto2j);
        double momentok = (rProducto1k-rProducto2k);
        
        System.out.println("El lambda es: "+df.format(lambdax) +","+df.format(lambday)+","+df.format(lambdaz));
        System.out.println("Modulo: "+modulof);
        System.out.println(brazoi+"i + "+brazoj+"j + "+brazok+"k");
        System.out.println(lambdax+"i + "+lambday+"j + "+lambdaz+"k");
        System.out.println(momentoi+"i + "+momentoj+"j + "+momentok+"k");
            
        Action response = Dialogs.create()
            .title("Respuesta")
            .nativeTitleBar()
            .masthead("Valor Ingresado")
            .message("Hola " + x1).showConfirm();
            if (response == Dialog.Actions.YES) {               
              Notifications.create()
                .text("Realizado con éxito")
                .showInformation();
            }      
             
        });
        
        hBox.getChildren().addAll(txtPx, txtPy, txtPz);
        hBox.setPadding(new Insets(15, 0, 15, 0));
        hBox.setSpacing(10);
        
        hBox2.getChildren().addAll(txtPx2, txtPy2, txtPz2);
        hBox2.setPadding(new Insets(15, 0, 15, 0));
        hBox2.setSpacing(10);
        
        hBox3.getChildren().addAll(txtFuerza);
        hBox3.setPadding(new Insets(15, 0, 15, 0));
        hBox3.setSpacing(10);
        
        vBox.getChildren().addAll(label1,hBox, label2, hBox2, hBox3, btn);
        vBox.setPadding(new Insets(15, 12, 15, 12));
        
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tab tab1 = new Tab();
        tab1.setText("Respecto a un punto");
        
        tab1.setContent(vBox);
        
        tabPane.getTabs().add(tab1);
        tabPane.getStyleClass().add(STYLE_CLASS_FLOATING);
        
        BorderPane contenido = new BorderPane();
        contenido.setCenter(tabPane);
        contenido.setTop(grid);

        //root.getChildren().add(tabPane);
        
        //Undecorator skin = new Undecorator(primaryStage, contenido);
        //skin.getStylesheets().add("skin/undecorator.css");
        
        contenido.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        contenido.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        
        Scene scene = new Scene(contenido, 500, 400);
        
        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
        
        scene.getStylesheets().add("css/JMetro.css");
        scene.getStylesheets().add("skin/undecorator.css");
        
        FadeTransition ft = new FadeTransition(Duration.millis(2000), contenido);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play(); 
        
        //primaryStage.getIcons().add(new
        //Image(this.getClass().getResourceAsStream("app.png")));

        primaryStage.setTitle("fxtructural App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("img/icon.png"));
        primaryStage.setHeight(Screen.getPrimary().getBounds().getHeight() / 2);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}
