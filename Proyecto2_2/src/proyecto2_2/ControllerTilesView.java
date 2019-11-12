/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Douglas
 */
public class ControllerTilesView implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TilePane tilePane;
    public static FileExplorerFx Fx3;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fx3 = new ClassTilesView();
        Fx3.tilePane = tilePane;
        Fx3.CreateTiles();
    }

}
