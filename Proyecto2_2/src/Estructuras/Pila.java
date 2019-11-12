/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoPila;

/**
 *
 * @author Douglas
 */
public class Pila {
    NodoPila tope;
    
    public Pila() {
        this.tope = null;
    }
    
    public void Push(String op, String u) {
        NodoPila nuevo = new NodoPila(op, u);
        if(this.tope == null) {
            this.tope = nuevo;
        } else {
            nuevo.setSig(tope);
            this.tope = nuevo;
        }
    }
    
    public NodoPila Pop() {
        NodoPila eliminado;
        if(this.tope == null) {
            eliminado = null;
        } else {
            eliminado = this.tope;
            this.tope = this.tope.getSig();
        }
        return eliminado;
    }
    
    public void Vaciar() {
        while(this.tope != null) {
            System.out.println("/--------------------/");
            System.out.println("Usuario: " + this.tope.getUsuario());
            System.out.println("Operacion: " + this.tope.getOperacion());
            System.out.println("Fecha: " + this.tope.getFecha());
            System.out.println("Hora: " + this.tope.getHora());
            System.out.println("/--------------------/");
            this.tope = this.tope.getSig();
        }
        this.tope = null;
    }
    
    public void Recorrer() {
        NodoPila aux = this.tope;
        while(aux != null) {
            System.out.println("/--------------------/");
            System.out.println("Usuario: " + this.tope.getUsuario());
            System.out.println("Operacion: " + this.tope.getOperacion());
            System.out.println("Fecha: " + this.tope.getFecha());
            System.out.println("Hora: " + this.tope.getHora());
            System.out.println("/--------------------/");
            aux = aux.getSig();
        }
    }
}
