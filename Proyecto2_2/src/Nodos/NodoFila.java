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
public class NodoFila {
    public String nombre;
    public String padre;
    public String ruta;
    public NodoFila sig;
    public NodoMatriz der;

    public NodoFila() {
        this.padre = "";
        this.nombre = "";
        this.sig = null;
        this.der = null;
    }

    public NodoFila(String nombre) {
        this.nombre = nombre;
        this.padre = "";
        this.sig = null;
        this.der = null;
    }
    
    public NodoFila(String nombre, String padre) {
        this.nombre = nombre;
        this.padre = padre;
        this.sig = null;
        this.der = null;
    }
    
    public int Contar() {
        int cont = 0;
        NodoMatriz auxM = this.der;
        while(auxM != null) {
            if(!auxM.hijo.equals("/")) {
                cont++;
            }
            auxM = auxM.derecha;
        }
        return cont;
    }
    
    public void obtenerNoRaiz() {
        
    }
}
