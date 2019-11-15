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
        //usuarios.insertar("Admin", "Admin");
        /*
        MatrizDispersa sm = new MatrizDispersa("Yo");
        sm.insertar("/", "Lol");
        sm.insertar("/", "Proyecto");
        sm.insertar("Lol", "Imagenes");
        sm.insertar("Lol", "Imagenes2");
        sm.insertar("Lol", "Packs");
        sm.insertar("Packs", "Dana");
        sm.graficar();
        sm.graficarGrafo();
        */
        /*
        AVL a = new AVL();
        a.insertar("alv.tugefa","Contenido1","YO");
        a.insertar("alv.tuputamadre","Contenido2","YOx2");
        a.insertar("boynas","Contenido3","Pito");
        a.insertar("caca.kk","Cacaroto","Goku");
        a.insertar("julio.txt","pack","Tu");
        a.insertar("putas.harry","putas","peluca");
        a.insertar("date.puto","loco","Crazy");
        a.graficar();
        */
        /*
        log.Push("Inicio de Sesion", "ALV");
        log.Push("Crear Archivo", "ALV");
        log.Push("Crear Carpeta", "ALV");
        for(int i = 0; i < 32000; i++) {}
        log.Push("Eliminar Archivo", "ALV");
        log.Push("Modificar Carpeta", "ALV");
        log.Push("Cierre de Sesion", "ALV");
        for(int i = 0; i < 640000; i++) {}
        log.Push("Inicio de Sesion", "Pito");
        log.Push("Eliminar Archivo", "Pito");
        log.Push("Modificar Carpeta", "Pito");
        log.Push("Cierre de Sesion", "Pito");
        log.graficar();
        */
        usuarios.insertar("Lolazo", "12345678");
        usuarios.buscar("Lolazo").matrix.insertar("/", "Proyectos");
        usuarios.buscar("Lolazo").matrix.insertar("/", "Reportes");
        usuarios.buscar("Lolazo").matrix.insertar("Proyectos", "EDD");
        usuarios.buscar("Lolazo").matrix.insertar("EDD", "Semestre1");
        
        boolean insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("alv.tugefa","Contenido1","Lolazo");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("alv.tuputamadre","Contenido2","YOx2");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("boynas","Contenido3","Pito");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("caca.kk","Cacaroto","Goku");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("julio.txt","pack","Tu");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("putas.harry","putas","peluca");
        insertar = usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.insertar("date.puto","loco","Crazy");
        
        usuarios.insertar("Alv", "qazwsxedc");
        insertar = usuarios.buscar("Alv").matrix.buscar("/", "/").archivos.insertar("julio.txt", "qwertyuiop", "zxcvbnm");
        
        actual = usuarios.buscar("Lolazo");
        //usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.listInOrder(usuarios.buscar("Lolazo").matrix.buscar("/", "/").archivos.root, 0);
        //System.out.println("");
        /*
        usuarios.insertar("Noobmaster69", "ElThortas");
        usuarios.insertar("Ana Morales", "Anabanana");
        usuarios.insertar("Pikachu", "yoteelijo");
        usuarios.insertar("Tu y Yo", "y Zaboomafoo");
        //usuarios.graficar();
        */
        launch(args);
    }
}
