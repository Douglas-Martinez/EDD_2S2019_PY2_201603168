/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Clases.FileInfo;
import Nodos.NodoFila;
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
public interface FileExplorer {
    Image getIconImageFX(File f);
    TreeItem<String>[] TreeCreate(NodoFila nf);
    String FindAbsolutePath(TreeItem<String> item, String s);
    boolean IsDrive(File f);
    void CreateTreeView(TreeView<String> treeview);
    void CreateTableView(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name, TableColumn<FileInfo, String> size);
    void CreateTableView();
    void CreateTilesView();
    void setLabelTxt();
    void Initiate();
    void setValues(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name);
    void CreateTiles();
}
