/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class NodoAVL {
    public String nombre;
    public String extension;
    public String contenido;
    public int fe;
    public int h;
    public String timestamp;
    public String propietario;
    public NodoAVL left;
    public NodoAVL right;

    public NodoAVL() {
        this.nombre = "";
        this.extension = "";
        this.contenido = "";
        this.fe = 0;
        this.h = 1;
        this.timestamp = "";
        this.propietario = "";
        this.left = null;
        this.right = null;
    }
    
    public NodoAVL(String nombre, String extension, String contenido, String propietario) {
        this.nombre = nombre;
        this.extension = extension;
        this.contenido = contenido;
        this.fe = 0;
        this.h = 1;
        this.timestamp = TimeStamp();
        this.propietario = propietario;
        this.left = null;
        this.right = null;
    }
    
    private String TimeStamp() {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        
        String ts = form.format(date);
        return ts;
    }
}
