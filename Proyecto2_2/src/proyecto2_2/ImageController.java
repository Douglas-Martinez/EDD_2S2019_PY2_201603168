/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Douglas
 */
public class ImageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private static ImageView imgView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //imgView.setId("");
            Desktop d = Desktop.getDesktop();
            File f = new File("src/"+Proyecto2_2.img);
            d.open(f);
            imgView.setId(null);
            imgView.setImage(null);
            imgView.setImage(new Image(ClassLoader.getSystemResourceAsStream(Proyecto2_2.img)));
            //imgView.setImage(new Image(Proyecto2_2.img));
            Proyecto2_2.contIm++;
        } catch(Exception e) {
            System.out.println("Error al iniciar la imagen " + e.getMessage());
        }
    }
}
