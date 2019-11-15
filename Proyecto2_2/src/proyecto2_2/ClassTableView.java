/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Nodos.NodoAVL;
import Nodos.NodoFila;
import Nodos.NodoMatriz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.File;
import javafx.scene.image.Image;
import static proyecto2_2.Proyecto2_2.actual;
import static proyecto2_2.Proyecto2_2.carpeta;
import static proyecto2_2.Proyecto2_2.padre;

/**
 *
 * @author Douglas
 */
public class ClassTableView extends FileExplorerFx {
    
    ClassTableView(){}
	
    @Override
    public void setValues(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image,TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name){
        this.tableview = tableview;
        this.date = date;
        this.name=name;
        this.image=image;
    }

    @Override
    public void CreateTableView() {
        if((CurrDirName == null) || CurrDirName.equals("")) {
            CurrDirName = "/";
        }
        NodoFila nf = Proyecto2_2.actual.matrix.padres.buscar(CurrDirName);
        NodoMatriz nm;
        if(Proyecto2_2.padre == null) {
            nm = Proyecto2_2.actual.matrix.buscar("/", Proyecto2_2.carpeta);
        } else {
            nm = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.padre, Proyecto2_2.carpeta);
        }
        int conA = nf.Contar();
        nm.archivos.linealizar();
        NodoAVL[] lna = new NodoAVL[nm.archivos.lista.size()];
        for(int q = 0; q < lna.length; q++) {
            lna[q] = nm.archivos.lista.get(q);
        }
        NodoFila[] lnf = new NodoFila[conA];
        NodoMatriz auxM = nf.der;
        int aux = 0;
        while(auxM != null) {
            if(!auxM.hijo.equals("/")) {
                lnf[aux] = Proyecto2_2.actual.matrix.padres.buscar(auxM.hijo);
                aux++;
            }
            auxM = auxM.derecha;
        }
        ObservableList<FileInfo> lista;
        FileInfo[] sts = new FileInfo[conA + lna.length];
        for(int w = 0; w < sts.length; w++) {
            String s1 = "";
            String s2 = "";
            String s3 = "";
            ImageView img = null;
            try {
                if(w < conA) {
                    img = new ImageView(new Image(ClassLoader.getSystemResourceAsStream("img/folder.png")));
                    s1 = lnf[w].nombre;
                    NodoMatriz tmp;
                    if(padre == null) {
                        tmp = actual.matrix.buscar("/", carpeta);
                    } else {
                        tmp = actual.matrix.buscar(padre, carpeta);
                    }
                    if(tmp != null) {
                        s2 = tmp.fecha;
                    } 
                    s3 = "C";
                } else {
                    img = new ImageView(new Image(ClassLoader.getSystemResourceAsStream("img/text.png")));
                    s1 = lna[w-conA].nombre;
                    s2 = lna[w-conA].timestamp;
                    s3 = "A";
                }
            } catch(Exception e) {
                System.out.println("Exception detected in tableview strings: " + e.getMessage());
            }
            sts[w] = new FileInfo(img,s1,s2,s3);
        }
        lista = FXCollections.observableArrayList(sts);

        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableview.setItems(lista);
        /*
        sdf = new SimpleDateFormat("dd/MM/yy");
        File[] fl;
        ObservableList<FileInfo> list;
        if(CurrDirFile==null) {
            CurrDirFile = new File("./"); 
        }
        if(CurrDirName.equals("This PC")) {
            fl = File.listRoots();
        } else {
            fl = CurrDirFile.listFiles();
        }
        FileInfo st[] = new FileInfo[fl.length];
        for(int i=0; i<fl.length;i++){
            String s1 = null;
            String s3 = null;
            String s4 = null;
            ImageView img = null;
            
            try {
                if(IsDrive(fl[i])) {
                    img = new ImageView(getIconImageFX(fl[i]));
                    s1 = fl[i].getAbsolutePath();
                    s4 = "1";
                } else {
                    img = new ImageView(getIconImageFX(fl[i]));
                    s1 = fl[i].getName();
                    s4 = "2";
                }
                s3 = sdf.format(fl[i].lastModified());
            } catch(Exception e) {
                System.out.println("Exception detected in tableview strings: " + e.getMessage());
            }
            st[i] = new FileInfo(img,s1,s3,s4);
        }

        list = FXCollections.observableArrayList(st);

        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableview.setItems(list);
        s*/
    }

    @Override
    public void CreateTiles() { }
    
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
    public void CreateTilesView() {}
    
    @Override
    public void Initiate() {}
    
    @Override
    public void CreateTableView(TableView<FileInfo> tableview,TableColumn<FileInfo, ImageView> image,TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name,TableColumn<FileInfo, String> size) {}
}
