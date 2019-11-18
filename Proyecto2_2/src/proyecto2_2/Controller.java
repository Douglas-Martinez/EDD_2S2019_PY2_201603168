/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Clases.ArchivoIn;
import Clases.ErrorUs;
import Clases.Usuario;
import Nodos.NodoAVL;
import Nodos.NodoMatriz;
import java.awt.Desktop;
import java.io.BufferedReader;
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

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
    
    @FXML private Pane secPane;
    @FXML private TreeView<String> treeview;
    @FXML private Label label;
    
    @FXML private Button btnLogOut;
    @FXML private Button btnCargarA;
    
    private int count;
    static ClassTreeView Fx1;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        count = 0;
        Fx1 = new ClassTreeView();

        Proyecto2_2.padre = "/";
        Proyecto2_2.carpeta = "/";
        Fx1.lbl = label;
        Fx2.lbl = label;
        label.setText(Proyecto2_2.carpeta);
        try {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            secPane.getChildren().add(newLoadedPane);
        } catch(NullPointerException | IOException e) { 
            System.out.println("Error al cargar la scene2: "+e.getMessage());
        }
        Fx1.CreateTreeView(treeview);
        handle();
    }

    @FXML
    private void handleMouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 1){
            try {
                TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();
                if(item != null) {
                    String s1 = Fx1.FindAbsolutePath(item, item.getValue());
                    String s2;
                    if(item.getParent() == null) {
                        s2 = "/";
                        Proyecto2_2.padre = s2;
                    } else {
                        s2 = Fx1.FindAbsolutePath(item.getParent(), item.getParent().getValue());
                        Proyecto2_2.padre = s2;
                    }
                    Proyecto2_2.carpeta = s1;

                    System.out.println("Selected Tree Dir : " + item.getValue());
                    label.setText(Proyecto2_2.carpeta);
                    Fx2.tableview.getItems().clear();
                    Fx2.CreateTableView();
                    if(Fx3 != null) {
                        Fx3.CreateTiles();
                    }
                    Proyecto2_2.selC = null;
                    Proyecto2_2.selA = null;
                }
            } catch(Exception x) {
                System.out.println(x.getMessage());
            }
        }
    }
    
    private void handle() {
        try {
            TreeItem<String> item = treeview.getRoot();
            
            String s1 = Fx1.FindAbsolutePath(item, item.getValue());
            String s2;
            Proyecto2_2.carpeta = item.getValue();
            if(item.getParent() == null) {
                s2 = "/";
                Proyecto2_2.padre = "/";
            } else {
                s2 = Fx1.FindAbsolutePath(item.getParent(), item.getParent().getValue());
                Proyecto2_2.padre = item.getParent().getValue();
            }
            Proyecto2_2.carpeta = s1;
            
            label.setText(Proyecto2_2.carpeta);
            
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
        if(Proyecto2_2.actual.usuario.equals("Admin")) {
            int si = 0;
            ObservableList<ErrorUs> dataNo = FXCollections.observableArrayList();
            
            File workingD = new File(System.getProperty("user.dir"));
            Stage st = (Stage) btnCargarA.getScene().getWindow();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("csv files", "*.csv");
            
            FileChooser fc = new FileChooser();
            fc.setTitle("Carga Masiva Usuarios");
            fc.setInitialDirectory(workingD);
            fc.getExtensionFilters().add(filter);
            File f = fc.showOpenDialog(st);
            
            if(f != null) {
                BufferedReader bf;
                try {
                    bf = new BufferedReader(new FileReader(f));
                    String line = bf.readLine();
                    line = bf.readLine();
                    while(line != null) {
                        String[] u1 = line.split(",");
                        if(u1.length == 2) {
                            String s1 = u1[0];
                            String s2 = u1[1];
                            if(s2.length() >= 8) {
                                Usuario temp = Proyecto2_2.usuarios.buscar(s1);
                                if(temp == null) {
                                    Proyecto2_2.usuarios.insertar(s1, s2);
                                    si++;
                                } else {
                                    dataNo.add(new ErrorUs(s1,"Usuario ya existe"));
                                }
                            } else {
                                dataNo.add(new ErrorUs(s1,"Contraseña \"" + s2 + "\" es menor a 8 caracteres"));
                            }
                        }
                        line = bf.readLine();
                    }
                    bf.close();
                    
                    Dialog n = new Dialog();
                    n.setTitle("Carga Usuarios");
                    n.setHeaderText("No. usuarios ingresados: " + si);
                    
                    if(!dataNo.isEmpty()) {
                        TableView table = new TableView();
                        table.setEditable(true);
                        
                        TableColumn<String, ErrorUs> c1 = new TableColumn<>("Usuario");
                        c1.setPrefWidth(100);
                        c1.setCellValueFactory(new PropertyValueFactory<>("us"));
                        
                        TableColumn<String, ErrorUs> c2 = new TableColumn<>("Error");
                        c2.setCellValueFactory(new PropertyValueFactory<>("err"));
                        c2.setPrefWidth(300);
                        
                        table.getColumns().clear();
                        table.getColumns().addAll(c1,c2);
                        table.getItems().addAll(dataNo);
                        
                        GridPane g = new GridPane();
                        g.add(new Label("ERRORES:"), 1, 1);
                        g.add(table, 1, 2);
                        n.getDialogPane().setContent(g);
                    }
                    n.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    n.showAndWait();
                    Proyecto2_2.log.Push("Carga masiva de usuarios", Proyecto2_2.actual.usuario);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Usuario Incorrecto");
            a.setHeaderText("Solo Admin puede realizar la operacion");
            a.showAndWait();
        }
    }
    
    @FXML
    private void Reportes(MouseEvent evt) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("Reportes.fxml"));
            Parent root = (Parent) fxml.load();
            Stage stgo = new Stage(StageStyle.DECORATED);
            stgo.setResizable(false);
            stgo.setTitle("Reportes");
            stgo.setScene(new Scene(root));
            stgo.showAndWait();
        } catch(Exception e) {
            System.out.println("Error al ccargar ventana de reportes");
        }
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
                } else {
                    NodoMatriz bus;
                    if(Proyecto2_2.carpeta.equals("/")) {
                        bus = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+r.get());
                    } else {
                        bus = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+"/"+r.get());
                    }
                    if(bus == null) {
                        if(Proyecto2_2.carpeta.equals("/")) {
                            Proyecto2_2.actual.matrix.insertar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+r.get());
                        } else {
                            Proyecto2_2.actual.matrix.insertar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+"/"+r.get());
                        }
                        Proyecto2_2.log.Push("Crear carpeta: " + r.get(), Proyecto2_2.actual.usuario);
                    } else {
                        String mas = "_";
                        while(bus != null) {
                            if(Proyecto2_2.carpeta.equals("/")) {
                                bus = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+r.get()+mas);
                            } else {
                                bus = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+"/"+r.get()+mas);
                            }
                            if(bus != null) {
                                mas += "_";
                            }
                        }
                        if(Proyecto2_2.carpeta.equals("/")) {
                            Proyecto2_2.actual.matrix.insertar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+r.get()+mas);
                        } else {
                            Proyecto2_2.actual.matrix.insertar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+"/"+r.get()+mas);
                        }
                        Proyecto2_2.log.Push("Crear carpeta: " + r.get()+mas, Proyecto2_2.actual.usuario);
                    }
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
        if(Proyecto2_2.selC != null) {
            TextInputDialog d = new TextInputDialog();
            d.setTitle("Modificar Carpeta");
            String[] n = Proyecto2_2.selC.hijo.split("/");
            d.setHeaderText("Se modificara la carpeta \"" + n[n.length - 1] + "\" ubicada en el directorio actual");
            d.setContentText("Ingresa el nuevo nombre de la carpeta seleccionada");
            d.setResizable(false);
            Optional<String> res = d.showAndWait();
            
            if(res.isPresent()) {
                if(!res.get().equals("")) {
                    String nuevo;
                    if(!Proyecto2_2.carpeta.equals("/")) {
                        nuevo = Proyecto2_2.carpeta + "/" + res.get();
                    } else {
                        nuevo = Proyecto2_2.carpeta + res.get();
                    }
                    NodoMatriz bus = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+"/"+nuevo);
                    if(bus == null) {
                        Proyecto2_2.actual.matrix.modificar(Proyecto2_2.carpeta, Proyecto2_2.selC.hijo, nuevo);
                    } else {
                        String mas = "_";
                        while(bus != null) {
                            bus = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.carpeta, Proyecto2_2.carpeta+"/"+nuevo+mas);
                            if(bus != null) {
                                mas += "_";
                            }
                        }
                        Proyecto2_2.actual.matrix.modificar(Proyecto2_2.carpeta, Proyecto2_2.selC.hijo, nuevo+mas);
                        Proyecto2_2.log.Push("Carpeta modificada a " + res.get()+mas, Proyecto2_2.actual.usuario);
                    }
                    Fx1.CreateTreeView(treeview);
                    Fx2.tableview.getItems().clear();
                    Fx2.CreateTableView();
                    if(Fx3 != null) {
                        Fx3.CreateTiles();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Nombre vacio");
                    a.setContentText("El nombre no puede estar vacio, prueba de nuevo con otro nombres");
                    a.showAndWait();
                    a.setContentText("El nombre de la carpeta no puede estar vacio");
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Carpeta Ivalida");
            a.setHeaderText("No se ha seleccionado ninguna carpeta");
            a.setContentText("Selecciona una carpeta y prueba de nuevo");
            a.showAndWait();
        }
        Proyecto2_2.selC = null;
        Proyecto2_2.selA = null;
    }
    
    @FXML
    private void EliminarC(MouseEvent evt) {
        if(Proyecto2_2.selC != null) {
            String n = Proyecto2_2.selC.hijo;
            NodoMatriz el = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.carpeta, Proyecto2_2.selC.hijo);
            if(el != null) {
                Proyecto2_2.actual.matrix.eliminar(Proyecto2_2.carpeta, Proyecto2_2.selC.hijo);
                Proyecto2_2.log.Push("Borrado de la carpeta " + n + " y todos su contenido (subcarpetas y archivos)", Proyecto2_2.actual.usuario);
                Fx1.CreateTreeView(treeview);
                Fx2.tableview.getItems().clear();
                Fx2.CreateTableView();
                if(Fx3 != null) {
                    Fx3.CreateTiles();
                }
            } else {
                System.out.println("Ocurrio un problema al eliminar la carpeta. NO se encontro " + Proyecto2_2.selC.hijo);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Carpeta Ivalida");
            a.setHeaderText("No se ha seleccionado ninguna carpeta");
            a.setContentText("Selecciona una carpeta y prueba de nuevo");
            a.showAndWait();
        }
        Proyecto2_2.selC = null;
        Proyecto2_2.selA = null;
    }
    
    @FXML
    private void CrearA(MouseEvent evt) {
        if(Proyecto2_2.carpeta != null) {
            Dialog<String[]> dilog = new Dialog<>();
            dilog.setTitle("Crear Archivo");
            dilog.setResizable(true);
            
            Label l1 = new Label("Nombre: ");
            Label l2 = new Label("Contenido");
            TextField t1 = new TextField();
            TextArea ta1 = new TextArea();
            
            GridPane g = new GridPane();
            g.add(l1, 1, 1);
            g.add(t1, 2, 1);
            g.add(l2, 1, 2);
            g.add(ta1, 2, 2);
            dilog.getDialogPane().setContent(g);

            ButtonType buttonType = new ButtonType("Crear", ButtonData.OK_DONE);
            dilog.getDialogPane().getButtonTypes().addAll(buttonType,ButtonType.CANCEL);
            
            dilog.setResultConverter(new Callback<ButtonType, String[]>() {
                @Override
                public String[] call(ButtonType b) {
                    if(b == buttonType) {
                        String[] r2 = new String[2];
                        r2[0] = t1.getText();
                        r2[1] = ta1.getText();
                        return r2;
                    }
                    return null;
                }
            });
            
            Optional<String[]> res = dilog.showAndWait();
            if(res.isPresent()) {
                if(res.get()[0].equals("")) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Nombre vacio");
                    a.setContentText("El nombre del archivo no puede estar vacio");
                    a.showAndWait();
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
                        boolean acc = nm.archivos.insertar(res.get()[0], res.get()[1], Proyecto2_2.actual.usuario);
                        if(acc == true) {
                            Proyecto2_2.log.Push("Archivo creado: " + res.get()[0], Proyecto2_2.actual.usuario);
                        } else {
                            NodoAVL sob = nm.archivos.buscar(res.get()[0]);
                            if(sob != null) {
                                sob.contenido = res.get()[1];
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setTitle("Sobreescirutra");
                                a.setContentText("Contenido del archivo " + sob.nombre + " sobreescrito");
                                a.showAndWait();
                                Proyecto2_2.log.Push("Sobreescritura del Archivo: " + sob.nombre, Proyecto2_2.actual.usuario);
                                Proyecto2_2.log.Push("Archivo creado: " + res.get()[0], Proyecto2_2.actual.usuario);
                            }
                        }
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Operacion Exitosa");
                        a.setHeaderText("Archivo creado exitosamente");
                        a.showAndWait();
                        Fx2.tableview.getItems().clear();
                        Fx2.CreateTableView();
                        if(Fx3 != null) {
                            Fx3.CreateTiles();
                        }
                    }
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Ruta Invalida");
            a.setHeaderText("Ubicacion Erronea");
            a.showAndWait();
        }
        Proyecto2_2.selC = null;
        selA = null;
    }
    
    @FXML
    private void ModificarA(MouseEvent evt) {
        if(Proyecto2_2.selA != null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Modificar Archivo");
            al.setHeaderText("Importante");
            al.setContentText("Si el borra completamente el nombre solo se modificara el contenido del archivo seleccionado.\n\n(No cambiara de nombre)");
            al.showAndWait();
            
            Dialog<String[]> dilog = new Dialog<>();
            dilog.setTitle("Modificar Archivo");
            dilog.setHeaderText("Archivo: " + selA.nombre);
            dilog.setResizable(false);
            
            Label l1 = new Label("Nombre: ");
            Label l2 = new Label("Contenido");
            TextField t1 = new TextField(selA.nombre);
            TextArea ta1 = new TextArea(selA.contenido);
            
            GridPane g = new GridPane();
            g.add(l1, 1, 1);
            g.add(t1, 2, 1);
            g.add(l2, 1, 2);
            g.add(ta1, 2, 2);
            dilog.getDialogPane().setContent(g);

            ButtonType buttonType = new ButtonType("Modificar", ButtonData.OK_DONE);
            dilog.getDialogPane().getButtonTypes().addAll(buttonType,ButtonType.CANCEL);
            
            dilog.setResultConverter(new Callback<ButtonType, String[]>() {
                @Override
                public String[] call(ButtonType b) {
                    if(b == buttonType) {
                        String[] r2 = new String[2];
                        r2[0] = t1.getText();
                        r2[1] = ta1.getText();
                        return r2;
                    }
                    return null;
                }
            });
            
            Optional<String[]> res = dilog.showAndWait();
            
            if(res.isPresent()) {
                NodoMatriz nm = Proyecto2_2.actual.matrix.buscar(selA.padre, selA.hijo);
                String s1 = selA.nombre;
                String s2 = selA.contenido;
                
                nm.archivos.eliminar(selA.nombre);
                if(!(res.get()[0].equals(""))) {
                    boolean acc = nm.archivos.insertar(res.get()[0], res.get()[1], Proyecto2_2.actual.usuario);
                    if(acc == true) {
                        Proyecto2_2.log.Push("Archivo creado por modificacion: " + res.get()[0], Proyecto2_2.actual.usuario);
                    } else {
                        NodoAVL sob = nm.archivos.buscar(res.get()[0]);
                        if(sob != null) {
                            if(res.get()[1].equals("")) {
                                sob.contenido = s2;
                            } else {
                                sob.contenido = res.get()[1];
                            }
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setTitle("Sobreescirutra");
                            a.setContentText("Contenido del archivo " + sob.nombre + " sobreescrito");
                            a.showAndWait();
                            Proyecto2_2.log.Push("Sobreescritura del archivo por modificacion: " + sob.nombre, Proyecto2_2.actual.usuario);
                        }
                    }
                } else {
                    boolean acc = nm.archivos.insertar(s1, res.get()[1], Proyecto2_2.actual.usuario);
                }
                Proyecto2_2.log.Push("Modificacion del archivo: \"" + s1 + "\" a: \"" + res.get()[0] + "\"", Proyecto2_2.actual.usuario);
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Operacion Exitosa");
                a.setHeaderText("Archivo Modificado con exito");
                a.showAndWait();
                Fx2.tableview.getItems().clear();
                Fx2.CreateTableView();
                if(Fx3 != null) {
                    Fx3.CreateTiles();
                }
                
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Archivo Ivalido");
            a.setHeaderText("No se ha seleccionado ningun archivo");
            a.setContentText("Selecciona un archivo y pruba de nuevo");
            a.showAndWait();
        }
        Proyecto2_2.selC = null;
        Proyecto2_2.selA = null;
    }
    
    @FXML
    private void EliminarA(MouseEvent evt) {
        if(Proyecto2_2.selA != null) {
            String n = selA.nombre;
            NodoMatriz nm = Proyecto2_2.actual.matrix.buscar(Proyecto2_2.selA.padre, Proyecto2_2.selA.hijo);
            boolean si = nm.archivos.eliminar(Proyecto2_2.selA.nombre);
            if(si) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Operacion Exitosa");
                a.setHeaderText("Archivo eliminado con exito");
                a.showAndWait();
                Proyecto2_2.log.Push("Elimino el archivo: " + n, Proyecto2_2.actual.usuario);

                Fx2.tableview.getItems().clear();
                Fx2.CreateTableView();
                if(Fx3 != null) {
                    Fx3.CreateTiles();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Archivo no se pudo eliminar");
                a.showAndWait();
                Fx2.tableview.getItems().clear();
                Fx2.CreateTableView();
                if(Fx3 != null) {
                    Fx3.CreateTiles();
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Archivo Ivalido");
            a.setHeaderText("No se ha seleccionado ningun archivo");
            a.setContentText("Selecciona un archivo y prueba de nuevo");
            a.showAndWait();
        }
        Proyecto2_2.selC = null;
        Proyecto2_2.selA = null;
    }
    
    @FXML
    private void CargarA(MouseEvent evt) {
        if(Proyecto2_2.carpeta != null) {
            ObservableList<ArchivoIn> dataSi = FXCollections.observableArrayList();
            
            File workingD = new File(System.getProperty("user.dir"));
            Stage st = (Stage) btnCargarA.getScene().getWindow();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("csv files", "*.csv");
            
            FileChooser fc = new FileChooser();
            fc.setTitle("Carga Masiva Archivos");
            fc.setInitialDirectory(workingD);
            fc.getExtensionFilters().add(filter);
            File f = fc.showOpenDialog(st);
            if(f != null) {
                BufferedReader bf;
                try {
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
                    
                    bf = new BufferedReader(new FileReader(f));
                    String line = bf.readLine();
                    line = bf.readLine();
                    while(line != null) {
                        String[] u1 = line.split(",");
                        if(u1.length == 2) {
                            String s1 = u1[0];
                            String s2 = u1[1].replaceAll("\"", "");
                            
                            if(nm != null) {
                                boolean acc = nm.archivos.insertar(s1, s2, Proyecto2_2.actual.usuario);
                                if(acc == true) {
                                    Proyecto2_2.log.Push("Archivo creado mediante carga masiva: " + s1, Proyecto2_2.actual.usuario);
                                    dataSi.add(new ArchivoIn(s1,s2));
                                } else {
                                    NodoAVL sob = nm.archivos.buscar(s1);
                                    if(sob != null) {
                                        sob.contenido = s2;
                                        Proyecto2_2.log.Push("Sobreescritura del Archivo mediante carga masiva: " + sob.nombre, Proyecto2_2.actual.usuario);
                                        dataSi.add(new ArchivoIn(s1,"Sobreescrito => "+s2));
                                    }
                                }
                            }
                        }
                        line = bf.readLine();
                    }
                    bf.close();
                    
                    Dialog d = new Dialog();
                    d.setTitle("Carga de Archivos");
                    d.setHeaderText("Archivos Ingresados");
                    
                    if(!dataSi.isEmpty()) {
                        TableView table = new TableView();
                        table.setEditable(true);
                        
                        TableColumn<String, ArchivoIn> c1 = new TableColumn<>("Nombre");
                        c1.setPrefWidth(150);
                        c1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                        
                        TableColumn<String, ArchivoIn> c2 = new TableColumn<>("Contenido");
                        c2.setPrefWidth(300);
                        c2.setCellValueFactory(new PropertyValueFactory<>("cont"));
                        
                        table.getColumns().clear();
                        table.getColumns().addAll(c1,c2);
                        table.getItems().addAll(dataSi);
                        
                        GridPane g = new GridPane();
                        g.add(table, 0, 0);
                        d.getDialogPane().setContent(g);
                    }
                    d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    d.showAndWait();
                    
                    Proyecto2_2.log.Push("Carga masiva de archivos realizada en la carpeta " + Proyecto2_2.carpeta, Proyecto2_2.actual.usuario);
                    Fx2.tableview.getItems().clear();
                    Fx2.CreateTableView();
                    if(Fx3 != null) {
                        Fx3.CreateTiles();
                    }
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Ruta Invalida");
            a.setHeaderText("Ubicacion Erronea");
            a.showAndWait();
        }
    }
    
    @FXML
    private void CompartirA(MouseEvent evt) {
        if(Proyecto2_2.selA != null) {
            TextInputDialog d = new TextInputDialog();
            d.setTitle("Compartir a");
            d.setHeaderText("Se compartira el archivo \"" + selA.nombre + "\"" );
            d.setContentText("Ingresa el nombre del usuario a compartir");
            d.setResizable(false);
            
            Optional<String> result = d.showAndWait();
            if(result.isPresent()) {
                String n = result.get();
                if(!n.equals("")) {
                    Usuario temp = Proyecto2_2.usuarios.buscar(n);
                    if(temp != null) {
                        boolean acc = temp.matrix.buscar("/", "/").archivos.insertar(selA.nombre, selA.contenido, temp.usuario);
                        if(acc == true) {
                             Alert a = new Alert(Alert.AlertType.INFORMATION);
                             a.setTitle("Operacion Exitosa");
                             a.setHeaderText("Archivo compartido con exito");
                             a.showAndWait();
                             Proyecto2_2.log.Push("Archivo " + selA.nombre + " compartido a la carpeta / del usuario: " + temp.usuario, Proyecto2_2.actual.usuario);
                        } else {
                            NodoAVL sob = temp.matrix.buscar("/", "/").archivos.buscar(selA.nombre);
                            if(sob != null) {
                                sob.contenido = selA.contenido;
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setTitle("Sobre escritura");
                                a.setHeaderText("Archivo Ya Existe");
                                a.setContentText("Contenido del archivo " + sob.nombre + " sobre escrito en la carpeta / del usuario: " + temp.usuario);
                                a.showAndWait();
                                Proyecto2_2.log.Push("Contenido del arcvhivo " + selA.nombre + " sobre escrito en la carpeta / del usuario: " + temp.usuario, Proyecto2_2.actual.usuario);
                                Proyecto2_2.log.Push("Archivo " + selA.nombre + " compartido a la carpeta / del usuario: " + temp.usuario, Proyecto2_2.actual.usuario);
                            } else {
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setTitle("Error al compartir");
                                a.setContentText("No se pudo compartir el archivo, lo sentimos");
                                a.showAndWait();
                            }
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setTitle("Usuario Invalido");
                        a.setContentText("El usuario solicitado no existe");
                        a.showAndWait();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setTitle("Usuario Invalido");
                    a.setContentText("El campo usuario no puede estar vacio");
                    a.showAndWait();
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Archivo Ivalido");
            a.setHeaderText("No se ha seleccionado ningun archivo");
            a.setContentText("Selecciona un archivo y pruba de nuevo");
            a.showAndWait();
        }
        Proyecto2_2.selC = null;
        selA = null;
    }
    
    @FXML
    private void DescargarA(MouseEvent evt) {
        if(Proyecto2_2.selA != null) {
            String nombre = "./Descargas/" + Proyecto2_2.selA.nombre;
            //String nombre = Proyecto2_2.selA.nombre;
            PrintWriter escribir;
            try {
                escribir = new PrintWriter(new BufferedWriter(new FileWriter(nombre)));
                escribir.println(Proyecto2_2.selA.contenido);
                escribir.close();
                
                Proyecto2_2.log.Push("Descarga correcta del archivo: " + Proyecto2_2.selA.nombre, Proyecto2_2.actual.usuario);
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Operacion Exitosa");
                a.setHeaderText("Archivo descargado con exito");
                a.showAndWait();
                
                File fOpen = new File(nombre);
                Desktop d = Desktop.getDesktop();
                try {
                    d.open(fOpen);
                } catch(Exception e) {
                    System.out.println("Error al abrir " + e.getMessage());
                }
            } catch (Exception e) {
                System.err.println("Error al crear archivo " + e.toString());
                Proyecto2_2.log.Push("No se pudo realizar la descarga del archivo: " + Proyecto2_2.selA.nombre, Proyecto2_2.actual.usuario);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Archivo Ivalido");
            a.setHeaderText("No se ha seleccionado ningun archivo");
            a.setContentText("Selecciona un archivo y pruba de nuevo");
            a.showAndWait();
        }
    }
}
