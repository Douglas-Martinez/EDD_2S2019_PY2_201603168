/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoAVL;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author Douglas
 */
public class AVL {
    public NodoAVL root;
    public int tam;
    public LinkedList<NodoAVL> lista;
    public String padre;
    public String hijo;
    
   public AVL(String p, String h) {
       this.root = null;
       this.tam = 0;
       this.lista = new LinkedList<>();
       this.padre = p;
       this.hijo = h;
   }
   
   public int getHeight(NodoAVL n) {
       if(n == null) {
           return 0;
       } else {
           return n.h;
       }
   }
   
   public int max(int a, int b) {
       if(a > b) {
           return a;
       } else {
           return b;
       }
   }
   
   public int getBalance(NodoAVL N) {
       if(N == null) {
           return 0;
       } else {
           N.fe = getHeight(N.right) - getHeight(N.left);
           return N.fe;
       }
   }
   
   public NodoAVL leftRotate(NodoAVL z) {
       NodoAVL y = z.right;
       NodoAVL T2 = y.left;
       
       y.left = z;
       z.right = T2;
       
       z.h = max(getHeight(z.left),getHeight(z.right)) + 1;
       y.h = max(getHeight(y.left),getHeight(y.right)) + 1;
       getBalance(z);
       getBalance(y);
       
       return y;
   }
   
   public NodoAVL rightRotate(NodoAVL z) {
       NodoAVL y = z.left;
       NodoAVL T3 = y.right;
       
       y.right = z;
       z.left = T3;
       
       z.h = max(getHeight(z.left),getHeight(z.right)) + 1;
       y.h = max(getHeight(y.left),getHeight(y.right)) + 1;
       getBalance(z);
       getBalance(y);
       
       return y;
   }
   
   public boolean insertar(String n, String c, String p) {
       int t1 = this.tam;
       this.root = insertar(this.root,n,c,p);
       linealizar();
       int t2 = this.tam;
       if(t1 != t2) {
           return true;
       } else {
           return false;
       }
   }
   
   private NodoAVL insertar(NodoAVL raiz, String n, String c, String p) {
       if(raiz ==  null) {
           this.tam++;
           return (new NodoAVL(n,c,p,this.padre,this.hijo));
       }
       if(n.compareTo(raiz.nombre) < 0) {
           raiz.left = insertar(raiz.left,n,c,p);
       } else if(n.compareTo(raiz.nombre) > 0) {
           raiz.right = insertar(raiz.right,n,c,p);
       } else {
           System.out.println("Nodo Ya Existe");
           return raiz;
       }
       
       raiz.h = 1 + max(getHeight(raiz.left),getHeight(raiz.right));
       int balance = getBalance(raiz);
       
       if(balance < -1 && n.compareTo(raiz.left.nombre) < 0) {
           return rightRotate(raiz);
       }
       if(balance > 1 && n.compareTo(raiz.right.nombre) > 0) {
           return leftRotate(raiz);
       }
       if(balance < -1 && n.compareTo(raiz.left.nombre) > 0) {
           raiz.left = leftRotate(raiz.left);
           return rightRotate(raiz);
       }
       if(balance > 1 && n.compareTo(raiz.right.nombre) < 0) {
           raiz.right = rightRotate(raiz.right);
           return leftRotate(raiz);
       }
       
       return raiz;
   }
   
   public NodoAVL minNodo(NodoAVL n) {
       NodoAVL actual = n;
       
       while(actual.left != null) {
           actual = actual.left;
       }
       
       return actual;
   }
   
   public void eliminar(String n) {
       this.root = eliminar(this.root, n);
       this.tam--;
       linealizar();
   }
   
   public NodoAVL buscar(String n) {
       return buscar(this.root, n);
   }
   
   private NodoAVL buscar(NodoAVL r, String n) {
       if(r == null) {
           return r;
       } else {
           if(r.nombre.equals(n)) {
               return r;
           } else {
               if(n.compareTo(r.nombre) < 0) {
                   r = buscar(r.left,n);
               } else {
                   r = buscar(r.right,n);
               }
           }
       }
       return r;
   }
   
   private NodoAVL eliminar(NodoAVL root, String n) {
       if(root == null) {
           return root;
       }
       
       if(n.compareTo(root.nombre) < 0) {
           root.left = eliminar(root.left, n);
       } else if(n.compareTo(root.nombre) > 0) {
           root.right = eliminar(root.right, n);
       } else {
           if((root.left == null)||(root.right == null)) {
                NodoAVL temp = null;
                if(temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                //Ningun hijo
                if(temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                 NodoAVL temp = minNodo(root.right);
                 root.nombre = temp.nombre;
                 root.right = eliminar(root.right, temp.nombre);
            }
        }

        if(root == null) {
            return root;
        }

        root.h = max(getHeight(root.left),getHeight(root.right)) + 1;
        int balance = getBalance(root);

        if(balance > 1 && getBalance(root.right) >= 0) {
            return leftRotate(root);
        }
        if(balance > 1 && getBalance(root.right) < 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        if(balance < -1 && getBalance(root.left) <= 0 ) {
            return rightRotate(root);
        }
        if(balance < -1 && getBalance(root.left) > 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        return root;
   }
   
   public void graficar() {
       PrintWriter escribir;
       try {
            escribir = new PrintWriter(new BufferedWriter(new FileWriter("src/Reportes/avl.dot")));
            escribir.println("digraph avl {\n");
            escribir.println("\tgraph[splines=ortho, nodesep=0.5];\n");
            escribir.println("\tnode[shape=record, style=filled, fillcolor=seashell2];\n");
            
            if(this.root == null) {
                escribir.println("\t\"Arbol Vacio\"\n");
            } else {
                imprimir(this.root,escribir);
                escribir.println("\n\n");
                conectar(this.root,escribir);
            }
            escribir.print("\tlabel = \"AVL de Archivos\"");
            escribir.println("}");
            escribir.close();
            Runtime.getRuntime().exec("dot src/Reportes/avl.dot -o src/Reportes/avl.png -Tpng");
       } catch(Exception e) {
           System.out.println(e.toString());
       }
   }
   
   public void imprimir(NodoAVL r, PrintWriter escribir) {
       if(r != null) {
            escribir.print("\t"+r.hashCode()+"[label=\"<C0>|Nombre: "+r.nombre+"\\nContenido: "+r.contenido+"\\nPropietario: "+r.propietario+"\\nTimeStmp: "+r.timestamp+"\\nAltura: "+r.h+"\\nFE: "+r.fe+"|<C1>\"];\n");
            imprimir(r.left,escribir);
            imprimir(r.right,escribir);
       }
   }
   
   public void conectar(NodoAVL r, PrintWriter escribir) {
       if(r != null) {
           if(r.left != null) {
               escribir.println(r.hashCode()+":C0->"+r.left.hashCode()+";\n");
           }
           if(r.right != null) {
               escribir.println(r.hashCode()+":C1->"+r.right.hashCode()+";\n");
           }
           
           conectar(r.left,escribir);
           conectar(r.right,escribir);
       }
   }
   
   public void PreOrder(NodoAVL n) {
       if(n != null) {
           System.out.print(n.nombre + " ");
           PreOrder(n.left);
           PreOrder(n.right);
       }
   }
   
   public void linealizar() {
       this.lista = new LinkedList<>();
       listInOrder(root);
   }
   
   public void InOrder(NodoAVL n) {
       if(n != null) {
           InOrder(n.left);
           System.out.print(n.nombre + " ");
           InOrder(n.right);
       }
   }
   
   public void listInOrder(NodoAVL n) {
       if(n != null) {
           listInOrder(n.left);
           this.lista.add(n);
           listInOrder(n.right);
       }
   }
}
