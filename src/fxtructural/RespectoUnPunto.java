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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author jmiguel
 */
public class RespectoUnPunto extends VBox {
    
    final Font metroFont = Font.loadFont(
      Fxtructural.class.getResource("SEGOEUIL.ttf").toExternalForm(), 22);
    
    public VBox RespectoUnPunto(){
         HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        VBox vBox = new VBox();
        
        
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
            double y1 = parseDouble(txtPy.getText());
            double z1 = parseDouble(txtPz.getText());
            

            double x2 = parseDouble(txtPx2.getText());
            double y2 = parseDouble(txtPy2.getText());
            double z2 = parseDouble(txtPz2.getText());

            double brazoi = parseDouble(df.format((x1-x2)));
            double brazoj = parseDouble(df.format((y1-y2)));
            double brazok = parseDouble(df.format((z1-z2)));

            double fuerza = parseDouble(txtFuerza.getText());

            double modulo = parseDouble(df.format(
            Math.sqrt(Math.pow(brazoi,2)+Math.pow(brazoj,2)+Math.pow(brazok,2))
            ));

            double lambdax = parseDouble(df.format((fuerza*(brazoi/modulo))));
            double lambday = parseDouble(df.format((fuerza*(brazoj/modulo))));
            double lambdaz = parseDouble(df.format((fuerza*(brazok/modulo))));
      
            producto[1] = x1;
            producto[2] = y1;
            producto[3] = z1;
            producto[4] = lambdax;
            producto[5] = lambday;
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
        
        return vBox;
        
    }
    
}
