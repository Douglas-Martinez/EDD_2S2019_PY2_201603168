/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import Estructuras.TablaHash;
import Clases.Usuario;
import Estructuras.Pila;
import Interfaz.Ingreso;
import Interfaz.Administrador;
import Interfaz.Home;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Douglas
 */
public class Proyecto2 {
    
    /**
     * @param args the command line arguments
     */
    
    public static TablaHash usuarios = new TablaHash();
    public static Pila log = new Pila();
    public static Usuario actual;
    
    public static void main(String[] args) {
        // TODO code application logic here
        //Ingreso p1 = new Ingreso();
        //p1.setVisible(true);
        //Administrador a = new Administrador();
        //a.setVisible(true);
        Home h = new Home();
        h.setVisible(true);
        
        
    }
}