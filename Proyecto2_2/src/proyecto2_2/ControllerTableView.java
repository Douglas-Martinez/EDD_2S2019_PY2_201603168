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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import static proyecto2_2.FileExplorerFx.CurrDirName;
import static proyecto2_2.Proyecto2_2.actual;

/**
 * FXML Controller class
 *
 * @author Douglas
 */
public class ControllerTableView implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TableView<FileInfo> tableview;
    @FXML private TableColumn<FileInfo, ImageView> image;
    @FXML private TableColumn<FileInfo, String> date;
    @FXML private TableColumn<FileInfo, String> name;
    
    private Desktop desktop;
    //public ObservableList<FileInfo> list;
    public ObservableList<FileInfo> lista;
    public static FileExplorerFx Fx2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fx2 = new ClassTableView();
        Fx2.setValues(tableview,image,date,name);
                
        if(Fx2.CurrDirFile==null) {
            Fx2.CurrDirFile = new File("./");
            Fx2.CurrDirStr  = Fx2.CurrDirFile.getAbsolutePath();
        }
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            File[] fl;
            ObservableList<FileInfo> list;
            if(Fx2.CurrDirFile == null) {
                Fx2.CurrDirFile = new File("./");
            }
            { 
                fl = Fx2.CurrDirFile.listFiles();
            }
			
            FileInfo st[] = new FileInfo[fl.length];
            for(int i=0; i<fl.length;i++) {
                String s1 = null;
                String s3 = null;
                String s4 = null;
                ImageView img = null;
                try{
                    if(Fx2.IsDrive(fl[i])) {
                        img = new ImageView(Fx2.getIconImageFX(fl[i]));
                        s1 = fl[i].getAbsolutePath();
                        s4 = "1";
                    } else {
                        img = new ImageView(Fx2.getIconImageFX(fl[i]));
                        s1 = fl[i].getName();
                        s4 = "2";
                    }
                    s3 = sdf.format(fl[i].lastModified());
                } catch(Exception e) {
                    System.out.println("Exception detected in tableview strings: "+ e.getMessage());
                }
                st[i] = new FileInfo(img,s1,s3,s4);
            }

            list = FXCollections.observableArrayList(st);

            image.setCellValueFactory(new PropertyValueFactory<>("image"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tableview.setItems(list);
        }
    }

    @FXML
    private void handleTableMouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 1) {
            
        } else if(mouseEvent.getClickCount() == 2) {
            String str = tableview.getSelectionModel().getSelectedItem().getName();
            String s = Fx2.CurrDirStr + "\\" + str;
            System.out.println(s);
            File file = new File(s);
            if(file.isDirectory()) {
                try{
                    Fx2.CurrDirFile = file;
                    Fx2.CurrDirStr = Fx2.CurrDirFile.getAbsolutePath();
                    Fx2.setLabelTxt();
                    tableview.getItems().clear();
                    Fx2.CreateTableView();
                } catch(Exception e) {  
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
