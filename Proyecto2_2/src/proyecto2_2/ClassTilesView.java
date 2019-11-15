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
import javafx.scene.image.Image;
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
        
        if((CurrDirName == null)|| (CurrDirName.equals(""))) {
            CurrDirName = "/";
        }
        NodoFila nf = Proyecto2_2.actual.matrix.padres.buscar(CurrDirName);
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
                    img = new ImageView(new Image("img/text.png"));
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
                       //Seleccionar
                    } else if(evt.getClickCount() == 2){
                        String str = virbox.getId();
                        System.out.println("Presionado " + str);
                        String str1 = CurrDirStr + "/" + str;
                        System.out.println(str1);
                        String[] noml = ((Image)((ImageView)virbox.getChildren().get(0)).getImage()).impl_getUrl().split("/");
                        String nom = noml[noml.length - 1];
                        if(nom.equals("folder.png")) {
                           try {
                               CurrDirStr  = CurrDirStr + "/" + str;
                               setLabelTxt();
                               padre = carpeta;
                               carpeta = str;
                               CurrDirName = str;
                               CreateTiles();
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
        
        /*
        File[] fl;
        if(CurrDirFile == null) {
            CurrDirFile=new File("./");
        }
        
        fl = CurrDirFile.listFiles();
        if(CurrDirName.equals("This PC")) {
            fl = File.listRoots();
        }
        
        int len = fl.length;
        
        tilePane.getChildren().clear();
        for(int i = 0; i < len; i++) {
            //Label temp = new Label();
            Label title = new Label(fl[i].getName());
            title.setId(fl[i].getName());
            ImageView imageview = new ImageView(new Image("img/folder.png"));
            VBox vbox = new VBox();
            vbox.setId(fl[i].getName());
            vbox.getChildren().addAll(imageview,title);

            vbox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getClickCount() == 2) {
                        System.out.println("Tile pressed " + vbox.getId());
                        String str = vbox.getId();
                        String str1 = CurrDirStr + "\\" + str;
                        File f = new File(str1);
                        if(f.isFile()) {
                            Desktop d =Desktop.getDesktop();
                            try {
                                d.open(f);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else{
                            CurrDirStr = str1;
                            CurrDirFile = new File(CurrDirStr);
                            setLabelTxt();
                            tilePane.getChildren().clear();
                            CreateTiles();
                        }
                    }
                }
            });
            TilePane.setAlignment(vbox, Pos.BOTTOM_LEFT);
            tilePane.getChildren().add(vbox);
        }
        */
    }

    @Override
    public TreeItem<String>[] TreeCreate(File dir) { 
        return null;
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
