/*
 * Copyright (C) 2014 jmiguel
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package fxtructural;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author jmiguel
 */
public class Centroide extends VBox {
    
    final Font metroFont = Font.loadFont(
      Fxtructural.class.getResource("SEGOEUIL.ttf").toExternalForm(), 22);
    
    public Integer base = 0;
    public Integer altura = 0;
    public Integer nRect = 0;
    public Integer nCir = 0;
    public Integer nTri = 0;
    public Integer contador = 0;
    public String[] datosRect;
    public String[] datosCir;
    public String[] datosTri;
    public String[] centroRect;
    public String[] centroCir;
    public String[] centroTri;
    public String[] centrosFiguras;
    public String[] areasFiguras;
    public Double sumatoriaAx;
    public Double sumatoriaAy;
    public Double sumatoriaAreas;
    public Double sumatoriasCentros;
    public String datos;
    
    final TextField txtBase = new TextField();
    final TextField txtAltura = new TextField();
    final TextField txtDistanciax = new TextField();
    final TextField txtDistanciay = new TextField();
    
    final Label lblAreas = new Label();
    final Label lblAreasx = new Label();
    final Label lblAreasy = new Label();
    final Label lblCentroideX = new Label();
    final Label lblCentroideY = new Label();
    
    public VBox Centroide(){
        
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
            simbolo.setDecimalSeparator('.');
            simbolo.setGroupingSeparator(',');
            DecimalFormat df = new DecimalFormat("0.0#", simbolo);
            
        VBox vbCentroides = new VBox();
        
        Rectangle rect = new Rectangle();
        rect.setStroke(Color.BLACK);
        rect.setFill(Color.BLACK);
        rect.setWidth(52);
        rect.setHeight(48);
        
        TextField txtRect = new TextField();
        txtRect.setPromptText("0");
        txtRect.setMaxWidth(50);
        
        Circle cir = new Circle();
        cir.setStroke(Color.BLACK);
        cir.setFill(Color.BLACK);
        cir.setRadius(24);
        
        TextField txtCir = new TextField();
        txtCir.setPromptText("0");
        txtCir.setMaxWidth(50);
        txtCir.setDisable(true);
                
        Image imgTri = new Image("img/tri.png");
        ImageView imgViewTri = new ImageView(imgTri);
        
        TextField txtTri = new TextField();
        txtTri.setPromptText("0");
        txtTri.setMaxWidth(50);
        
        HBox hbImg = new HBox();
        hbImg.getChildren().addAll(rect,txtRect,imgViewTri,txtTri,cir,txtCir);
        hbImg.setPadding(new Insets(15, 0, 15, 0));
        hbImg.setAlignment(Pos.CENTER);
        hbImg.setMinHeight(50);
        hbImg.setSpacing(5);
        
        
        
        VBox hvDetalle = new VBox();
        hvDetalle.getChildren().addAll(lblAreas, lblAreasx, lblAreasy);
        hvDetalle.setPadding(new Insets(15, 0, 15, 0));
        hvDetalle.setAlignment(Pos.BASELINE_CENTER);
        hvDetalle.setSpacing(10);
        
        
        Button btnIniciar = new Button("Iniciar");
        btnIniciar.setFont(metroFont);
        btnIniciar.getStyleClass().add("btn");
        btnIniciar.setAlignment(Pos.CENTER);
        
        vbCentroides.getChildren().addAll(hbImg, btnIniciar, hvDetalle, lblCentroideX, lblCentroideY);
        vbCentroides.setPadding(new Insets(15, 12, 15, 12));
        vbCentroides.setAlignment(Pos.TOP_CENTER);
        vbCentroides.setSpacing(10);
        
        btnIniciar.setOnMouseClicked((MouseEvent) -> {
     
            
            iniciarCaptura(txtRect.getText(), txtCir.getText(), txtTri.getText());
            
            sumatoriaAreas = 0.0;
            sumatoriaAx = 0.0;
            sumatoriaAy = 0.0;
            
            if (datosRect != null & datosCir != null & datosTri != null) {
                int cantidad = datosRect.length+datosCir.length+datosTri.length;
                centrosFiguras = new String[cantidad-1];
                int contador = 0;
                for (int i = 0; i < datosRect.length; i++) {
                    String datos = datosRect[i];
                    String base;
                    String altura;
                    String operacion;
                    String xDist;
                    String yDist;
                 
                    int inicioBase = datos.indexOf("b");
                    int finBase = datos.indexOf(",");

                    int inicioAltura = datos.indexOf("a");
                    int finAltura = datos.indexOf("$"); 

                    int inicioOpe = datos.indexOf("$");
                    int finOpe = datos.indexOf("x");
                    
                    int inicioX = datos.indexOf("x");
                    int finX = datos.indexOf("y");
                    
                    int inicioY = datos.indexOf("y");

                    base = datos.substring(inicioBase +1, finBase);
                    altura = datos.substring(inicioAltura +1, finAltura);
                    xDist = datos.substring(inicioX +1, finX);
                    yDist = datos.substring(inicioY +1);
                    operacion = datos.substring(inicioOpe + 1, finOpe);

                    centrosFiguras[i] = calcularCentroideRect(base, altura, xDist, yDist);
                    contador++;
                }
                for (int i = 0; i < datosCir.length; i++) {
                    String datos = datosCir[i];
                    String base;
                    String altura;
                    String operacion;
                    String xDist;
                    String yDist;
                 
                    int inicioBase = datos.indexOf("b");
                    int finBase = datos.indexOf(",");

                    int inicioAltura = datos.indexOf("a");
                    int finAltura = datos.indexOf("$"); 

                    int inicioOpe = datos.indexOf("$");
                    int finOpe = datos.indexOf("x");
                    
                    int inicioX = datos.indexOf("x");
                    int finX = datos.indexOf("y");
                    
                    int inicioY = datos.indexOf("y");

                    base = datos.substring(inicioBase +1, finBase);
                    altura = datos.substring(inicioAltura +1, finAltura);
                    xDist = datos.substring(inicioX +1, finX);
                    yDist = datos.substring(inicioY +1);
                    operacion = datos.substring(inicioOpe + 1, finOpe);
                    int total = i+1;
                    int contador1=contador+total;
                    centrosFiguras[contador1] = calcularCentroideCir(base, altura);
                    
                }
                for (int i = 0; i < datosTri.length; i++) {
                    String datos = datosTri[i];
                    String base;
                    String altura;
                    String operacion;
                    String xDist;
                    String yDist;
                 
                    int inicioBase = datos.indexOf("b");
                    int finBase = datos.indexOf(",");

                    int inicioAltura = datos.indexOf("a");
                    int finAltura = datos.indexOf("$"); 

                    int inicioOpe = datos.indexOf("$");
                    int finOpe = datos.indexOf("x");
                    
                    int inicioX = datos.indexOf("x");
                    int finX = datos.indexOf("y");
                    
                    int inicioY = datos.indexOf("y");

                    base = datos.substring(inicioBase +1, finBase);
                    altura = datos.substring(inicioAltura +1, finAltura);
                    operacion = datos.substring(inicioOpe + 1, finOpe);
                    xDist = datos.substring(inicioX +1, finX);
                    yDist = datos.substring(inicioY +1);
                    
                    int total = i+1;
                    centrosFiguras[contador+total] = calcularCentroideTri(base, altura, xDist, yDist);

                }
                for (int i = 0 ; i < centrosFiguras.length ; i++) {
                    System.out.println("Elemento en indice " + i + ": " + centrosFiguras[i]);
                }
                
            } 
            
                if (datosRect != null && datosTri != null) {
                    int cantidad = datosRect.length+datosTri.length;
                centrosFiguras = new String[cantidad];
                areasFiguras = new String[cantidad];
                
                for (int i = 0 ; i < datosRect.length ; i++) {
                    System.out.println("Elemento en indice " + i + ": " + datosRect[i]);
                }
                for (int i = 0 ; i < datosTri.length ; i++) {
                    System.out.println("Elemento en indice " + i + ": " + datosTri[i]);
                }
                
                for (int i = 0; i < datosRect.length; i++) {
                    String datosRectangulo = datosRect[i];
                    String baseRectangulo;
                    String alturaRectangulo;
                    String operacionRectangulo;
                    String xDist;
                    String yDist;
                 
                    int inicioBase = datosRectangulo.indexOf("b");
                    int finBase = datosRectangulo.indexOf(",");

                    int inicioAltura = datosRectangulo.indexOf("a");
                    int finAltura = datosRectangulo.indexOf("$"); 

                    int inicioOpeRec = datosRectangulo.indexOf("$");
                    int finOpeRec = datosRectangulo.indexOf("x");
                    
                    int inicioX = datosRectangulo.indexOf("x");
                    int finX = datosRectangulo.indexOf("y");
                    
                    int inicioY = datosRectangulo.indexOf("y");

                    baseRectangulo = datosRectangulo.substring(inicioBase +1, finBase);
                    alturaRectangulo = datosRectangulo.substring(inicioAltura +1, finAltura);
                    operacionRectangulo = datosRectangulo.substring(inicioOpeRec +1, finOpeRec);
                    xDist = datosRectangulo.substring(inicioX +1, finX);
                    yDist = datosRectangulo.substring(inicioY +1);
                    
                    String centros = calcularCentroideRect(baseRectangulo, alturaRectangulo, xDist, yDist);
                    String areas = calcularAreaRectangulo(baseRectangulo, alturaRectangulo, operacionRectangulo);
                    Double areaR = calcularAreaRectangulo(parseDouble(baseRectangulo), parseDouble(alturaRectangulo));
                    if (operacionRectangulo.equals("s")) {
                        sumatoriaAreas = sumatoriaAreas + areaR;
                    } else {
                        sumatoriaAreas = sumatoriaAreas - areaR;
                    }
                    centrosFiguras[i] = centros;                    
                    areasFiguras[i] = areas;
                    
                    String datosRectangulox = centros;
                    
                    int inicioRectangulox = datosRectangulox.indexOf("x");
                    int finRectangulox = datosRectangulox.indexOf(",");

                    int inicioRectanguloy = datosRectangulox.indexOf("y");

                    //int inicioOpeTriangulo = datosTriangulo.indexOf("$");
                        
                    String xRectangulox = datosRectangulox.substring(inicioRectangulox +1, finRectangulox);
                    String yRectanguloy = datosRectangulox.substring(inicioRectanguloy +1);
                    
                    if (operacionRectangulo.equals("s")) {
                        sumatoriaAx = sumatoriaAx+(parseDouble(xRectangulox)*areaR);
                        sumatoriaAy = sumatoriaAy+(parseDouble(yRectanguloy)*areaR);
                    } else {
                        sumatoriaAx = sumatoriaAx+(parseDouble(xRectangulox)*-areaR);
                        sumatoriaAy = sumatoriaAy+(parseDouble(yRectanguloy)*-areaR);
                    }
                    
                    contador++;

                    
                }
                    System.out.println("Contador: "+contador);
                for (int i = 0; i < datosTri.length; i++) {
                    String datosTriangulo = datosTri[i];
                    String baseTriangulo;
                    String alturaTriangulo;
                    String operacionTriangulo;
                    String xDist;
                    String yDist;
                 
                    int inicioBaseTriangulo = datosTriangulo.indexOf("b");
                    int finBaseTriangulo = datosTriangulo.indexOf(",");

                    int inicioAlturaTriangulo = datosTriangulo.indexOf("a");
                    int finAlturaTriangulo = datosTriangulo.indexOf("$"); 

                    int inicioOpeTriangulo = datosTriangulo.indexOf("$");
                    int finOpe = datosTriangulo.indexOf("x");
                    
                    int inicioX = datosTriangulo.indexOf("x");
                    int finX = datosTriangulo.indexOf("y");
                    
                    int inicioY = datosTriangulo.indexOf("y");

                    baseTriangulo = datosTriangulo.substring(inicioBaseTriangulo +1, finBaseTriangulo);
                    alturaTriangulo = datosTriangulo.substring(inicioAlturaTriangulo +1, finAlturaTriangulo);
                    operacionTriangulo = datosTriangulo.substring(inicioOpeTriangulo + 1, finOpe);
                    xDist = datosTriangulo.substring(inicioX +1, finX);
                    yDist = datosTriangulo.substring(inicioY +1);
                   
                    int contador1 = contador+i;
                    System.out.println("Contador: "+contador1);
                    Double areaR = calcularAreaTriangulo(parseDouble(baseTriangulo), parseDouble(alturaTriangulo));
                    if (operacionTriangulo.equals("s")) {
                        sumatoriaAreas = sumatoriaAreas + areaR;
                    } else {
                        sumatoriaAreas = sumatoriaAreas - areaR;
                    }
                    
                    String centroTri = calcularCentroideTri(baseTriangulo, alturaTriangulo, xDist, yDist);
                    centrosFiguras[contador1] = centroTri;
                    areasFiguras[contador1] = calcularAreaTriangulo(baseTriangulo, alturaTriangulo, operacionTriangulo);
                    
                    String datosTriangulox = centroTri;
                    
                    int inicioTriangulox = datosTriangulox.indexOf("x");
                    int finTriangulox = datosTriangulox.indexOf(",");

                    int inicioTrianguloy = datosTriangulox.indexOf("y");

                    //int inicioOpeTriangulo = datosTriangulo.indexOf("$");
                        
                    String xTriangulox = datosTriangulox.substring(inicioTriangulox +1, finTriangulox);
                    String yTrianguloy = datosTriangulox.substring(inicioTrianguloy +1);
                    
                    if (operacionTriangulo.equals("s")) {
                        sumatoriaAx = sumatoriaAx+(parseDouble(xTriangulox)*areaR);
                        sumatoriaAy = sumatoriaAy+(parseDouble(yTrianguloy)*areaR);
                    } else {
                        sumatoriaAx = sumatoriaAx+(parseDouble(xTriangulox)*-areaR);
                        sumatoriaAy = sumatoriaAy+(parseDouble(yTrianguloy)*-areaR);
                    }
                    
                }
                    
                
                } else {
                    
                    int cantidad = datosRect.length;
                centrosFiguras = new String[cantidad];
                areasFiguras = new String[cantidad];
                
                    if (datosRect != null) {
                    for (int i = 0; i < datosRect.length; i++) {
                    String datosRectangulo = datosRect[i];
                    String baseRectangulo;
                    String alturaRectangulo;
                    String operacionRectangulo;
                    String xDist;
                    String yDist;
                 
                    int inicioBase = datosRectangulo.indexOf("b");
                    int finBase = datosRectangulo.indexOf(",");

                    int inicioAltura = datosRectangulo.indexOf("a");
                    int finAltura = datosRectangulo.indexOf("$"); 

                    int inicioOpeRec = datosRectangulo.indexOf("$");
                    int finOpeRec = datosRectangulo.indexOf("x");
                    
                    int inicioX = datosRectangulo.indexOf("x");
                    int finX = datosRectangulo.indexOf("y");
                    
                    int inicioY = datosRectangulo.indexOf("y");

                    baseRectangulo = datosRectangulo.substring(inicioBase +1, finBase);
                    alturaRectangulo = datosRectangulo.substring(inicioAltura +1, finAltura);
                    operacionRectangulo = datosRectangulo.substring(inicioOpeRec +1, finOpeRec);
                    xDist = datosRectangulo.substring(inicioX +1, finX);
                    yDist = datosRectangulo.substring(inicioY +1);
                    
                    String centros = calcularCentroideRect(baseRectangulo, alturaRectangulo, xDist, yDist);
                    String areas = calcularAreaRectangulo(baseRectangulo, alturaRectangulo, operacionRectangulo);
                    Double areaR = calcularAreaRectangulo(parseDouble(baseRectangulo), parseDouble(alturaRectangulo));
                    if (operacionRectangulo.equals("s")) {
                        sumatoriaAreas = sumatoriaAreas + areaR;
                    } else {
                        sumatoriaAreas = sumatoriaAreas - areaR;
                    }
                    centrosFiguras[i] = centros;                    
                    areasFiguras[i] = areas;
                    
                    String datosRectangulox = centros;
                    
                    int inicioRectangulox = datosRectangulox.indexOf("x");
                    int finRectangulox = datosRectangulox.indexOf(",");

                    int inicioRectanguloy = datosRectangulox.indexOf("y");

                    //int inicioOpeTriangulo = datosTriangulo.indexOf("$");
                        
                    String xRectangulox = datosRectangulox.substring(inicioRectangulox +1, finRectangulox);
                    String yRectanguloy = datosRectangulox.substring(inicioRectanguloy +1);
                    
                    if (operacionRectangulo.equals("s")) {
                        sumatoriaAx = sumatoriaAx+(parseDouble(xRectangulox)*areaR);
                        sumatoriaAy = sumatoriaAy+(parseDouble(yRectanguloy)*areaR);
                    } else {
                        sumatoriaAx = sumatoriaAx+(parseDouble(xRectangulox)*-areaR);
                        sumatoriaAy = sumatoriaAy+(parseDouble(yRectanguloy)*-areaR);
                    }
                    
                    contador++;

                    
                }
                    }
                }
                System.out.println("Contador "+contador);
               

            lblAreas.setText("ΣA: "+sumatoriaAreas);
            lblAreasx.setText("ΣXA: "+sumatoriaAx);
            lblAreasy.setText("ΣYA: "+sumatoriaAy);
            
                    String stCentroide = calcularCentroide(sumatoriaAreas.toString(), sumatoriaAx.toString(), sumatoriaAy.toString());

                    Double xDist;
                    Double yDist;
                    
                    int inicioX = stCentroide.indexOf("x");
                    int finX = stCentroide.indexOf("y");
                    
                    int inicioY = stCentroide.indexOf("y");

                    xDist = parseDouble(stCentroide.substring(inicioX +1, finX));
                    yDist = parseDouble(stCentroide.substring(inicioY +1));
            
                    lblCentroideX.setText("X = "+df.format(xDist));
                    lblCentroideY.setText("Y = "+df.format(yDist));
            
            
            System.out.println("Suma Areas "+sumatoriaAreas);
            System.out.println("Suma Areasx "+sumatoriaAx);
            System.out.println("Suma Areasy "+sumatoriaAy);
            
            /*
            for (int i = 0; i < centrosFiguras.length; i++) {
                System.out.println("Centros Finales "+i+":"+centrosFiguras[i]);
            }
            for (int i = 0; i < areasFiguras.length; i++) {
                System.out.println("Areas Figuras "+i+":"+areasFiguras[i]);
            }
            */
 
        });
        
        return vbCentroides;
    }
    
    public void iniciarCaptura(String nRectx, String nCirx, String nTrix){
          
        if (nRectx.length() == 0 && nCirx.length() == 0 && nTrix.length() == 0) {
            System.out.println("Debe ingresar almenos 1 figura");
        }else{
            
            if (nRectx.length() != 0) {
                nRect = parseInt(nRectx);
                datosRect = new String[nRect];
            }
            if (nCirx.length() != 0) {
                nCir = parseInt(nCirx);
                datosCir = new String[nCir];
            }
            if (nTrix.length() != 0) {
                nTri = parseInt(nTrix);
                datosTri = new String[nTri];
            }

            if (nRect > 0 && nCir > 0 && nTri > 0) {
                                
                for (int i = 1; i <= nRect; i++) {
                    mostrarDialogo("Rectángulo "+i,"r",i);
                }
                for (int j = 1; j <= nCir; j++) {
                    mostrarDialogo("Circulo "+j,"c",j);
                }
                for (int k = 1; k <= nTri; k++) {
                    mostrarDialogo("Triángulo "+k,"r",k);
                }
                
            }else{
                if (nRect > 0 && nTri > 0) {
                    for (int i = 1; i <= nRect; i++) {
                          mostrarDialogo("Rectángulo "+i,"r",i);
                    }
                    for (int k = 1; k <= nTri; k++) {
                                mostrarDialogo("Triángulo "+k,"t",k);
                            }
                }else{
                    if (nRect > 0) {
                        for (int j = 1; j <= nRect; j++) {
                            mostrarDialogo("Rectangulo "+j,"r",j);
                        }
                    } else {
                        if (nTri > 0) {
                            
                            for (int k = 1; k <= nTri; k++) {
                                mostrarDialogo("Triángulo "+k,"t",k);
                            }
                            
                        }
                    }
                }
            }
            datos="";
                        

        }
        
    }
    
    public void mostrarDialogo (String stTitulo, String figura, int pos){
                Stage dialogoStage = new Stage();
                
                dialogoStage.initModality(Modality.WINDOW_MODAL);
                dialogoStage.initStyle(StageStyle.UNDECORATED);

                txtBase.setMaxWidth(80);
                txtBase.clear();
                txtAltura.setMaxWidth(80);
                txtAltura.clear();
                txtDistanciax.setMaxWidth(80);
                txtDistanciax.clear();
                txtDistanciay.setMaxWidth(80);
                txtDistanciay.clear();
                
                GridPane grid = new GridPane();
                grid.add(new Label("Base"), 0, 0);
                grid.add(txtBase, 1, 0);
                grid.add(new Label("Altura"), 0, 1);
                grid.add(txtAltura, 1, 1);
                grid.add(new Label("Distancia en X"), 0, 2);
                grid.add(txtDistanciax, 1, 2);
                grid.add(new Label("Distancia en Y"), 0, 3);
                grid.add(txtDistanciay, 1, 3);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setAlignment(Pos.CENTER);
                
                ToggleGroup grupo = new ToggleGroup();
                
                RadioButton suma = new RadioButton("Suma");
                suma.setToggleGroup(grupo);
                suma.setSelected(true);
                
                RadioButton resta = new RadioButton("Resta");
                resta.setToggleGroup(grupo);

                

                Button btnAgregar = new Button("Agregar");
                btnAgregar.setOnMouseClicked((MouseEvent) -> {
                    
                    String ope = "";
                    String x = "0";
                    String y = "0";
                    
                    if (suma.isSelected()) {
                        ope = "s";
                    } else {
                        if (resta.isSelected()) {
                            ope = "r";
                        }
                    }
                    
                    if (!txtDistanciax.getText().isEmpty() && !txtDistanciay.getText().isEmpty() ) {
                        x = txtDistanciax.getText();
                        y = txtDistanciay.getText();
                    } else {
                        if (!txtDistanciax.getText().isEmpty()) {
                            x = txtDistanciax.getText();
                        }
                        if (!txtDistanciay.getText().isEmpty()) {
                            y = txtDistanciay.getText();
                        }
                    }
                    
                    if (figura.equals("r")) {
                        
                        datosRect[pos-1] = "b"+txtBase.getText()+",a"+txtAltura.getText()+"$"+ope+"x"+x+"y"+y;
                        System.out.println("");
                    }else{
                        if (figura.equals("c")) {
                            datosCir[pos-1] = "b"+txtBase.getText()+",a"+txtAltura.getText()+"$"+ope+"x"+x+"y"+y;
                        }else{
                            datosTri[pos-1] = "b"+txtBase.getText()+",a"+txtAltura.getText()+"$"+ope+"x"+x+"y"+y;
                        }
                    }
                    
                    dialogoStage.fireEvent(new WindowEvent(dialogoStage, WindowEvent.WINDOW_CLOSE_REQUEST));
                });

                Button btnCerrar = new Button("Cancelar");
                btnCerrar.setOnAction((ActionEvent e1) -> { 
                    dialogoStage.fireEvent(new WindowEvent(dialogoStage, WindowEvent.WINDOW_CLOSE_REQUEST));
                });
                
                HBox hbRadioButton = new HBox();
                hbRadioButton.getChildren().addAll(suma,resta);
                hbRadioButton.setAlignment(Pos.BASELINE_CENTER);
                hbRadioButton.setSpacing(10);
                hbRadioButton.setPadding(new Insets(0, 0, 15, 0));
                
                HBox hbBotones = new HBox();
                hbBotones.getChildren().addAll(btnAgregar,btnCerrar);
                hbBotones.setAlignment(Pos.BASELINE_CENTER);
                hbBotones.setSpacing(10);
                
                VBox vbForm = new VBox();
                vbForm.getChildren().addAll(grid, hbRadioButton, hbBotones);
                vbForm.setPadding(new Insets(15, 12, 15, 12));
                vbForm.setAlignment(Pos.TOP_CENTER);
                vbForm.setSpacing(30);

                BorderPane contenido = new BorderPane();
                contenido.setTop(crearEncabezado(dialogoStage, stTitulo));
                contenido.setCenter(vbForm);

                Scene scene1 = new Scene(contenido, 400, 360);

                scene1.getStylesheets().add("css/JMetro.css");
                scene1.getStylesheets().add("skin/undecorator.css");

                dialogoStage.setScene(scene1);
                dialogoStage.showAndWait();
                
                        
    }
    
    public String calcularCentroide(String areas, String ax, String ay) {
        
        Double xBarra = parseDouble(ax)/parseDouble(areas);
        Double yBarra = parseDouble(ay)/parseDouble(areas);
        
        String centroide = "x"+xBarra.toString()+"y"+yBarra.toString();
        
        return centroide;

    }
    
    public String calcularCentroideRect(String base, String altura, String xDist, String yDist) {
        
        if (xDist == "" & yDist == "") {
            xDist = "0";
            yDist = "0";
        }else{
            if (xDist == "") {
                xDist = "0";
            }
            if (yDist == "") {
                yDist = "0";
            }
        }
        
        double x = (parseDouble(base)/2)+parseDouble(xDist);
        double y = (parseDouble(altura)/2)+parseDouble(yDist);
        
        String centroide = "x"+String.valueOf(x)+",y"+String.valueOf(y);
        
        return centroide;

    }

    
    public String calcularCentroideCir(String base, String altura) {
        
        return null;

    }
    
    public void calcularCentroideSemiCir(String base, String altura) {

    }
    
    public String calcularCentroideTri(String base, String altura, String xDist, String yDist) {
        if (xDist == "" & yDist == "") {
            xDist = "0";
            yDist = "0";
        }else{
            if (xDist == "") {
                xDist = "0";
            }
            if (yDist == "") {
                yDist = "0";
            }
        }
        
        double x = (parseDouble(base)/3)+parseDouble(xDist);
        double y = (parseDouble(altura)/3)+parseDouble(yDist);
        
        String centroide = "x"+String.valueOf(x)+",y"+String.valueOf(y);
        
        return centroide;
    }
    
    public String calcularAreaRectangulo(final String base, final String altura, final String operacion) {
        Double areaR = 0.0;
        if (operacion.equals("s")) {
            areaR = (parseDouble(base)*parseDouble(altura));
        } else {
            if (operacion.equals("r")){
                areaR = -(parseDouble(base)*parseDouble(altura));
            }
        }
        
        return areaR.toString();
    }
    
    public Double calcularAreaRectangulo(final Double base, final Double altura) {
        Double area = 0.0;
        
        area = (base*altura);
        
        return area;
    }
        
    public double calcularAreaCirculo(final double base, final double altura, final String operacion) {
        double area = 0;
        if (operacion.equals("s")) {
            area = ((base*altura)/2);
        } else {
            if (operacion.equals("r")){
                area = -(base*altura);
            }
        }
        
        return area;
    }
    
    public double calcularAreaSemiCirculo(final double base, final double altura) {
        double area = (base*altura);
        return area;
    }
    
    public String calcularAreaTriangulo(final String base, final String altura, final String operacion) {
        Double areaT = 0.0;
        if (operacion.equals("s")) {
            areaT = 0.5*(parseDouble(base)*parseDouble(altura));
        } else {
            if (operacion.equals("r")){
                areaT = -0.5*(parseDouble(base)*parseDouble(altura));
            }
        }        
        return areaT.toString();
    }
    
    public Double calcularAreaTriangulo(final Double base, final Double altura) {
        Double areaT = 0.0;
        areaT = (base*altura)/2;
        
        return areaT;
    }
    
    private GridPane crearEncabezado(Stage stage, String stTitulo){
        HBox botones = new HBox();
        HBox tituloHB = new HBox();
        GridPane grid = new GridPane();
        
        Label titulo = new Label(stTitulo);
        titulo.setFont(metroFont);
        titulo.setId("titulo");
        titulo.setAlignment(Pos.TOP_LEFT);
        /*
        Image img = new Image("img/icon1.png");
        ImageView imgView = new ImageView(img);
        */
        
        Button btnCerrar = new Button("");
                btnCerrar.getStyleClass().add("decoration-button-close");
                btnCerrar.setId("close");
                btnCerrar.setPrefHeight(30.0);
                btnCerrar.setPrefWidth(34.0);
                btnCerrar.setFocusTraversable(false);

        btnCerrar.setOnAction((ActionEvent e1) -> { 
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        
        botones.getChildren().addAll(btnCerrar);
        botones.setAlignment(Pos.TOP_RIGHT);
        botones.getStyleClass().add("rigth");
        
        tituloHB.getChildren().addAll(titulo);
        tituloHB.getStyleClass().add("left");
        tituloHB.setSpacing(5);
        tituloHB.setPadding(new Insets(5, 10, 10, 10));
        
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
