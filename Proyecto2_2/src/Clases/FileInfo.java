/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author Douglas
 */
public class FileInfo {
    private ImageView image;
    private SimpleStringProperty name;
    private SimpleStringProperty date;
    private String tipo;

    public FileInfo(ImageView image, String name, String date, String t){
        super();
        this.image = image;
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.tipo = t;
    }

    /**
     * @return the image
     */
    public ImageView getImage() {
        return image;
    }
    
    /**
     * @param image the image to set
     */
    public void setImage(ImageView image) {
        this.image = image;
    }
    
    /**
     * @return the name
     */
    public String getName(){
        return name.get();
    }
    
    /**
     * @return the date
     */
    public String getDate() {
        return date.get();
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
