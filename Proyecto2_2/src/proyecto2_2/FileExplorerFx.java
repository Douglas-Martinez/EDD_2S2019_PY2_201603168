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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    TableColumn<FileInfo, String> size;

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
    public String calculateSize(File f){
        String s;
        long sizeInByte=0;
        Path path;
        
        if(IsDrive(f)){
            return Long.toString(f.getTotalSpace()/(1024*1024*1024))+"GB";
        }

        path = Paths.get(f.toURI());
        try {
            sizeInByte = Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(sizeInByte<(1024)) { 
            s = Long.toString(sizeInByte)+"B";
            return s;
        } else if(sizeInByte>=(1024) && sizeInByte<(1024*1024)) {
            long sizeInKb = sizeInByte/1024;
            s = Long.toString(sizeInKb)+"KB"; 
            return s; 
        } else if(sizeInByte>=(1024*1024) && sizeInByte<(1024*1024*1024)) {
            long sizeInMb = sizeInByte/(1024*1024);
            s = Long.toString(sizeInMb)+"MB";
            return s;
        } else if(sizeInByte>=(1024*1024*1024)) {
            long sizeInGb = sizeInByte/(1024*1024*1024);
            s = Long.toString(sizeInGb)+"GB";
            return s; 
        }

        return null;
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