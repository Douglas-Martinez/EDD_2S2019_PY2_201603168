/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Clases.Usuario;
import Estructuras.Pila;
import Estructuras.TablaHash;
import Interfaz.Ingreso;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Douglas
 */
public class Proyecto2_2 extends Application {

    public static TablaHash usuarios = new TablaHash();
    public static Pila log = new Pila();
    public static Usuario actual;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Ingreso.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stg = new Stage(StageStyle.UNDECORATED);
        stg.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Ingreso");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        usuarios.insertar("Admin", "Admin");
        launch(args);
    }
}
