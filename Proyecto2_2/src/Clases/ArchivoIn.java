/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Douglas
 */
public class ArchivoIn {
    private final SimpleStringProperty nom;
    private final SimpleStringProperty cont;
    
    public ArchivoIn() {
        this.nom = null;
        this.cont = null;
    }
    
    public ArchivoIn(String n, String c) {
        this.nom = new SimpleStringProperty(n);
        this.cont = new SimpleStringProperty(c);
    }
    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom.get();
    }

    /**
     * @return the cont
     */
    public String getCont() {
        return cont.get();
    }
}
