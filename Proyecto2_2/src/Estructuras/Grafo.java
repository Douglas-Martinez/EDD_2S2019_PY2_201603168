/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoEnlace;
import Nodos.NodoGrafo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Douglas
 */
public class Grafo {
    
    public NodoGrafo inicio;
    
    public Grafo() {
        this.inicio = null;
    }
    
    public NodoGrafo buscar(String nombre) {
        NodoGrafo buscar = inicio;
        while(buscar != null) {
            if(buscar.nombre.equals(nombre)) {
                return buscar;
            }
            buscar = buscar.sig;
        }
        return null;
    }
    
    public boolean insertarVertice(String nombre) {
        NodoGrafo buscar = inicio;
        if(buscar == null) {
            NodoGrafo nuevo = new NodoGrafo(nombre, inicio);
            inicio = nuevo;
            return true;
        }        
        return false;
    }
    
    public boolean insertarRuta(String o, String d) {
        this.insertarVertice(o);
        this.insertarVertice(d);
        NodoGrafo buscar = buscar(o);
        System.out.println(buscar);
        if(buscar != null) {
            buscar.enlaces.insertar(d);
            return true;
        }
        return false;
    }
    
    public boolean eliminarRuta(String o, String d) {
        NodoGrafo buscar = buscar(o);
        if(buscar != null) {
            buscar.enlaces.elliminar(d);
            return true;
        }
        return false;
    }
    
    public void imprimirGrafo() {
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(new BufferedWriter(new FileWriter("grafo.dot")));
            escribir.println("digraph dibujo{");
            if (inicio == null) {
                escribir.println("\"Grafo Vacio\"");
            } else {
                NodoGrafo aux = inicio;
                while (aux != null) {
                    NodoEnlace tmp = aux.enlaces.inicio;
                    escribir.println("\""+aux.nombre+"\""); 
                    while (tmp != null) {
                        escribir.println("\""+aux.nombre+"\"->\""+tmp.nombre+"\"");  
                        tmp = tmp.sig; 
                    }
                    aux = aux.sig;
                }
            }
            escribir.println("}");
            escribir.close();
            Runtime.getRuntime().exec("dot grafo.dot -o grafo.png -Tpng -Gcharset=utf8");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
