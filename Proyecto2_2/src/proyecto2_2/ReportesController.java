/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Douglas
 */
public class ReportesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    private void llamar() {
        try {
//            Dialog d = new Dialog();
//            d.setTitle("Imagen");
//            d.setWidth(600);
//            d.setHeight(700);
//            
//            //ImageView iv = new ImageView(Proyecto2_2.img);
//            Label l1 = new Label();
//            l1.setGraphic(new ImageView(new Image(Proyecto2_2.img)));
//            //GridPane g = new GridPane();
//            
//            HBox hb = new HBox();
//            hb.setSpacing(10);
//            hb.getChildren().clear();
//            hb.getChildren().add(l1);
//            //g.add(l1, 0, 0);
//            //g.autosize();
//            d.getDialogPane().setContent(hb);
//            d.getDialogPane().getButtonTypes().add(ButtonType.OK);
//            d.showAndWait();
            
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("Image.fxml"));
            Parent root = (Parent) fxml.load();
            Stage stgo = new Stage(StageStyle.DECORATED);
            stgo.setResizable(false);
            stgo.setTitle("Reporte");
            stgo.setScene(new Scene(root));
            stgo.show();
            
        } catch(Exception e) {
            System.out.println("Error al abrir la imagen: " + Proyecto2_2.img);
        }
    }
    
    @FXML
    private void hash(MouseEvent evt) {
        Proyecto2_2.usuarios.graficar();
        Proyecto2_2.img = "./Reportes/tablaHash.png";
        llamar();
        Proyecto2_2.log.Push("Generacion del Reporte Tabla Hash", Proyecto2_2.actual.usuario);
        Proyecto2_2.contIm ++;
        Proyecto2_2.img = "";
    }
    
    @FXML
    private void graph(MouseEvent evt) {
        if(Proyecto2_2.actual != null) {
            Proyecto2_2.actual.matrix.graficarGrafo();
            Proyecto2_2.img = "./Reportes/grafo.png";
            llamar();
            Proyecto2_2.log.Push("Generacion del Reporte del Grafo de Directorios", Proyecto2_2.actual.usuario);
            Proyecto2_2.contIm ++;
            Proyecto2_2.img = "";
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error Usuario");
            a.setContentText("Usuario Invalido");
            a.showAndWait();
        }
    }
    
    @FXML
    private void matrix(MouseEvent evt) {
        if(Proyecto2_2.actual != null) {
            Proyecto2_2.actual.matrix.graficar();
            Proyecto2_2.img = "./Reportes/matriz.png";
            llamar();
            Proyecto2_2.log.Push("Generacion del Reporte de la Matriz de Directorios", Proyecto2_2.actual.usuario);
            Proyecto2_2.contIm ++;
            Proyecto2_2.img = "";
        } else {
           Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error Usuario");
            a.setContentText("Usuario Invalido");
            a.showAndWait(); 
        }
    }
    
    @FXML
    private void tree(MouseEvent evt) {
        if(Proyecto2_2.carpeta != null) {
            String p = "";
            if(Proyecto2_2.padre == null) {
                p = "/";
            } else {
                p = Proyecto2_2.padre;
            }
            Proyecto2_2.actual.matrix.buscar(p,Proyecto2_2.carpeta).archivos.graficar();
            Proyecto2_2.img = "./Reportes/avl.png";
            llamar();
            Proyecto2_2.log.Push("Generacion del Reporte del Arbol de Archivos de la carpeta " + Proyecto2_2.carpeta, Proyecto2_2.actual.usuario);
            Proyecto2_2.contIm ++;
            Proyecto2_2.img = "";
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error Carpeta");
            a.setContentText("Carpeta Invalido");
            a.showAndWait();
        }
    }
    
    @FXML
    private void log(MouseEvent evt) {
        Proyecto2_2.log.graficar();
        Proyecto2_2.img = "Reportes/log.png";
        llamar();
        Proyecto2_2.log.Push("Generacion del Reporte de la Bitacora de la Aplicacion", Proyecto2_2.actual.usuario);
        Proyecto2_2.contIm ++;
        Proyecto2_2.img = "";
    }
}
