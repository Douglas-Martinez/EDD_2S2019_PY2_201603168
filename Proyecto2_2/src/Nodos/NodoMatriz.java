/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.AVL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class NodoMatriz {
    public String padre;
    public String hijo;
    public String propietario;
    String fecha;
    public AVL archivos;
    //String nombre;
    public NodoMatriz arriba;
    public NodoMatriz abajo;
    public NodoMatriz izquierda;
    public NodoMatriz derecha;

    public NodoMatriz() {
        this.padre = "";
        this.hijo = "";
        this.fecha = TimeStamp();
        this.propietario = "";
        this.archivos = new AVL();
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
    }
    
    public NodoMatriz(String padre, String hijo, String p) {
        this.padre = padre;
        this.hijo = hijo;
        this.fecha = TimeStamp();
        this.propietario = p;
        this.archivos = new AVL();
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
    }
    
    private String TimeStamp() {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        
        String ts = form.format(date);
        return ts;
    }
}
