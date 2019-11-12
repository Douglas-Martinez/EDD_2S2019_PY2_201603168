/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoFila;
import Nodos.NodoMatriz;
import Nodos.NodoColumna;

/**
 *
 * @author Douglas
 */
public class MatrizDispersa {
    public String propietario;
    public ListaFilas padres;
    public ListaColumnas hijos;
    
    public MatrizDispersa() {
        this.propietario = "";
        this.padres = new ListaFilas();
        this.hijos = new ListaColumnas();
    }
    
    public MatrizDispersa(String p) {
        this.propietario = p;
        this.padres = new ListaFilas();
        this.hijos = new ListaColumnas();
    }
    
    public NodoMatriz buscar(String p, String h) {
        NodoFila auxF = this.padres.inicio;
        while(auxF != null) {
            if(auxF.nombre.equals(p)) {
                NodoMatriz auxM = auxF.der;
                while(auxM != null) {
                    if(auxM.hijo.equals(h)) {
                        return auxM;
                    }
                    auxM = auxM.derecha;
                }
            }
            auxF = auxF.sig;
        }
        return null;
    }
    
    public void insertar(String p, String h) {
        if(buscar(p,h) == null) {
            NodoMatriz nuevo = new NodoMatriz(p, h, this.propietario);
            NodoFila auxF = this.padres.buscar(p);
            NodoColumna auxC = this.hijos.buscar(h);
            
            //Asignar en filas
            if(auxF == null) {
                auxF = new NodoFila(p);
                this.padres.insertar(p);
                auxF = this.padres.buscar(p);
                auxF.der = nuevo;
            } else {
                if(nuevo.hijo.compareTo(auxF.der.hijo) < 0) {
                    nuevo.derecha = auxF.der;
                    auxF.der.izquierda = nuevo;
                    auxF.der = nuevo;
                } else {
                    NodoMatriz auxM = auxF.der;
                    while(auxM.derecha != null) {
                        if(nuevo.hijo.compareTo(auxM.derecha.hijo) < 0) {
                            nuevo.derecha = auxM.derecha;
                            auxM.derecha.izquierda = nuevo;
                            nuevo.izquierda = auxM;
                            auxM.derecha = nuevo;
                            break;
                        }
                    }
                    auxM.derecha = nuevo;
                    nuevo.izquierda = auxM;
                }
            }
            
            //Asignar en columnas
            if(auxC == null) {
                auxC = new NodoColumna(h);
                this.hijos.insertar(h);
                auxC = this.hijos.buscar(h);
                auxC.abajo = nuevo;
            } else {
                if(nuevo.padre.compareTo(auxC.abajo.padre) < 0) {
                    nuevo.abajo = auxC.abajo;
                    auxC.abajo.arriba = nuevo;
                    auxC.abajo = nuevo;
                } else {
                    NodoMatriz auxM = auxC.abajo;
                    while(auxM.abajo != null) {
                        if(nuevo.padre.compareTo(auxM.abajo.padre) < 0) {
                            nuevo.abajo = auxM.abajo;
                            auxM.abajo.arriba = nuevo;
                            nuevo.arriba = auxM;
                            auxM.abajo = nuevo;
                            break;
                        }
                        auxM = auxM.abajo;
                    }
                    auxM.abajo = nuevo;
                    nuevo.arriba = auxM;
                }
            }
        } else {
            System.out.println("La carpeta con esa ruta ya existe");
        }
    }
}
