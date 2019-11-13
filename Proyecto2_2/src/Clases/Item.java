/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Douglas
 */
public class Item {
    public String nombre;
    public String date;
    public String tipo;
    
    public Item() {
        this.nombre = "";
        this.date = "";
        this.tipo = "";
    }
    
    public Item(String n, String d, String t) {
        this.nombre = n;
        this.date = d;
        this.tipo = t;
    }
}
