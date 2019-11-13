/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author Douglas
 */
public class ImageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private ImageView imgView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            imgView.setImage(new Image(new FileInputStream("./src/Reportes/tablaHash.png")));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
