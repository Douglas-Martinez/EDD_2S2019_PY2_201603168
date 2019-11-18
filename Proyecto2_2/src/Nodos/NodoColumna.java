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
public class NodoColumna {
    public String nombre;
    public NodoColumna sig;
    public NodoMatriz abajo;

    public NodoColumna() {
        this.nombre = "";
        this.sig = null;
        this.abajo = null;
    }

    public NodoColumna(String nombre) {
        this.nombre = nombre;
        this.sig = null;
        this.abajo = null;
    }
}
