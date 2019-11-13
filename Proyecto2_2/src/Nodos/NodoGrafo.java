/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.ListaEnlace;

/**
 *
 * @author Douglas
 */
public class NodoGrafo {
    public String nombre;
    public NodoGrafo sig;
    public ListaEnlace enlaces;
    
    public NodoGrafo() {}
    
    public NodoGrafo(String n, NodoGrafo s) {
        this.nombre = n;
        this.sig = s;
        this.enlaces = new ListaEnlace();
    }
    
    
}
