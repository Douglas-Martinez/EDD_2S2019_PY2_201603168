/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Nodos.NodoAVL;
import Nodos.NodoFila;
import Nodos.NodoMatriz;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import static proyecto2_2.Proyecto2_2.actual;
import static proyecto2_2.Proyecto2_2.carpeta;
import static proyecto2_2.Proyecto2_2.padre;

/**
 *
 * @author Douglas
 */
public class ClassTilesView extends FileExplorerFx{

    ClassTilesView(){}

    @Override
    public void CreateTiles() {
        NodoFila nf;
        if((CurrDirName == null)|| (CurrDirName.equals(""))) {
            CurrDirName = "/";
            nf = Proyecto2_2.actual.matrix.padres.buscar("/");
        } else {
            nf = Proyecto2_2.actual.matrix.padres.buscar(CurrDirName);
        }
        NodoMatriz nm;
        if(Proyecto2_2.padre == null) {
            nm = Proyecto2_2.actual.matrix.buscar("/", carpeta);
        } else {
            nm = Proyecto2_2.actual.matrix.buscar(padre, carpeta);
        }
        int conA = nf.Contar();
        nm.archivos.linealizar();
        
        NodoAVL[] nal = new NodoAVL[nm.archivos.lista.size()];
        for(int q = 0; q < nal.length; q++) {
            nal[q] = nm.archivos.lista.get(q);
        }
        NodoFila[] nfl = new NodoFila[conA];
        NodoMatriz auxM = nf.der;
        int aux = 0;
        while(auxM != null) {
            if(!auxM.hijo.equals("/")) {
                nfl[aux] = actual.matrix.padres.buscar(auxM.hijo);
                aux++;
            }
            auxM = auxM.derecha;
        }
        
        FileInfo[] sts = new FileInfo[conA + nal.length];
        for(int e = 0; e < sts.length; e++) {
            String nombre = "";
            String date = "";
            String tipo = "";
            ImageView img = null;
            try {
                if(e < conA) {
                    img = new ImageView(new Image("img/folder.png"));
                    nombre = nfl[e].nombre;
                    NodoMatriz tmp;
                    if(padre == null) {
                        tmp = actual.matrix.buscar("/", carpeta);
                    } else {
                        tmp = actual.matrix.buscar(padre, carpeta);
                    }
                    if(tmp != null) {
                        date = tmp.fecha;
                    }
                    tipo = "C";
                } else {
                    img = new ImageView(new Image("img/icon.png"));
                    nombre = nal[e-conA].nombre;
                    date = nal[e-conA].timestamp;
                    tipo = "A";
                }
            } catch(Exception exc) {
                System.out.println("Exception building tiles, " + exc.getMessage());
            }
            sts[e] = new FileInfo(img,nombre,date,tipo);
        }
        
        tilePane.getChildren().clear();
        for(int w = 0; w < sts.length; w++) {
            Label titulo = new Label(sts[w].getName());
            titulo.setId(sts[w].getName());
            VBox virbox = new VBox();
            virbox.setId(sts[w].getName());
            virbox.getChildren().addAll(sts[w].getImage(),titulo);
            
            virbox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent evt) {
                    if(evt.getClickCount() == 1) {
                        for(Node v: tilePane.getChildren()) {
                            for(Node b: ((VBox)v).getChildren()) {
                                b.setEffect(null);
                            }
                        }
                        DropShadow ds = new DropShadow(BlurType.GAUSSIAN, Color.CORAL, 5, 12, 1, 1);
                        virbox.getChildren().get(0).setEffect(ds);
                        
                        String str = virbox.getId();
                        String[] noml = ((Image)((ImageView)virbox.getChildren().get(0)).getImage()).impl_getUrl().split("/");
                        String nom = noml[noml.length - 1];
                        
                        NodoMatriz aux;
                        if(nom.equals("folder.png")) {
                            aux = Proyecto2_2.actual.matrix.buscar(carpeta, str);
                            if(aux != null) {
                                Proyecto2_2.selC = aux;
                                System.out.println("Carpeta Seleccionada: " + Proyecto2_2.selC.hijo);
                            }
                        } else if(nom.equals("icon.png")) {
                            if(padre == null) {
                                aux = Proyecto2_2.actual.matrix.buscar("/", carpeta);
                            } else {
                                aux = Proyecto2_2.actual.matrix.buscar(padre, carpeta);
                            }
                            if(aux != null) {
                                NodoAVL tmp = aux.archivos.buscar(str);
                                if(tmp != null) {
                                    Proyecto2_2.selA = tmp;
                                    System.out.println("Archivo seleccionado: " + tmp.nombre + ". Cont: " + tmp.contenido);
                                }
                            }
                        }
                    } else if(evt.getClickCount() == 2){
                        String str = virbox.getId();
                        System.out.println("Presionado " + str);
                        String str1 = CurrDirStr + "/" + str;
                        System.out.println(str1);
                        String[] noml = ((Image)((ImageView)virbox.getChildren().get(0)).getImage()).impl_getUrl().split("/");
                        String nom = noml[noml.length - 1];
                        if(nom.equals("folder.png")) {
                           try {
                               if(CurrDirStr.equals("/")) {
                                   CurrDirStr  =  CurrDirStr + str;
                               } else {
                                   CurrDirStr  =  CurrDirStr + "/" + str;
                               }
                               setLabelTxt();
                               padre = carpeta;
                               carpeta = str;
                               CurrDirName = str;
                               CreateTiles();
                               
                               Proyecto2_2.selA = null;
                               Proyecto2_2.selC = null;
                           } catch(Exception e) {
                               System.out.println("Error with the tile open process. " + e.getMessage());
                           }
                        }
                    }
                }
            });
            TilePane.setAlignment(virbox, Pos.BOTTOM_LEFT);
            tilePane.getChildren().add(virbox);
        }
    }
    
    @Override
    public TreeItem<String>[] TreeCreate(NodoFila nf) { 
        return null;
    }
    
    @Override
    public String FindAbsolutePath(TreeItem<String> item, String s) {
        return null;
    }
    
    @Override
    public void CreateTreeView(TreeView<String> treeview) {}
    
    @Override
    public void CreateTableView (TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name, TableColumn<FileInfo, String> size) {}
    
    @Override
    public void CreateTableView() {}
    
    @Override
    public void CreateTilesView() {}
    
    @Override
    public void Initiate() {}
    
    @Override
    public void setValues(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name) {}
}
