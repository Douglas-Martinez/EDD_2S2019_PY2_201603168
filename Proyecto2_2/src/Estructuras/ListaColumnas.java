/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoColumna;

/**
 *
 * @author Douglas
 */
public class ListaColumnas {
    public NodoColumna inicio;

    public ListaColumnas() {
        this.inicio = null;
    }
    
    public NodoColumna buscar(String n) {
        NodoColumna tmp = this.inicio;
        while(tmp != null) {
            if(tmp.nombre.equals(n)) {
                return tmp;
            }
            tmp = tmp.sig;
        }
        return null;
    }
    
    public void insertar(String s) {
        NodoColumna tmp = buscar(s);
        if(tmp == null) {
            NodoColumna nuevo = new NodoColumna(s);
            if(this.inicio == null) {
                this.inicio = nuevo;
            } else if(nuevo.nombre.compareTo(this.inicio.nombre) < 0) {
                nuevo.sig = this.inicio;
                this.inicio = nuevo;
            } else {
                NodoColumna actual = this.inicio;
                NodoColumna anterior = this.inicio;
                while((actual.sig != null) && (nuevo.nombre.compareTo(actual.nombre)) > 0) {
                    anterior = actual;
                    actual = actual.sig;
                }
                if(nuevo.nombre.compareTo(actual.nombre) > 0) {
                    anterior = actual;
                }
                nuevo.sig = anterior.sig;
                anterior.sig = nuevo;
            }
        }
    }
}
