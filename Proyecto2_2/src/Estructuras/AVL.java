/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoAVL;

/**
 *
 * @author Douglas
 */
public class AVL {
    public NodoAVL root;
    
   public AVL() {
       this.root = null;
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
   
   public void insertar(String n, String e, String c, String p) {
       this.root = insertar(this.root,n,e,c,p);
   }
   
   private NodoAVL insertar(NodoAVL raiz, String n, String e, String c, String p) {
       if(raiz ==  null) {
           return (new NodoAVL(n,e,c,p));
       }
       
       if(n.compareTo(raiz.nombre) < 0) {
           raiz.left = insertar(raiz.left,n,e,c,p);
       } else if(n.compareTo(raiz.nombre) > 0) {
           raiz.right = insertar(raiz.right,n,e,c,p);
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
           //Uno o ningun hijo
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
   
   public void PreOrder(NodoAVL n) {
       if(n != null) {
           System.out.print(n.nombre + " ");
           PreOrder(n.left);
           PreOrder(n.right);
       }
   }
   
   public void InOrder(NodoAVL n) {
       if(n != null) {
           PreOrder(n.left);
           System.out.print(n.nombre + " ");
           PreOrder(n.right);
       }
   }
}
