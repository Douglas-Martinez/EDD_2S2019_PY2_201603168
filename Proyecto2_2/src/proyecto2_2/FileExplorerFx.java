/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_2;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;

import static javafx.embed.swing.SwingFXUtils.toFXImage;

/**
 *
 * @author Douglas
 */
public abstract class FileExplorerFx implements FileExplorer{
    static File CurrDirFile;
    static String CurrDirStr;
    static Label lbl;
    static String CurrDirName;
    static TilePane tilePane;
    SimpleDateFormat sdf;

    TableView<FileInfo> tableview;
    TableColumn<FileInfo, ImageView> image;
    TableColumn<FileInfo, String> date;
    TableColumn<FileInfo, String> name;

    FileExplorerFx(){}
    
    @Override
    public Image getIconImageFX(File f) {
        ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(f);
        java.awt.Image img = icon.getImage();
        BufferedImage bimg = (BufferedImage) img;
        Image imgfx = toFXImage(bimg,null);
        return imgfx;
    }
    
    @Override
    public boolean IsDrive(File f){
        File[] sysroots = File.listRoots();
        for(int i=0; i<sysroots.length;i++) {
            if(f.equals(sysroots[i])) {
                return true; 
            }
        }
        return false;
    }
    
    @Override
    public int FilesHiddensCount(File dir){
        int count = 0;
        File[] fl = dir.listFiles();
        for(int i=0; i<fl.length; i++){
            try{
                if(fl[i].isHidden() || fl[i].isFile()) {
                    count++;
                }
            } catch(Exception x){
                System.out.println("Exception at prototype1, fileexplorer CountDir: "+x.getMessage());
            }

        }
        return count;
    }
    
    @Override
    public void setLabelTxt() {
        lbl.setText(CurrDirStr);
    }
}