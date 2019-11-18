/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.io.File;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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
//            ImageIcon imc = new ImageIcon(Proyecto2_2.img);
//            JLabel im = new JLabel(imc);
//            
//            JFrame jf = new JFrame();
//            jf.setLayout(new FlowLayout());
//            jf.setBounds(10, 10, 3000, 2000);
//            jf.add(im);
//            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            jf.pack();
//            jf.setVisible(true);
//            
            Desktop d = Desktop.getDesktop();
            File f = new File(Proyecto2_2.img);
            try {
                d.open(f);
            } catch(Exception ex) {
                Alert aa = new Alert(Alert.AlertType.WARNING);
                aa.setHeaderText(Proyecto2_2.img);
                aa.setContentText(ex.getMessage());
                aa.showAndWait();
            }
            
            /*
            Image img = new Image("Reportes/log.png");
            ImageView imgV = new ImageView(img);
            
            BorderPane bp = new BorderPane();
            bp.setCenter(imgV);
            
            ScrollPane sp = new ScrollPane();
            sp.setContent(bp);
            
            Scene sce = new Scene(sp);
            Stage stg = new Stage();
            stg.setScene(sce);
            stg.showAndWait();
            */
        } catch(Exception e) {
            System.out.println("Error al abrir la imagen: " + Proyecto2_2.img);
            //Alert a = new Alert(Alert.AlertType.WARNING);
            //a.setHeaderText(no + ". Se abrira una ventana");
            //a.setContentText(e.getMessage());
            //a.showAndWait();
        }
    }
    
    @FXML
    private void hash(MouseEvent evt) {
        Proyecto2_2.usuarios.graficar();
        Proyecto2_2.img = "src/Reportes/tablaHash.png";
        llamar();
        Proyecto2_2.log.Push("Generacion del Reporte Tabla Hash", Proyecto2_2.actual.usuario);
        Proyecto2_2.contIm ++;
        Proyecto2_2.img = "";
    }
    
    @FXML
    private void graph(MouseEvent evt) {
        if(Proyecto2_2.actual != null) {
            Proyecto2_2.actual.matrix.graficarGrafo();
            Proyecto2_2.img = "src/Reportes/grafo.png";
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
            //Proyecto2_2.img = "src/Reportes/matriz.png";
            Proyecto2_2.img = "src/Reportes/matriz.png";
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
            String p;
            if(Proyecto2_2.padre == null) {
                p = "/";
            } else {
                p = Proyecto2_2.padre;
            }
            Proyecto2_2.actual.matrix.buscar(p,Proyecto2_2.carpeta).archivos.graficar();
            Proyecto2_2.img = "src/Reportes/avl.png";
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
        Proyecto2_2.img = "src/Reportes/log.png";
        llamar();
        Proyecto2_2.log.Push("Generacion del Reporte de la Bitacora de la Aplicacion", Proyecto2_2.actual.usuario);
        Proyecto2_2.contIm ++;
        Proyecto2_2.img = "";
    }
}
