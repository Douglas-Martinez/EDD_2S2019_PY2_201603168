/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import java.awt.Desktop;
import java.io.BufferedWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static proyecto2_2.ControllerTableView.Fx2;
import static proyecto2_2.ControllerTilesView.Fx3;


/**
 * FXML Controller class
 *
 * @author Douglas
 */
public class Controller implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    @FXML private Button btn;
    @FXML private Pane secPane;
    @FXML private TreeView<String> treeview;
    @FXML private Label label;
    
    @FXML private Button btnReportes;
    @FXML private Button btnLogOut;
    
    private int count;
    static ClassTreeView Fx1;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        count = 0;
        Fx1 = new ClassTreeView();

        Fx1.CurrDirStr = "/";
        Fx1.CurrDirName = "/";
        Fx1.lbl = label;
        Fx2.lbl = label;
        label.setText(Fx1.CurrDirStr);
        try {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            secPane.getChildren().add(newLoadedPane);
        } catch(NullPointerException e) {
            e.printStackTrace();
        } catch(IOException e) { 
            e.printStackTrace();
        }
        Fx1.CreateTreeView(treeview);
        handle();
    }

    @FXML
    private void handleMouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 1){
            try {
                TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();
                Proyecto2_2.carpeta = item.getValue();
                if(item.getParent() == null) {
                    Proyecto2_2.padre = null;
                } else {
                    Proyecto2_2.padre = item.getParent().getValue();
                }
                Fx1.CurrDirName = item.getValue();
                System.out.println("Selected Text : " + item.getValue());
                Fx1.CurrDirStr = Fx1.FindAbsolutePath(item,item.getValue());
                Fx2.CurrDirStr = Fx1.CurrDirStr;
                label.setText("/"+Fx1.CurrDirStr);
                Fx2.tableview.getItems().clear();
                Fx2.CreateTableView();
                Fx3.CreateTiles();
            } catch(Exception x) {
                System.out.println(x.getMessage());
            }
        }
    }
    
    private void handle() {
        try {
            TreeItem<String> item = treeview.getRoot();
            Proyecto2_2.carpeta = item.getValue();
            if(item.getParent() == null) {
                Proyecto2_2.padre = null;
            } else {
                Proyecto2_2.padre = item.getParent().getValue();
            }
            Fx1.CurrDirName = item.getValue();
            //System.out.println("Selected Text : " + item.getValue());
            Fx1.CurrDirStr = Fx1.FindAbsolutePath(item,item.getValue());
            Fx2.tableview.getItems().clear();
            Fx2.CreateTableView();
            Fx3.CreateTiles();
        } catch(Exception x) {
            System.out.println(x.getMessage());
        }
    }

    @FXML
    private void loadFxml(ActionEvent event) throws IOException {
        count = ( count+1 )%2;
        Pane newLoadedPane;
        secPane.getChildren().clear();
        if(count==0) { 
            newLoadedPane =  FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        } else {
            newLoadedPane =  FXMLLoader.load(getClass().getResource("Scene3.fxml"));
        }
        secPane.getChildren().add(newLoadedPane);
        Fx2.CreateTableView();
        Proyecto2_2.log.Push("Cambio la vista del contenido de la carpeta", Proyecto2_2.actual.usuario);
    }
    
    @FXML
    private void LogOut(MouseEvent evt) throws IOException {
        //Open Ingreso
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Ingreso.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stgo = new Stage(StageStyle.DECORATED);
        stgo.setResizable(false);
        stgo.setTitle("Ingreso");
        stgo.setScene(new Scene(root));
        stgo.show();
        //Close this
        Stage stgc = (Stage) btnLogOut.getScene().getWindow();
        stgc.close();
        Proyecto2_2.log.Push("Cerrar Sesion", Proyecto2_2.actual.usuario);
        Proyecto2_2.actual = null;
    }
    
    @FXML
    private void CargaMasiva(MouseEvent ect) {
        
    }
    
    @FXML
    private void Subir(MouseEvent evt) {
        
    }
    
    @FXML
    private void Reportes(MouseEvent evt) {
        
    }
    
    @FXML
    private void CrearC(MouseEvent evt) {
        
    }
    
    @FXML
    private void ModificarC(MouseEvent evt) {
        
    }
    
    @FXML
    private void EliminarC(MouseEvent evt) {
        
    }
    
    @FXML
    private void CrearA(MouseEvent evt) {
        
    }
    
    @FXML
    private void ModificarA(MouseEvent evt) {
        
    }
    
    @FXML
    private void EliminarA(MouseEvent evt) {
        
    }
    
    @FXML
    private void CargarA(MouseEvent evt) {
        
    }
    
    @FXML
    private void CompartirA(MouseEvent evt) {
        
    }
    
    @FXML
    private void DescargarA(MouseEvent evt) {
        
        PrintWriter escribir;
        try {
            String nombre = "./src/Descargas/" + "";
            escribir = new PrintWriter(new BufferedWriter(new FileWriter(nombre)));
            escribir.println("");
            escribir.close();
            /*
            File open = new File(nombre);
            Desktop d = Desktop.getDesktop();
            
            try {
                d.open(open);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }*/
            
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        
    }
}
