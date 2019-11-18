/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoFila;
import Nodos.NodoMatriz;
import Nodos.NodoColumna;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

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
        insertar("/", "/");
    }
    
    public MatrizDispersa(String p) {
        this.propietario = p;
        this.padres = new ListaFilas();
        this.hijos = new ListaColumnas();
        insertar("/", "/");
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
    
    private void insFC(String p, String h) {
        this.padres.insertar(p);
        this.padres.insertar(h);
        this.hijos.insertar(p);
        this.hijos.insertar(h);
    }
    
    public void insertar(String p, String h) {
        if(buscar(p,h) == null) {
            insFC(p, h);
            NodoMatriz nuevo = new NodoMatriz(p, h, this.propietario);
            NodoFila auxF = this.padres.buscar(p);
            NodoColumna auxC = this.hijos.buscar(h);
            
            //Asignar en filas
            if(auxF.der == null) {
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
                        auxM = auxM.derecha;
                    }
                    auxM.derecha = nuevo;
                    nuevo.izquierda = auxM;
                }
            }
            
            //Asignar en columnas
            if(auxC.abajo == null) {
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
    
    public void graficar() {
        PrintWriter escribir;
        try {
            //escribir = new PrintWriter(new BufferedWriter(new FileWriter("src/Reportes/matriz.dot")));
            escribir = new PrintWriter(new BufferedWriter(new FileWriter("Reportes/matriz.dot")));
            escribir.println("digraph matriz {\n");
            escribir.println("\trankdir = TB;\n");
            escribir.println("\tnode[shape = rectangle];\n");
            escribir.println("\tgraph[nodesep = 0.5];\n");
            
            escribir.println("\t0001000[label=\"Matriz\", style=\"filled\", fontcolor=\"white\", color=\"black\"];\n");
            
            //Nodos en Columna
            NodoColumna auxC = this.hijos.inicio;
            while(auxC != null) {
                escribir.println("\t" + auxC.hashCode()+"[label=\""+auxC.nombre+"\", style=\"solid\"];");
                auxC = auxC.sig;
            }
            //Nodos en Fila
            escribir.println("");
            NodoFila auxF = this.padres.inicio;
            while(auxF != null) {
                escribir.println("\t" + auxF.hashCode()+"[label=\""+auxF.nombre+"\", style=\"solid\"];");
                auxF = auxF.sig;
            }
            //Nodos internos
            escribir.println("");
            auxF = this.padres.inicio;
            while(auxF != null) {
                NodoMatriz auxM = auxF.der;
                while(auxM != null) {
                    if(auxM.padre.equals("/") && auxM.hijo.equals("/")) {
                        escribir.println("\t" + auxM.hashCode()+"[label=\"/\", style=\"filled\"];");
                    } else if(auxM.padre.equals("/")) {
                        String[] m = auxM.hijo.split("/");
                        String n = m[m.length - 1];
                        escribir.println("\t" + auxM.hashCode()+"[label=\"/ "+n+"\", style=\"filled\"];");
                    } else {
                        String[] m1 = auxM.padre.split("/");
                        String n1 = m1[m1.length - 1];
                        String[] m2 = auxM.hijo.split("/");
                        String n2 = m2[m2.length - 1];
                        escribir.println("\t" + auxM.hashCode()+"[label=\""+n1+" / "+n2+"\", style=\"filled\"];");
                    }
                    auxM = auxM.derecha;
                }
                auxF = auxF.sig;
            }
            
            //Conexion nodos columna
            escribir.println("");
            auxC = this.hijos.inicio;
            if(auxC != null) {
                escribir.println("\t" + "0001000 -> " + auxC.hashCode() + "[dir=both];");
                while(auxC.sig != null) {
                    escribir.println("\t" + auxC.hashCode() + " -> " + auxC.sig.hashCode() + "[dir=both];");
                    auxC = auxC.sig;
                }
            }
            //Conexion nodos fila
            escribir.println("");
            auxF = this.padres.inicio;
            if(auxF != null) {
                escribir.println("\t" + "0001000 -> " + auxF.hashCode() + "[dir=both];");
                while(auxF.sig != null) {
                    escribir.println("\t" + auxF.hashCode() + " -> " + auxF.sig.hashCode() + "[dir=both];");
                    auxF = auxF.sig;
                }
            }
            
            //Conexion vertical
            escribir.println("");
            auxC = this.hijos.inicio;
            while(auxC != null) {
                NodoMatriz auxM = auxC.abajo;
                if(auxC.abajo != null) {
                    escribir.println("\t" + auxC.hashCode() + " -> " + auxC.abajo.hashCode() + "[dir=both];");
                    while(auxM.abajo != null) {
                        escribir.println("\t" + auxM.hashCode() + " -> " + auxM.abajo.hashCode() + "[dir=both];");
                        auxM = auxM.abajo;
                    }
                }
                auxC = auxC.sig;
            }
            
            
            //Conexion horizontal
            escribir.println("");
            auxF = this.padres.inicio;
            while(auxF != null) {
                NodoMatriz auxM = auxF.der;
                if(auxF.der != null) {
                    escribir.println("\t" + auxF.hashCode() + " -> " + auxF.der.hashCode() + "[constraint=false, dir=both];");
                    //escribir.println("\t" + auxF.hashCode() + " -> " + auxF.der.hashCode() + "[dir=both];");
                    while(auxM.derecha != null) {
                        escribir.println("\t" + auxM.hashCode() + " -> " + auxM.derecha.hashCode() + "[contraint=false, dir=both];");
                        //escribir.println("\t" + auxM.hashCode() + " -> " + auxM.derecha.hashCode() + "[dir=both];");
                        auxM = auxM.derecha;
                    }
                }
                auxF = auxF.sig;
            }
            
            //rank same horizontal
            auxC = this.hijos.inicio;
            if(auxC != null) {
                escribir.print("\t" + "{ rank=same; 0001000; ");
                while(auxC != null) {
                    escribir.print(auxC.hashCode() + "; ");
                    auxC = auxC.sig;
                }
            }
            escribir.println(" }");
            
            auxF = this.padres.inicio;
            if(auxF != null) {
                while(auxF != null) {
                    escribir.print("\t" + "{ rank=same; " + auxF.hashCode() + "; ");
                    if(auxF.der != null) {
                        NodoMatriz auxM = auxF.der;
                        while(auxM != null) {
                            escribir.print(auxM.hashCode() + "; ");
                            auxM = auxM.derecha;
                        }
                    }
                    escribir.println("}");
                    auxF = auxF.sig;
                }
            }
            escribir.print("\tlabel = \"Matriz de Archivos\"");
            escribir.println("}");
            escribir.close();
            //Runtime.getRuntime().exec("dot src/Reportes/matriz.dot -o src/Reportes/matriz.png -Tpng");
            Runtime.getRuntime().exec("dot Reportes/matriz.dot -o Reportes/matriz.png -Tpng");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void graficarGrafo() {
        PrintWriter escribir;
        try {
            //escribir = new PrintWriter(new BufferedWriter(new FileWriter("src/Reportes/grafo.dot")));
            escribir = new PrintWriter(new BufferedWriter(new FileWriter("Reportes/grafo.dot")));
            escribir.println("digraph grafo {");
            if(padres == null) {
                escribir.println("\t\"Grafo Vacio\"");
            } else {
                NodoFila auxF = this.padres.inicio;
                while(auxF != null) {
                    escribir.println("\t\""+auxF.nombre+"\"[label=\""+auxF.nombre+"\"];");
                    auxF = auxF.sig;
                }
                recorridoGrafo(padres,escribir);
            }
            escribir.println("\tlabel = \"Grafo de Carpetas\";");
            escribir.println("}");
            escribir.close();
            //Runtime.getRuntime().exec("dot src/Reportes/grafo.dot -o src/Reportes/grafo.png -Tpng -Gcharset=utf8");
            Runtime.getRuntime().exec("dot Reportes/grafo.dot -o Reportes/grafo.png -Tpng -Gcharset=utf8");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    
    private void recorridoGrafo(ListaFilas r, PrintWriter escribir) {
        NodoFila auxF = r.inicio;
        while(auxF != null) {
            NodoMatriz auxM = auxF.der;
            while(auxM != null) {
                if(!auxM.padre.equals("/") && !auxM.hijo.equals("/")) {
                    escribir.println("\t\""+auxF.nombre + "\"->\"" + auxM.hijo + "\";");
                } else if(auxM.padre.equals("/") && !auxM.hijo.equals("/")) {
                    escribir.println("\t\"/\"->\"" + auxM.hijo + "\";");
                }
                auxM = auxM.derecha;
            }
            escribir.println("");
            auxF = auxF.sig;
        }
    }
    
    public void eliminar(String p, String h) {
        NodoFila auxF = this.padres.buscar(p);
        if(auxF.der.hijo.equals(h)) {
            if(auxF.der.derecha != null) {
                auxF.der.derecha.izquierda = null;
            }
            auxF.der = auxF.der.derecha;
        } else {
            NodoMatriz auxM = auxF.der;
            while(auxM != null) {
                if(auxM.hijo.equals(h)) {
                    auxM.izquierda.derecha = auxM.derecha;
                    //Elimina NodoM
                    break;
                }
                auxM = auxM.derecha;
            }
        }
        
        NodoColumna auxC = this.hijos.buscar(h);
        eliminaHijos(h);
        //Elimina
        auxC.abajo = auxC.abajo.abajo;
        //Elimina Columna
        NodoColumna auxC2 = this.hijos.inicio;
        while(auxC2.sig != null) {
            if(auxC2.sig.nombre.equals(h)) {
                auxC2.sig = auxC2.sig.sig;
                break;
            }
            auxC2 = auxC2.sig;
        }
        //Elimina Fila
        NodoFila auxF2 = this.padres.inicio;
        while(auxF2.sig != null) {
            if(auxF2.sig.nombre.equals(h)) {
                auxF2.sig = auxF2.sig.sig;
                break;
            }
            auxF2 = auxF2.sig;
        }
    }
    
    public void eliminaHijos(String ph) {
        NodoFila auxF = this.padres.buscar(ph);
        if(auxF.der != null) {
            NodoMatriz auxM = auxF.der;
            while(auxM != null) {
                this.eliminar(auxM.padre, auxM.hijo);
                auxM = auxM.derecha;
            }
        }
    }
    
    public int contarCarpetas(String np) {
        int cont = 0;
        NodoFila aux = this.padres.buscar(np);
        if(aux != null) {
            NodoMatriz auxM = aux.der;
            while(auxM != null) {
                cont++;
            }
        }
        return cont;
    }

    public void modificar(String p, String h, String n) {
        NodoMatriz nm = buscar(p, h);
        
        AVL tmp = nm.archivos;
        tmp.cambiarHijo(n);
        this.insertar(p, n);
        NodoMatriz bus = this.buscar(p, n);
        bus.archivos = tmp;
        
        auxModificar(nm.hijo, n);
        eliminar(p, h);
    }
    
    private void auxModificar(String p, String p2) {
        NodoFila auxF = this.padres.buscar(p);
        NodoMatriz nm = auxF.der;
        while(nm != null) {
            String[] s = nm.hijo.split("/");
            String name = p2 + "/" + s[s.length - 1];
            
            AVL tmp = nm.archivos;
            tmp.cambiarHijo(p2);
            this.insertar(p2, name);
            NodoMatriz bus = this.buscar(p2, name);
            bus.padre = p2;
            bus.hijo = name;
            bus.archivos = tmp;
            
            auxModificar(nm.hijo, name);
            
            nm = nm.derecha;
        }
    }
}
