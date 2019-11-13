/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.Usuario;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Douglas
 */
public class TablaHash {
    int fin;
    int nElementos;
    double factor;
    Usuario[] tabla;
    
    public TablaHash() {
        this.fin = 7;
        this.nElementos = 0;
        this.factor = 0.0;
        this.tabla = new Usuario[7];
        for (int i = 0; i < this.fin; i++) {
            tabla[i] = null;
        }
    }
    
    public TablaHash(int t) {
        this.fin = t;
        this.nElementos = 0;
        this.factor = 0.0;
        this.tabla = new Usuario[t];
        for (int i = 0; i < this.fin; i++) {
            this.tabla[i] = null;
        }
    }
    
    private int hash(String n) {
        int k = 0;
        for (int i = 0; i < n.length(); i++) {
            k += (int) n.charAt(i);
        }
        
        int p = k % this.fin;
        int rep = 1;
        int aux = 1;
        while(this.tabla[p] != null) {
            if(rep > 5) {
                p++;
                rep = 1;
            }
            p = p + rep*rep; 
            while(p > this.fin - 1) {
                p -= this.fin;
            }
            rep++;
            aux++;
        }
        return p;
    }
    
    private int hashBuscar(String clave) {
        int k = 0;
        for (int i = 0; i < clave.length(); i++) {
            k += (int) clave.charAt(i);
        }
        
        int p = k % this.fin;
        int rep = 1;
        while(this.tabla[p] != null) {
            if(rep > 5) {
                p++;
                rep = 1;
            }
            if(this.tabla[p].usuario.equalsIgnoreCase(clave)) {
                break;
            } else {
                p = p + rep*rep;
                while(p > this.fin - 1) {
                    p -= this.fin;
                }
            }
            rep++;
        }
        if(this.tabla[p] == null){
            p = -1;
        }
        return p;
    }
    
    private boolean esprimo(int n) {
        boolean primo = true;
        int c = 2;
        while((primo) && (c != n)) {
            if(n % c == 0) {
                primo = false;
            }
            c++;
        }
        return primo;
    }
    
    private int obtenerPrimo(int tam) {
        int prox = 2;
        while(prox <= tam || !esprimo(prox)) {
            prox++;
        }
        return prox;
    }
    
    public Usuario buscar(String clave) {
        int posB = hashBuscar(clave);
        if(posB == -1) {
            return null;
        } else {
            Usuario nuevo = this.tabla[posB];
            return nuevo;
        }
    }
    
    public boolean insertar(String s, String p) {
        int posb = hashBuscar(s);
        if(posb == -1) {
            int pos = hash(s);
            Usuario nuevoU = new Usuario(s, p, pos);
            this.tabla[pos] = nuevoU;
            this.nElementos++;
            this.factor = ((double)this.nElementos / (double)this.fin);
            if(this.factor >= 0.75) {
                int nuevo = obtenerPrimo(this.fin);
                reHashing(nuevo);
            }
            return true;
        } else {
            return false;
        }
    }
    
    private void reHashing(int nuevoT) {
        TablaHash nuevo = new TablaHash(nuevoT);
        for(int i = 0; i < this.fin; i++) {
            if(this.tabla[i] != null) {
                nuevo.insertar(this.tabla[i].usuario,this.tabla[i].password);
            }
        }
        this.fin = nuevo.fin;
        this.nElementos = nuevo.nElementos;
        this.factor = nuevo.factor;
        this.tabla = nuevo.tabla;
    }
    
    public void graficar() {
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(new BufferedWriter(new FileWriter("src/Reportes/tablaHash.dot")));
            escribir.println("digraph tablaHash{ \n");
            escribir.println("\ttbl [\n");
            escribir.println("\t\tshape=plaintext");
            escribir.println("\t\tlabel=<");
            escribir.println("\t\t\t<table border='0' cellborder='1' cellspacing='0'>");
            escribir.println("\t\t\t\t<tr>");
            escribir.println("\t\t\t\t\t<td>No.</td>");
            escribir.println("\t\t\t\t\t<td>Usuario</td>");
            escribir.println("\t\t\t\t</tr>");
            
            for(int i = 0; i < this.fin; i++) {
                if(this.tabla[i] != null) {
                    escribir.println("\t\t\t\t<tr>");
                    escribir.println("\t\t\t\t\t<td>"+ i + "</td>");
                    escribir.println("\t\t\t\t\t<td cellpadding='2'>");
                    escribir.println("\t\t\t\t\t\t<table border='0' cellspacing='0'>");
                    escribir.println("\t\t\t\t\t\t\t<tr>");
                    escribir.println("\t\t\t\t\t\t\t\t<td>Usuario: "+this.tabla[i].usuario+"</td>");
                    escribir.println("\t\t\t\t\t\t\t</tr>");
                    escribir.println("\t\t\t\t\t\t\t<tr>");
                    escribir.println("\t\t\t\t\t\t\t\t<td>Password: "+this.tabla[i].hexdigest+"</td>");
                    escribir.println("\t\t\t\t\t\t\t</tr>");
                    escribir.println("\t\t\t\t\t\t\t<tr>");
                    escribir.println("\t\t\t\t\t\t\t\t<td>Timestamp: "+this.tabla[i].timestamp+"</td>");
                    escribir.println("\t\t\t\t\t\t\t</tr>");
                    escribir.println("\t\t\t\t\t\t</table>");
                    escribir.println("\t\t\t\t\t</td>");
                    escribir.println("\t\t\t\t</tr>");
                } else {
                    escribir.println("\t\t\t\t<tr>");
                    escribir.println("\t\t\t\t\t<td>"+ i + "</td>");
                    escribir.println("\t\t\t\t</tr>");
                }
            }
            
            escribir.println("\t\t\t</table>");
            escribir.println("\t\t>];");
            escribir.println("}");
            escribir.close();
            
            Runtime.getRuntime().exec("dot src/Reportes/tablaHash.dot -o src/Reportes/tablaHash.png -Tpng");
            
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}