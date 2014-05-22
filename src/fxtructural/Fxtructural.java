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
        
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tab tab1 = new Tab();
        tab1.setText("Respecto a un punto");
        
        Tab tab2 = new Tab();
        tab2.setText("Respecto a un eje");
        
        Tab tab3 = new Tab();
        tab3.setText("Centroides");
        
       final Font metroFont = Font.loadFont(
      Fxtructural.class.getResource("SEGOEUIL.ttf").toExternalForm(), 22);
        
        HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        VBox vBox = new VBox();
        HBox botones = new HBox();
        HBox tituloHB = new HBox();
        GridPane grid = new GridPane();
        
        Label titulo = new Label("Cálculo de momento y centroides");
        titulo.setFont(metroFont);
        titulo.setId("titulo");
        titulo.setAlignment(Pos.TOP_LEFT);
        
        Image img = new Image("img/icon1.png");
        ImageView imgView = new ImageView(img);
        
        Label label1 = new Label("Punto de Inicio:");        
               
        TextField txtPx = new TextField();
        txtPx.setPromptText("x");
        txtPx.getStyleClass().add("min");
        
        TextField txtPy = new TextField();
        txtPy.setPromptText("y");
        txtPy.getStyleClass().add("min");
        
        TextField txtPz = new TextField();
        txtPz.setPromptText("z");
        txtPz.getStyleClass().add("min");
        
        //Punto final
        Label label2 = new Label("Punto de Fin:"); 
        
        TextField txtPx2 = new TextField();
        txtPx2.setPromptText("x");
        txtPx2.getStyleClass().add("min");
        
        TextField txtPy2 = new TextField();
        txtPy2.setPromptText("y");
        txtPy2.getStyleClass().add("min");
        
        TextField txtPz2 = new TextField();
        txtPz2.setPromptText("z");
        txtPz2.getStyleClass().add("min");
        
        //Fuerza
        TextField txtFuerza = new TextField();
        txtFuerza.setPromptText("Fuerza");
        
        Button min = new Button("");
                min.getStyleClass().add("decoration-button-minimize");
                min.setId("minimize");
                min.setPrefHeight(30.0);
                min.setPrefWidth(34.0);
                min.setFocusTraversable(false);
        
        min.setOnAction((ActionEvent e1) -> { 
            primaryStage.setIconified(true);
        });
        
        Button btnCerrar = new Button("");
                btnCerrar.getStyleClass().add("decoration-button-close");
                btnCerrar.setId("close");
                btnCerrar.setPrefHeight(30.0);
                btnCerrar.setPrefWidth(34.0);
                btnCerrar.setFocusTraversable(false);

        btnCerrar.setOnAction((ActionEvent e1) -> { 
            primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
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
        
        //Calculo respecto a un punto
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setFont(metroFont);
        btnCalcular.getStyleClass().add("btn");
        btnCalcular.setOnAction((ActionEvent e) -> {
        
            DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
            simbolo.setDecimalSeparator('.');
            simbolo.setGroupingSeparator(',');
            DecimalFormat df = new DecimalFormat("0.00#", simbolo);
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

            double brazoi = parseDouble(df.format((x2-x1)));
            double brazoj = parseDouble(df.format((y2-y1)));
            double brazok = parseDouble(df.format((z2-z1)));

            double fuerza = parseDouble(txtFuerza.getText());

            double modulo = parseDouble(df.format(
            Math.sqrt(Math.pow(brazoi,2)+Math.pow(brazoj,2)+Math.pow(brazok,2))
            ));

            double lambdax = parseDouble(df.format((fuerza*(brazoi/modulo))));
            producto[4] = lambdax;
            double lambday = parseDouble(df.format((fuerza*(brazoj/modulo))));
            producto[5] = lambday;
            double lambdaz = parseDouble(df.format((fuerza*(brazok/modulo))));
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
            System.out.println("Modulo: "+modulo);
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
        
        vBox.getChildren().addAll(label1,hBox, label2, hBox2, hBox3, btnCalcular);
        vBox.setPadding(new Insets(15, 12, 15, 12));
       
        tab1.setContent(vBox);
        
        //CALCULO RESPECTO A UN EJE
        HBox hbInicio = new HBox();
        HBox hbFin = new HBox();
        HBox hbBrazo = new HBox();
        HBox hbFuerza = new HBox();
        VBox vBox22 = new VBox();
        
        Label lblTitulo22 = new Label("Punto de inicio:");        
               
        TextField txtPx22 = new TextField();
        txtPx22.setPromptText("x");
        txtPx22.getStyleClass().add("min");
        
        TextField txtPy22 = new TextField();
        txtPy22.setPromptText("y");
        txtPy22.getStyleClass().add("min");
        
        TextField txtPz22 = new TextField();
        txtPz22.setPromptText("z");
        txtPz22.getStyleClass().add("min");
        
        //Punto final
        Label lblTitulo23 = new Label("Punto de fin:"); 
        
        TextField txtPx23 = new TextField();
        txtPx23.setPromptText("x");
        txtPx23.getStyleClass().add("min");
        
        TextField txtPy23 = new TextField();
        txtPy23.setPromptText("y");
        txtPy23.getStyleClass().add("min");
        
        TextField txtPz23 = new TextField();
        txtPz23.setPromptText("z");
        txtPz23.getStyleClass().add("min");
        
        //Punto de la fuerza
        Label lblTitulo24 = new Label("Punto de la fuerza:"); 
        
        TextField txtFx = new TextField();
        txtFx.setPromptText("x");
        txtFx.getStyleClass().add("min");
        
        TextField txtFy = new TextField();
        txtFy.setPromptText("y");
        txtFy.getStyleClass().add("min");
        
        TextField txtFz = new TextField();
        txtFz.setPromptText("z");
        txtFz.getStyleClass().add("min");
        
        //Fuerza
        Label lblTitulo25 = new Label("Fuerza:"); 
        TextField txtFuerzax = new TextField();
        txtFuerzax.setPromptText("x");
        
        TextField txtFuerzay = new TextField();
        txtFuerzay.setPromptText("y");
        
        TextField txtFuerzaz = new TextField();
        txtFuerzaz.setPromptText("z");
        
        //Calculo respecto a un eje
        Button btnCalcular2 = new Button("Calcular");
        btnCalcular2.setFont(metroFont);
        btnCalcular2.getStyleClass().add("btn");
        btnCalcular2.setOnAction((ActionEvent ev) -> {
                
            DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
            simbolo.setDecimalSeparator('.');
            simbolo.setGroupingSeparator(',');
            DecimalFormat df = new DecimalFormat("0.00", simbolo);

            double producto[] = new double[7];

            double x1 = parseDouble(txtPx22.getText());
            
            double y1 = parseDouble(txtPy22.getText());
            producto[2] = y1;
            double z1 = parseDouble(txtPz22.getText());
            producto[3] = z1;

            double x2 = parseDouble(txtPx23.getText());
            double y2 = parseDouble(txtPy23.getText());
            double z2 = parseDouble(txtPz23.getText());
            
            double brazofx = parseDouble(txtFx.getText());
            double brazofy = parseDouble(txtFy.getText());
            double brazofz = parseDouble(txtFz.getText());

            double brazoi = parseDouble(df.format((x2-x1)));
            double brazoj = parseDouble(df.format((y2-y1)));
            double brazok = parseDouble(df.format((z2-z1)));

            double fuerzax = parseDouble(txtFuerzax.getText());
            double fuerzay = parseDouble(txtFuerzay.getText());
            double fuerzaz = parseDouble(txtFuerzaz.getText());

            double modulo = parseDouble(df.format(
            Math.sqrt(Math.pow(brazoi,2)+Math.pow(brazoj,2)+Math.pow(brazok,2))
            ));
            
            double brazoBFx = parseDouble(df.format((brazofx-x1)));
            double brazoBFy = parseDouble(df.format((brazofy-y1)));
            double brazoBFz = parseDouble(df.format((brazofz-z1)));
            
            //Lambda Fuerza
            double lambdafx = (brazofx/modulo);
            double lambdafy = (brazofy/modulo);
            double lambdafz = (brazofz/modulo);
            
            //Lambda Brazo
            double lambdaBCx = parseDouble(df.format((brazoi/modulo)));
            double lambdaBCy = parseDouble(df.format((brazoj/modulo)));
            double lambdaBCz = parseDouble(df.format((brazok/modulo)));
            
            producto[1] = brazoBFx;
            producto[2] = brazoBFy;
            producto[3] = brazoBFz;
            producto[4] = fuerzax;
            producto[5] = fuerzay;
            producto[6] = fuerzaz;

            double rProducto1i = (producto[2]*producto[6]);
            double rProducto1j = (producto[3]*producto[4]);
            double rProducto1k = (producto[1]*producto[5]);

            double rProducto2i = (producto[3]*producto[5]);
            double rProducto2j = (producto[1]*producto[6]);
            double rProducto2k = (producto[2]*producto[4]);

            double momentoi = parseDouble(df.format((rProducto1i-rProducto2i)));
            double momentoj = parseDouble(df.format((rProducto1j-rProducto2j)));
            double momentok = parseDouble(df.format((rProducto1k-rProducto2k)));
            
            
            double momento = ((momentoi*lambdaBCx)+(momentoj*lambdaBCy)+(momentok*lambdaBCz));

            System.out.println("El lambda es: "+lambdaBCx +","+lambdaBCy+","+lambdaBCz);
            System.out.println("Modulo: "+modulo);
            System.out.println(brazoi+"i + "+brazoj+"j + "+brazok+"k");
            System.out.println(momentoi+"i + "+momentoj+"j + "+momentok+"k");
            System.out.println("Momento: "+df.format(momento));
        });
        
        hbInicio.getChildren().addAll(txtPx22, txtPy22, txtPz22);
        hbInicio.setPadding(new Insets(15, 0, 15, 0));
        hbInicio.setSpacing(10);
        
        hbFin.getChildren().addAll(txtPx23, txtPy23, txtPz23);
        hbFin.setPadding(new Insets(15, 0, 15, 0));
        hbFin.setSpacing(10);
        
        hbBrazo.getChildren().addAll(txtFx,txtFy,txtFz);
        hbBrazo.setPadding(new Insets(15, 0, 15, 0));
        hbBrazo.setSpacing(10);
        
        hbFuerza.getChildren().addAll(txtFuerzax,txtFuerzay,txtFuerzaz);
        hbFuerza.setPadding(new Insets(15, 0, 15, 0));
        hbFuerza.setSpacing(10);
        
        vBox22.getChildren().addAll(lblTitulo22,hbInicio, lblTitulo23, hbFin, lblTitulo24, hbBrazo,lblTitulo25,hbFuerza, btnCalcular2);
        vBox22.setPadding(new Insets(15, 12, 15, 12));
        
        tab2.setContent(vBox22);
        
        tabPane.getTabs().addAll(tab1, tab2, tab3);
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
        
        Scene scene = new Scene(contenido, 500, 500);
        
        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
        
        scene.getStylesheets().add("css/JMetro.css");
        scene.getStylesheets().add("skin/undecorator.css");
        
        FadeTransition ft = new FadeTransition(Duration.millis(3000), contenido);
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.play(); 
        
        //primaryStage.getIcons().add(new
        //Image(this.getClass().getResourceAsStream("app.png")));

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

    
}
