/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Clases.Usuario;
import Estructuras.MatrizDispersa;
import Estructuras.Pila;
import Estructuras.TablaHash;
import Nodos.NodoAVL;
import Nodos.NodoMatriz;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public static String img;
    
    //Transiciones
    public static String carpeta;
    public static String padre;
    
    //
    public static NodoAVL selA;
    public static NodoMatriz selC;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        //FXMLLoader fxml = new FXMLLoader(getClass().getResource("Ingreso.fxml"));
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Scene.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stg = new Stage(StageStyle.DECORATED);
        stg.setTitle("Ingreso");
        stg.setResizable(false);
        stg.setScene(new Scene(root));
        stg.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean insertar;
        
        usuarios.insertar("Admin", "Admin");
        //actual = usuarios.buscar("Admin");
        
        usuarios.insertar("Lolazo", "12345678");
        usuarios.buscar("Lolazo").matrix.insertar("/", "Proyectos");
        usuarios.buscar("Lolazo").matrix.insertar("/", "Reportes");
        usuarios.buscar("Lolazo").matrix.insertar("Proyectos", "EDD");
        usuarios.buscar("Lolazo").matrix.insertar("EDD", "Semestre1");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("alv.tugefa","Contenido1","Lolazo");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("alv.tuputamadre","Contenido2","YOx2");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("boynas","Contenido3","Pito");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("caca.kk","Cacaroto","Goku");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("julio.txt","pack","Tu");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("putas.harry","putas","peluca");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("date.puto","loco","Crazy");
        
        usuarios.insertar("Alv", "qazwsxedc");
        insertar = usuarios.buscar("Alv").matrix.buscar("/", "/").archivos.insertar("julio.txt", "qwertyuiop", "zxcvbnm");
        
        actual = usuarios.buscar("Lolazo");
        
        
        launch(args);
    }
}