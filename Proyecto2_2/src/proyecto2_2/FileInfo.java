/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author Douglas
 */
public class FileInfo {
    private ImageView image;
    private SimpleStringProperty name;
    private SimpleStringProperty size;
    private SimpleStringProperty date;

    public FileInfo(ImageView image, String name, String size, String date){
        super();
        this.image = image;
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleStringProperty(size);
        this.date = new SimpleStringProperty(date);
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
     * @return the size
     */
    public String getSize(){
        return size.get();
    }
    
    /**
     * @return the date
     */
    public String getDate() {
        return date.get();
    }
}
