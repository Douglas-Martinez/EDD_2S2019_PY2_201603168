/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class RegistroController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmar;
    @FXML private Button btnRegistrar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    @FXML
    private void Registrar(MouseEvent evt) {
        if(!txtUsuario.getText().equals("")) {
            if(txtPassword.getText().length() >= 8) {
                if(txtPassword.getText().equals(txtConfirmar.getText())) {
                    boolean res = Proyecto2_2.usuarios.insertar(txtUsuario.getText(), txtPassword.getText());
                    if(res == true) {
                        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                        dialog.setTitle("Registro Exitoso");
                        dialog.setHeaderText(null);
                        dialog.setContentText("Usuario registrado satisfactoriamente");
                        dialog.initStyle(StageStyle.UTILITY);
                        dialog.showAndWait();
                        Proyecto2_2.log.Push("Usuario Registrado", txtUsuario.getText());
                        //Close this
                        Stage stg = (Stage) btnRegistrar.getScene().getWindow();
                        stg.close();
                    } else {
                        Alert dialog = new Alert(Alert.AlertType.ERROR);
                        dialog.setTitle("Error");
                        dialog.setHeaderText(null);
                        dialog.setContentText("Ya existe un usuario con ese nombre");
                        dialog.initStyle(StageStyle.UTILITY);
                        dialog.showAndWait();
                        txtPassword.setText("");
                        txtConfirmar.setText("");
                    }
                } else {
                    Alert dialog = new Alert(Alert.AlertType.WARNING);
                    dialog.setTitle("Advertencia");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Las contraseñas no coinciden");
                    dialog.initStyle(StageStyle.UTILITY);
                    dialog.showAndWait();
                    txtPassword.setText("");
                    txtConfirmar.setText("");
                }
            } else {
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setTitle("Advertencia");
                dialog.setHeaderText(null);
                dialog.setContentText("La contraseña debe de ser de al menos 8 caracteres");
                dialog.initStyle(StageStyle.UTILITY);
                dialog.showAndWait();
                txtPassword.setText("");
                txtConfirmar.setText("");
            }
        } else {
            Alert dialog = new Alert(Alert.AlertType.WARNING);
            dialog.setTitle("Advertencia");
            dialog.setHeaderText(null);
            dialog.setContentText("El usuario no puede estar vacio");
            dialog.initStyle(StageStyle.UTILITY);
            dialog.showAndWait();
            txtUsuario.setText("");
            txtPassword.setText("");
            txtConfirmar.setText("");
        }
    }
}
