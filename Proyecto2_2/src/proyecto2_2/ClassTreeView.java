/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Clases.FileInfo;
import Nodos.NodoFila;
import Nodos.NodoMatriz;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 *
 * @author Douglas
 */
public class ClassTreeView extends FileExplorerFx {
    
    ClassTreeView(){};

    @Override
    public TreeItem<String>[] TreeCreate(NodoFila nf) {
        TreeItem<String>[] A = null;
        int n = nf.Contar();
        NodoFila[] fnf = new NodoFila[n];
        
        NodoMatriz auxM = nf.der;
        int aux = 0;
        while(auxM != null) {
            fnf[aux] = Proyecto2_2.actual.matrix.padres.buscar(auxM.hijo);
            aux++;
            auxM = auxM.derecha;
        }
        A = new TreeItem[n];
        int pos = 0;
        
        for(int j = 0; j < fnf.length; j++) {
            if(n==0) {
                A[pos] =new TreeItem<>(fnf[j].nombre, new ImageView(new Image(ClassLoader.getSystemResourceAsStream("img/folder.png"))));
                pos++;
            } else if(n > 0) {
                A[pos] =new TreeItem<>(fnf[j].nombre, new ImageView(new Image(ClassLoader.getSystemResourceAsStream("img/folder.png"))));
                try {
                    A[pos].getChildren().addAll(TreeCreate(fnf[j]));
                    pos++;
                } catch(Exception e) {
                    System.out.println("Exception treecreate " + fnf[j].nombre + ", " + e.getMessage());
                }
            }
        }
        return A;
    }
    
    @Override
    public String FindAbsolutePath(TreeItem<String> item, String s) {
        if((item.getParent() == null)||(item.getParent().getValue().equals("/"))) {
            if(s.equals("/")) {
                return "/";
            }
            return "/" + s;
        } else {
            String dir = item.getParent().getValue();
            dir = dir + "/" + s;
            return FindAbsolutePath(item.getParent(), dir);
        }
    }

    @Override
    public void CreateTreeView(TreeView<String> treeview) {
        NodoFila nf = Proyecto2_2.actual.matrix.padres.buscar("/");
        int cont = nf.Contar();
        if(cont > 0) {
            NodoFila[] fnf = new NodoFila[cont];
            NodoMatriz auxM = nf.der;
            int aux = 0;
            while(auxM != null) {
                if(!auxM.hijo.equals("/")) {
                    fnf[aux] = Proyecto2_2.actual.matrix.padres.buscar(auxM.hijo);
                    aux++;
                }
                auxM = auxM.derecha;
            }
            TreeItem<String> raiz = new TreeItem<>("/", new ImageView(new Image(ClassLoader.getSystemResourceAsStream("img/folder.png"))));
            TreeItem<String>[] sig = new TreeItem[cont];
            for(int j = 0; j < fnf.length; j++) {
                sig[j] = new TreeItem<>(fnf[j].nombre, new ImageView(new Image(ClassLoader.getSystemResourceAsStream("img/folder.png"))));
                try {
                    sig[j].getChildren().addAll(TreeCreate(fnf[j]));
                } catch(Exception e) {
                    System.out.println("Exception detected: " + e.fillInStackTrace() + "raiz.getChildren().addAll en vez de .add()");
                } finally {
                    raiz.getChildren().add(sig[j]);
                }
            }
            treeview.setRoot(raiz);
        } else {
            TreeItem<String> raiz = new TreeItem<>("/", new ImageView(new Image(ClassLoader.getSystemResourceAsStream("img/folder.png"))));
            treeview.setRoot(raiz);
        }
    }

    @Override
    public void CreateTableView(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name, TableColumn<FileInfo, String> size) {}

    @Override
    public void CreateTableView() {}

    @Override
    public void CreateTiles() {}
    
    @Override
    public void Initiate() {}

    @Override
    public void setValues(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name) {}

    @Override
    public void CreateTilesView() {}
}
