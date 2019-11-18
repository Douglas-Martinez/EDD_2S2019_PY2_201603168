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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
            //System.out.println(System.getProperty("user.dir") + "\\src\\Reportes\\matriz.png");
            ImageIcon imc = new ImageIcon(System.getProperty("user.dir") + Proyecto2_2.img);
            imc.getImage().flush();
            Icon i = new ImageIcon(imc.getImage().getScaledInstance(1100, 900, java.awt.Image.SCALE_DEFAULT));
            JLabel im = new JLabel();
            im.setIcon(i);
            JFrame jf = new JFrame();
            jf.setLayout(new FlowLayout());
            jf.add(im);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.pack();
            jf.setVisible(true);
            
            
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Ruta de la imagen: " + System.getProperty("user.dir") + Proyecto2_2.img);
            a.showAndWait();
            
            Desktop d = Desktop.getDesktop();
            File f = new File(System.getProperty("user.dir") + Proyecto2_2.img);
            try {
                d.open(f);
            } catch(Exception ex) {
                Alert aa = new Alert(Alert.AlertType.WARNING);
                aa.setHeaderText(Proyecto2_2.img);
                aa.setContentText(ex.getMessage());
                aa.showAndWait();
            }
        } catch(Exception e) {
            System.out.println("Error al abrir la imagen: " + System.getProperty("user.dir") + Proyecto2_2.img);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error al abrir: " + System.getProperty("user.dir") + Proyecto2_2.img);
            a.showAndWait();
            Desktop d = Desktop.getDesktop();
            File f = new File(System.getProperty("user.dir") + Proyecto2_2.img);
            try {
                d.open(f);
            } catch(Exception ex) {
                Alert aa = new Alert(Alert.AlertType.WARNING);
                aa.setHeaderText(Proyecto2_2.img);
                aa.setContentText(ex.getMessage());
                aa.showAndWait();
            }
        }
    }
    
    @FXML
    private void hash(MouseEvent evt) {
        Proyecto2_2.usuarios.graficar();
        //Proyecto2_2.img = "src/Reportes/tablaHash.png";
        Proyecto2_2.img = "\\Reportes\\tablaHash.png";
        llamar();
        Proyecto2_2.log.Push("Generacion del Reporte Tabla Hash", Proyecto2_2.actual.usuario);
        Proyecto2_2.img = "";
    }
    
    @FXML
    private void graph(MouseEvent evt) {
        if(Proyecto2_2.actual != null) {
            Proyecto2_2.actual.matrix.graficarGrafo();
            //Proyecto2_2.img = "src/Reportes/grafo.png";
            Proyecto2_2.img = "\\Reportes\\grafo.png";
            llamar();
            Proyecto2_2.log.Push("Generacion del Reporte del Grafo de Directorios", Proyecto2_2.actual.usuario);
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
            Proyecto2_2.img = "\\Reportes\\matriz.png";
            llamar();
            Proyecto2_2.log.Push("Generacion del Reporte de la Matriz de Directorios", Proyecto2_2.actual.usuario);
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
            //Proyecto2_2.img = "src/Reportes/avl.png";
            Proyecto2_2.img = "\\Reportes\\avl.png";
            llamar();
            Proyecto2_2.log.Push("Generacion del Reporte del Arbol de Archivos de la carpeta " + Proyecto2_2.carpeta, Proyecto2_2.actual.usuario);
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
        //Proyecto2_2.img = "src/Reportes/log.png";
        Proyecto2_2.img = "\\Reportes\\log.png";
        llamar();
        Proyecto2_2.log.Push("Generacion del Reporte de la Bitacora de la Aplicacion", Proyecto2_2.actual.usuario);
        Proyecto2_2.img = "";
    }
}
