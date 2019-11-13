/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoEnlace;

/**
 *
 * @author Douglas
 */
public class ListaEnlace {
    
    public NodoEnlace inicio;
    
    public ListaEnlace() {
        this.inicio = null;
    }
    
    public boolean buscar(String nombre) {
        NodoEnlace buscar = inicio;
        while(buscar != null) {
            if(buscar.nombre.equalsIgnoreCase(nombre)) {
                return true;
            }
            buscar = buscar.sig;
        }
        return false;
    }
    
    //Inserta al inicio
    public boolean insertar(String nombre) {
        if(!buscar(nombre)) {
            NodoEnlace nuevo = new NodoEnlace(nombre, inicio);
            inicio = nuevo;
            return true;
        }
        return false;
    }
    
    public boolean elliminar(String eliminar) {
        if(inicio != null) {
            NodoEnlace aux = inicio;
            if(aux.nombre.equals(eliminar) && inicio.sig == null) {
                inicio = null;
                return true;
            }
            if(aux.nombre.equals(eliminar) && inicio.sig != null) {
                NodoEnlace tmp = inicio.sig;
                inicio = tmp;
                tmp = null;
                return true;
            }
            while(aux.sig != null) {
                if(aux.sig.nombre.equals(eliminar)) {
                    NodoEnlace tmp = aux.sig;
                    aux.sig = tmp.sig;
                    tmp = null;
                    return true;
                }
            }
        }
        return false;
    }
}
