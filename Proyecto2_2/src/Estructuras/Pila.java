/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoPila;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

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
    
    public void graficar() {
        PrintWriter escribir;
        try {
            //escribir = new PrintWriter(new BufferedWriter(new FileWriter("src/Reportes/log.dot")));
            escribir = new PrintWriter(new BufferedWriter(new FileWriter("Reportes/log.dot")));
            escribir.println("digraph pila{ ");
            escribir.println("\tnode[shape=record];\n");
            escribir.println("\tgraph[pencolor=transparent];\n");
            escribir.println("\trankdir=TB;\n");
            
            NodoPila aux = this.tope;
            if(aux == null) {
                escribir.println("\t\"Pila Vacia\"\n");
            } else {
                while(aux != null) {
                    escribir.println("\t"+aux.hashCode()+"[label=\"Operacion: "+aux.getOperacion()+"\\nUsuario: "+aux.getUsuario()+"\\nTimeStamp: "+aux.getFecha()+"::"+aux.getHora()+"\"];\n");
                    aux = aux.getSig();
                }
                
                escribir.println("");
                
                aux = this.tope;
                escribir.print("\t"+aux.hashCode());
                aux = aux.getSig();
                while(aux != null) {
                    escribir.print("->" + aux.hashCode());
                    aux = aux.getSig();
                }
                escribir.println(";\n");
            }
            
            escribir.print("\tlabel = \"Log - Bitácora\"");
            escribir.println("}");
            escribir.close();
            //Runtime.getRuntime().exec("dot src/Reportes/log.dot -o src/Reportes/log.png -Tpng");
            Runtime.getRuntime().exec("dot Reportes/log.dot -o Reportes/log.png -Tpng");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
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
