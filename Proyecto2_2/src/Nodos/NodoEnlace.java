/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

/**
 *
 * @author Douglas
 */
public class NodoEnlace {
    public String nombre;
    public NodoEnlace sig;
    
    public NodoEnlace() {
        this.nombre = "";
        this.sig = null;
    }
    
    public NodoEnlace(String n, NodoEnlace s) {
        this.nombre = n;
        this.sig = s;               
    }
}
