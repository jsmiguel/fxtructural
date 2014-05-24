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

/**
 *
 * @author jmiguel
 */
public class RespectoUnEje extends VBox {
    
    final Font metroFont = Font.loadFont(
      Fxtructural.class.getResource("SEGOEUIL.ttf").toExternalForm(), 22);
    
    public VBox RespectoUnEje(){
    
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
            double z1 = parseDouble(txtPz22.getText());

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
        
        return vBox22;
        
    }
    
}
