/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoFila;

/**
 *
 * @author Douglas
 */
public class ListaFilas {
    public NodoFila inicio;

    public ListaFilas() {
        this.inicio = null;
    }
    
    public NodoFila buscar(String s) {
        NodoFila tmp = this.inicio;
        while(tmp != null) {
            if(tmp.nombre.equals(s)) {
                return tmp;
            }
            tmp = tmp.sig;
        }
        return null;
    }
    
    public void insertar(String s) {
        NodoFila nuevo = new NodoFila(s);
        if(this.inicio == null) {
            this.inicio = nuevo;
        } else if(nuevo.nombre.compareTo(this.inicio.nombre) < 0) {
            nuevo.sig = this.inicio;
            this.inicio = nuevo;
        } else {
            NodoFila actual = this.inicio;
            NodoFila anterior = this.inicio;
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
