/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Nodos.NodoAVL;
import Nodos.NodoMatriz;
import java.awt.Desktop;
import java.io.BufferedWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import static proyecto2_2.ControllerTableView.Fx2;
import static proyecto2_2.ControllerTilesView.Fx3;
import static proyecto2_2.Proyecto2_2.selA;


/**
 * FXML Controller class
 *
 * @author Douglas
 */
public class Controller implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    @FXML private Button btn;
    @FXML private Pane secPane;
    @FXML private TreeView<String> treeview;
    @FXML private Label label;
    
    @FXML private Button btnReportes;
    @FXML private Button btnLogOut;
    
    private int count;
    static ClassTreeView Fx1;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        count = 0;
        Fx1 = new ClassTreeView();

        Fx1.CurrDirStr = "/";
        Fx1.CurrDirName = "/";
        Proyecto2_2.padre = "/";
        Proyecto2_2.carpeta = "/";
        Fx1.lbl = label;
        Fx2.lbl = label;
        label.setText(Fx1.CurrDirStr);
        try {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            secPane.getChildren().add(newLoadedPane);
        } catch(NullPointerException e) {
            e.printStackTrace();
        } catch(IOException e) { 
            e.printStackTrace();
        }
        Fx1.CreateTreeView(treeview);
        handle();
        Fx2.CurrDirStr = "";
    }

    @FXML
    private void handleMouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 1){
            try {
                TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();
                Proyecto2_2.carpeta = item.getValue();
                if(item.getParent() == null) {
                    Proyecto2_2.padre = null;
                } else {
                    Proyecto2_2.padre = item.getParent().getValue();
                }
                Fx1.CurrDirName = item.getValue();
                System.out.println("Selected Text : " + item.getValue());
                Fx1.CurrDirStr = Fx1.FindAbsolutePath(item,item.getValue());
                Fx2.CurrDirStr = Fx1.CurrDirStr;
                label.setText(Fx1.CurrDirStr);
                Fx2.tableview.getItems().clear();
                Fx2.CreateTableView();
                if(Fx3 != null) {
                    Fx3.CreateTiles();
                }
                Proyecto2_2.selC = null;
                Proyecto2_2.selA = null;
            } catch(Exception x) {
                System.out.println(x.getMessage());
            }
        }
    }
    
    private void handle() {
        try {
            TreeItem<String> item = treeview.getRoot();
            Proyecto2_2.carpeta = item.getValue();
            if(item.getParent() == null) {
                Proyecto2_2.padre = null;
            } else {
                Proyecto2_2.padre = item.getParent().getValue();
            }
            Fx1.CurrDirName = item.getValue();
            if(Fx1.CurrDirName.equals("/")) {
                label.setText("/");
            } else {
                label.setText("/"+Fx1.CurrDirStr);
                Fx1.CurrDirStr = Fx1.FindAbsolutePath(item,item.getValue());
                Fx2.CurrDirStr = Fx1.CurrDirStr;
            }
            Fx1.CurrDirStr = Fx1.FindAbsolutePath(item,item.getValue());
            Fx2.tableview.getItems().clear();
            Fx2.CreateTableView();
            if(Fx3 != null) {
                Fx3.CreateTiles();
            }
            Proyecto2_2.selC = null;
            Proyecto2_2.selA = null;
        } catch(Exception x) {
            System.out.println(x.getMessage());
        }
    }

    @FXML
    private void loadFxml(ActionEvent event) throws IOException {
        count = ( count+1 )%2;
        Pane newLoadedPane;
        secPane.getChildren().clear();
        if(count==0) { 
            newLoadedPane =  FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        } else {
            newLoadedPane =  FXMLLoader.load(getClass().getResource("Scene3.fxml"));
        }
        secPane.getChildren().add(newLoadedPane);
        Fx2.CreateTableView();
        Proyecto2_2.selC = null;
        Proyecto2_2.selA = null;
        Proyecto2_2.log.Push("Cambio la vista del contenido de la carpeta", Proyecto2_2.actual.usuario);
    }
    
    @FXML
    private void LogOut(MouseEvent evt) throws IOException {
        //Open Ingreso
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Ingreso.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stgo = new Stage(StageStyle.DECORATED);
        stgo.setResizable(false);
        stgo.setTitle("Ingreso");
        stgo.setScene(new Scene(root));
        stgo.show();
        //Close this
        Stage stgc = (Stage) btnLogOut.getScene().getWindow();
        stgc.close();
        Proyecto2_2.log.Push("Cerrar Sesion", Proyecto2_2.actual.usuario);
        Proyecto2_2.actual = null;
    }
    
    @FXML
    private void CargaMasiva(MouseEvent ect) {
        
    }
    
    @FXML
    private void Reportes(MouseEvent evt) {
        
    }
    
    @FXML
    private void CrearC(MouseEvent evt) {
        if(Proyecto2_2.actual != null) {
            TextInputDialog crear = new TextInputDialog();
            crear.setTitle("Crear Carpeta");
            crear.setHeaderText("La carpeta se creara en la ruta actual");
            crear.setContentText("Nombre de la carpeta:");
            
            Optional<String> r = crear.showAndWait();
            if(r.isPresent()) {
                if(r.get().equals("")) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Nombre vacio");
                    a.showAndWait();
                    a.setContentText("El nombre de la carpeta no puede estar vacio");
                    Proyecto2_2.log.Push("Fallo al crear carpeta: Nobre Vacio", Proyecto2_2.actual.usuario);
                } else {
                    Proyecto2_2.actual.matrix.insertar(Proyecto2_2.carpeta, r.get());
                    Proyecto2_2.log.Push("Crear carpeta: " + r.get(), Proyecto2_2.actual.usuario);
                    Fx1.CreateTreeView(treeview);
                    Fx2.tableview.getItems().clear();
                    Fx2.CreateTableView();
                    if(Fx3 != null) {
                        Fx3.CreateTiles();
                    }
                }
            }
        }
    }
    
    @FXML
    private void ModificarC(MouseEvent evt) {
        Proyecto2_2.selC = null;
        Proyecto2_2.selA = null;
    }
    
    @FXML
    private void EliminarC(MouseEvent evt) {
        Proyecto2_2.selC = null;
        Proyecto2_2.selA = null;
    }
    
    @FXML
    private void CrearA(MouseEvent evt) {
        if(Proyecto2_2.actual != null) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Crear Archivo");
            dialog.setGraphic(null);

            ButtonType change = new ButtonType("Modificar", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(change, ButtonType.CANCEL);
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nombre = new TextField();
            TextField cont = new TextField();

            grid.add(new Label("Nombre:"), 0, 0);
            grid.add(nombre, 1, 0);
            grid.add(new Label("Contenido:"), 0, 1);
            grid.add(cont, 1, 1);
            
            Node acept = dialog.getDialogPane().lookupButton(change);
            dialog.getDialogPane().setContent(grid);
            
            Optional<Pair<String, String>> result = dialog.showAndWait();
            if(result.isPresent()) {
                if(nombre.getText().equals("")) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Nombre vacio");
                    a.setContentText("El nombre del archivo no puede estar vacio");
                    a.showAndWait();
                    Proyecto2_2.log.Push("Fallo al crear archivo: Nobre Vacio", Proyecto2_2.actual.usuario);
                } else {
                    NodoMatriz nm;
                    if(!(Proyecto2_2.padre == null) && !(Proyecto2_2.carpeta == null)) {
                        nm = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.padre, Proyecto2_2.carpeta);
                    } else {
                        if((Proyecto2_2.padre == null) && (Proyecto2_2.carpeta == null)) {
                            nm = Proyecto2_2.actual.matrix.buscar("/", "/");
                        } else {
                            if(Proyecto2_2.padre == null) {
                                nm = Proyecto2_2.actual.matrix.buscar("/", Proyecto2_2.carpeta);
                            } else {
                                nm = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.carpeta, "/");
                            }
                        }
                    }
                    if(nm != null) {
                        if(nm != null) {
                            boolean acc = nm.archivos.insertar(nombre.getText(), cont.getText(), Proyecto2_2.actual.usuario);
                            if(acc == true) {
                                Proyecto2_2.log.Push("Archivo creado: " + nombre.getText(), Proyecto2_2.actual.usuario);
                            } else {
                                NodoAVL sob = nm.archivos.buscar(nombre.getText());
                                if(sob != null) {
                                    sob.contenido = cont.getText();
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setTitle("Sobreescirutra");
                                    a.setContentText("Contenido del archivo " + sob.nombre + " sobreescrito");
                                    a.showAndWait();
                                    Proyecto2_2.log.Push("Sobreescritura del Archivo: " + sob.nombre, Proyecto2_2.actual.usuario);
                                }
                            }
                        }
                        Fx2.tableview.getItems().clear();
                        Fx2.CreateTableView();
                        if(Fx3 != null) {
                            Fx3.CreateTiles();
                        }
                    }
                }
            }
        }
        Proyecto2_2.selC = null;
        selA = null;
    }
    
    @FXML
    private void ModificarA(MouseEvent evt) {
        if(Proyecto2_2.selA != null) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Modificar Archivo");
            dialog.setHeaderText("Archivo: " + selA.nombre);
            dialog.setGraphic(null);

            ButtonType change = new ButtonType("Modificar", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(change, ButtonType.CANCEL);
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nombre = new TextField();
            nombre.setPromptText(Proyecto2_2.selA.nombre);
            TextField cont = new TextField();
            cont.setPromptText(Proyecto2_2.selA.contenido);

            grid.add(new Label("Nombre:"), 0, 0);
            grid.add(nombre, 1, 0);
            grid.add(new Label("Contenido:"), 0, 1);
            grid.add(cont, 1, 1);
            
            Node acept = dialog.getDialogPane().lookupButton(change);
            dialog.getDialogPane().setContent(grid);
            
            Optional<Pair<String, String>> result = dialog.showAndWait();
            if(result.isPresent()) {
                NodoMatriz nm = Proyecto2_2.actual.matrix.buscar(selA.padre, selA.hijo);
                String s1 = selA.nombre;
                String s2 = selA.contenido;
                
                nm.archivos.eliminar(selA.nombre);
                if(!(nombre.getText().equals(""))) {
                    boolean acc = nm.archivos.insertar(nombre.getText(), cont.getText(), Proyecto2_2.actual.usuario);
                    if(acc == true) {
                        Proyecto2_2.log.Push("Archivo creado: " + nombre.getText(), Proyecto2_2.actual.usuario);
                    } else {
                        NodoAVL sob = nm.archivos.buscar(nombre.getText());
                        if(sob != null) {
                            if(cont.getText().equals("")) {
                                sob.contenido = s2;
                            } else {
                                sob.contenido = cont.getText();
                            }
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setTitle("Sobreescirutra");
                            a.setContentText("Contenido del archivo " + sob.nombre + " sobreescrito");
                            a.showAndWait();
                            Proyecto2_2.log.Push("Sobreescritura del Archivo: " + sob.nombre, Proyecto2_2.actual.usuario);
                        }
                    }
                } else {
                    if(cont.getText().equals("")) {
                        boolean acc = nm.archivos.insertar(s1, s2, Proyecto2_2.actual.usuario);
                    } else {
                        boolean acc = nm.archivos.insertar(s1, cont.getText(), Proyecto2_2.actual.usuario);
                    }
                }
                /*
                if(!(nombre.getText().equals("")) && !(cont.getText().equals(""))) {
                    boolean acc = nm.archivos.insertar(nombre.getText(), cont.getText(), Proyecto2_2.actual.usuario);
                    if(acc == true) {
                        Proyecto2_2.log.Push("Archivo creado: " + nombre.getText(), Proyecto2_2.actual.usuario);
                    } else {
                        NodoAVL sob = nm.archivos.buscar(nombre.getText());
                        if(sob != null) {
                            sob.contenido = cont.getText();
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setTitle("Sobreescirutra");
                            a.setContentText("Contenido del archivo " + sob.nombre + " sobreescrito");
                            a.showAndWait();
                            Proyecto2_2.log.Push("Sobreescritura del Archivo: " + sob.nombre, Proyecto2_2.actual.usuario);
                        }
                    }
                } else {
                    if(nombre.getText().equals("") && cont.getText().equals("")) {
                        boolean acc = nm.archivos.insertar(s1, s2, Proyecto2_2.actual.usuario);
                        
                        if(acc == true) {
                            Proyecto2_2.log.Push("Archivo creado: " + nombre.getText(), Proyecto2_2.actual.usuario);
                        } else {
                            NodoAVL sob = nm.archivos.buscar(nombre.getText());
                            if(sob != null) {
                                sob.contenido = cont.getText();
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setTitle("Sobreescirutra");
                                a.setContentText("Contenido del archivo " + sob.nombre + " sobreescrito");
                                a.showAndWait();
                                Proyecto2_2.log.Push("Sobreescritura del Archivo: " + sob.nombre, Proyecto2_2.actual.usuario);
                            }
                        }
                        
                    } else {
                        if(nombre.getText().equals("")) {
                            boolean acc = nm.archivos.insertar(s1, cont.getText(), Proyecto2_2.actual.usuario);
                            
                            if(acc == true) {
                                Proyecto2_2.log.Push("Archivo creado: " + nombre.getText(), Proyecto2_2.actual.usuario);
                            } else {
                                NodoAVL sob = nm.archivos.buscar(nombre.getText());
                                if(sob != null) {
                                    sob.contenido = cont.getText();
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setTitle("Sobreescirutra");
                                    a.setContentText("Contenido del archivo " + sob.nombre + " sobreescrito");
                                    a.showAndWait();
                                    Proyecto2_2.log.Push("Sobreescritura del Archivo: " + sob.nombre, Proyecto2_2.actual.usuario);
                                }
                            }
                            
                        } else {
                            boolean acc = nm.archivos.insertar(nombre.getText(), s2, Proyecto2_2.actual.usuario);
                                if(acc == true) {
                                    Proyecto2_2.log.Push("Archivo creado: " + nombre.getText(), Proyecto2_2.actual.usuario);
                                } else {
                                    NodoAVL sob = nm.archivos.buscar(nombre.getText());
                                    if(sob != null) {
                                        sob.contenido = cont.getText();
                                        Alert a = new Alert(Alert.AlertType.WARNING);
                                        a.setTitle("Sobreescirutra");
                                        a.setContentText("Contenido del archivo " + sob.nombre + " sobreescrito");
                                        a.showAndWait();
                                        Proyecto2_2.log.Push("Sobreescritura del Archivo: " + sob.nombre, Proyecto2_2.actual.usuario);
                                    }
                                }
                        }
                    }    
                }
                */
                Proyecto2_2.log.Push("Modificacion del archivo: \"" + s1 + "\" a: \"" + nombre.getText() + "\"", Proyecto2_2.actual.usuario);
                Fx2.tableview.getItems().clear();
                Fx2.CreateTableView();
                if(Fx3 != null) {
                    Fx3.CreateTiles();
                }
            }
        } 
        Proyecto2_2.selC = null;
        selA = null;
    }
    
    @FXML
    private void EliminarA(MouseEvent evt) {
        if(Proyecto2_2.selA != null) {
            NodoMatriz nm = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.selA.padre, Proyecto2_2.selA.hijo);
            nm.archivos.eliminar(Proyecto2_2.selA.nombre);
            Fx2.tableview.getItems().clear();
            Fx2.CreateTableView();
            if(Fx3 != null) {
                Fx3.CreateTiles();
            }
            Proyecto2_2.log.Push("Elimino el archivo: " + Proyecto2_2.selA.nombre, Proyecto2_2.actual.usuario);
        }
        Proyecto2_2.selC = null;
        Proyecto2_2.selA = null;
    }
    
    @FXML
    private void CargarA(MouseEvent evt) {
        
    }
    
    @FXML
    private void CompartirA(MouseEvent evt) {
           
    }
    
    @FXML
    private void DescargarA(MouseEvent evt) {
        if(Proyecto2_2.selA != null) {
            String nombre = "src/Descargas/" + Proyecto2_2.selA.nombre;
            PrintWriter escribir;
            try {
                escribir = new PrintWriter(new BufferedWriter(new FileWriter(nombre)));
                escribir.println(Proyecto2_2.selA.contenido);
                escribir.close();
            } catch (Exception e) {
                System.err.println("Error al crear archivo " + e.toString());
                Proyecto2_2.log.Push("No se pudo realizar la descarga del archivo: " + Proyecto2_2.selA.nombre, Proyecto2_2.actual.usuario);
            }
            Proyecto2_2.log.Push("Descarga correcta del archivo: " + Proyecto2_2.selA.nombre, Proyecto2_2.actual.usuario);
            File fOpen = new File(nombre);
            Desktop d = Desktop.getDesktop();
            try {
                d.open(fOpen);
            } catch(Exception e) {
                System.out.println("Error al abrir " + e.getMessage());
            }
        }
    }
}
