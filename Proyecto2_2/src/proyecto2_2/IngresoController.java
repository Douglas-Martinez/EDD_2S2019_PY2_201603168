/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import Clases.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Douglas
 */
public class IngresoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnIngresar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    @FXML
    private void Ingresar(MouseEvent evt) throws IOException {
        String user = txtUsuario.getText();
        String pass = txtPassword.getText();
        
        Usuario us = Proyecto2_2.usuarios.buscar(user);
        if(us != null) {
            if(us.usuario.equals("Admin")) {
                if(us.password.equals(pass)) {
                    Proyecto2_2.actual = us;
                    //Open new ADMIN Stage
                    FXMLLoader fxml = new FXMLLoader(getClass().getResource("Scene_Admin.fxml"));
                    Parent root = (Parent) fxml.load();
                    Stage stgo = new Stage(StageStyle.DECORATED);
                    stgo.setResizable(false);
                    stgo.setTitle("EDD DRIVE - Admin");
                    stgo.setScene(new Scene(root));
                    stgo.show();
                    Proyecto2_2.log.Push("Inicio de Sesion", user);
                    //Close this Stage
                    Stage stgc = (Stage) btnIngresar.getScene().getWindow();
                    stgc.close();
                } else {
                    Alert dialog = new Alert(AlertType.ERROR);
                    dialog.setTitle("Error");
                    dialog.setHeaderText(null);
                    dialog.setContentText("La contraseña no coincide");
                    dialog.initStyle(StageStyle.UTILITY);
                    dialog.showAndWait();
                    txtPassword.setText("");
                }
            } else {
                if(pass.length() >= 8) {
                    if(us.password.equals(pass)) {
                        Proyecto2_2.actual = us;
                        //Open new ADMIN Stage
                        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Scene.fxml"));
                        Parent root = (Parent) fxml.load();
                        Stage stgo = new Stage(StageStyle.DECORATED);
                        stgo.setResizable(false);
                        stgo.setTitle("EDD DRIVE");
                        stgo.setScene(new Scene(root));
                        stgo.show();
                        Proyecto2_2.log.Push("Inicio de Sesion", user);
                        //Close this Stage
                        Stage stgc = (Stage) btnIngresar.getScene().getWindow();
                        stgc.close();
                    } else {
                        Alert dialog = new Alert(AlertType.ERROR);
                        dialog.setTitle("Error");
                        dialog.setHeaderText(null);
                        dialog.setContentText("La contraseña no coincide");
                        dialog.initStyle(StageStyle.UTILITY);
                        dialog.showAndWait();
                        txtPassword.setText("");
                    }
                } else {
                    Alert dialog = new Alert(AlertType.WARNING);
                    dialog.setTitle("Advertencia");
                    dialog.setHeaderText(null);
                    dialog.setContentText("La contraseña debe de ser mayor a 8 caracteres");
                    dialog.initStyle(StageStyle.UTILITY);
                    dialog.showAndWait();
                    txtPassword.setText("");
                }
            }
        } else {
            Alert dialog = new Alert(AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText(null);
            dialog.setContentText("Este usuario: \"" + user + "\" no existe");
            dialog.initStyle(StageStyle.UTILITY);
            dialog.showAndWait();
            txtUsuario.setText("");
            txtPassword.setText("");
        }
    }
    
    @FXML
    private void Registrar(MouseEvent evt) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Registro.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stg = new Stage(StageStyle.DECORATED);
        stg.setResizable(false);
        stg.setTitle("Registro");
        stg.setScene(new Scene(root));
        stg.show();
    }
}
