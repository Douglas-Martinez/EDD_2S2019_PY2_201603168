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
    public static int contIm = 0;
    
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
        //Codigo para crear carpetas
        /*
        File f = new File("Descargas");
        boolean b = f.mkdir();
        if(b) {
            System.out.println(f.getName()+" creado");
        } else {
            System.out.println(f.getName()+" no creado");
        }
        f = new File("Reportes");
        b = f.mkdir();
        if(b) {
            System.out.println(f.getName()+" creado");
        } else {
            System.out.println(f.getName()+" no creado");
        }
        */
        usuarios.insertar("Admin", "Admin");
        //actual = usuarios.buscar("Admin");
        
        usuarios.insertar("Lolazo", "12345678");
        usuarios.buscar("Lolazo").matrix.insertar("/", "/Proyectos");
        usuarios.buscar("Lolazo").matrix.insertar("/", "/Reportes");
        usuarios.buscar("Lolazo").matrix.insertar("/Proyectos", "/Proyectos/EDD");
        usuarios.buscar("Lolazo").matrix.insertar("/Proyectos/EDD", "/Proyectos/EDD/Semestre1");
        usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("alv.123","Contenido1","Lolazo");
        usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("alv.2123","Contenido2","YOx2");
        usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("boynas","Contenido3","Pito");
        usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("asanfad","lol","Goku");
        usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("julio.txt","pack","Tu");
        
        usuarios.insertar("Alv", "qazwsxedc");
        usuarios.buscar("Alv").matrix.buscar("/", "/").archivos.insertar("julio.txt", "qwertyuiop", "zxcvbnm");
        
        actual = usuarios.buscar("Lolazo");
        
        
        launch(args);
    }
}