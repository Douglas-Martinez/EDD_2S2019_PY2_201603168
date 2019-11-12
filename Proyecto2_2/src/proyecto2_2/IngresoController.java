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
import javafx.fxml.Initializable;
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
    @FXML private Button btnRegistrar;
    
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
                    //Open admin home
                    Stage stg = (Stage) btnIngresar.getScene().getWindow();
                    stg.close();
                } else {
                    Alert dialog = new Alert(AlertType.ERROR);
                    dialog.setTitle("Error");
                    dialog.setHeaderText(null);
                    dialog.setContentText("La contraseña no coincide");
                    dialog.initStyle(StageStyle.UTILITY);
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    dialog.showAndWait();
                    txtPassword.setText("");
                }
            } else {
                if(pass.length() >= 8) {
                    if(us.password.equals(pass)) {
                        Proyecto2_2.actual = us;
                        //Open user home
                        Stage stg = (Stage) btnIngresar.getScene().getWindow();
                        stg.close();
                    } else {
                        Alert dialog = new Alert(AlertType.ERROR);
                        dialog.setTitle("Error");
                        dialog.setHeaderText(null);
                        dialog.setContentText("La contraseña no coincide");
                        dialog.initStyle(StageStyle.UTILITY);
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        dialog.showAndWait();
                        txtPassword.setText("");
                    }
                } else {
                    Alert dialog = new Alert(AlertType.WARNING);
                    dialog.setTitle("Advertencia");
                    dialog.setHeaderText(null);
                    dialog.setContentText("La contraseña debe de ser mayor a 8 caracteres");
                    dialog.initStyle(StageStyle.UTILITY);
                    java.awt.Toolkit.getDefaultToolkit().beep();
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
            java.awt.Toolkit.getDefaultToolkit().beep();
            dialog.showAndWait();
            txtUsuario.setText("");
            txtPassword.setText("");
        }
    }
    
    @FXML
    private void Registrar(MouseEvent evt) {
        //Registro
    }
}
