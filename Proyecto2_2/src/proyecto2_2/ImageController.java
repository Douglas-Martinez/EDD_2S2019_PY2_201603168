/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    @FXML private ScrollPane imgViewPanel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //imgView.setId("");
//            Desktop d = Desktop.getDesktop();
//            File f = new File("./"+Proyecto2_2.img);
//            d.open(f);
            Runtime.getRuntime().exec("javaws -clearcache");
            Image ni = new Image("Reportes/log.png",true);
            ImageView m = new ImageView(ni);
            m.setCache(true);

            imgViewPanel.setContent(m);
            Proyecto2_2.contIm++;
        } catch(Exception e) {
            System.out.println("Error al iniciar la imagen " + e.getMessage());
            System.out.println("->" + e);
            System.out.println("-->" + e.toString());
            e.printStackTrace();
        }
    }
}