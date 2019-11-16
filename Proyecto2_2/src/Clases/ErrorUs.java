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
public class ErrorUs {
    private final SimpleStringProperty us;
    private final SimpleStringProperty err;
    
    public ErrorUs() {
        this.us = null;
        this.err = null;
    }
    
    public ErrorUs(String u, String e) {
        this.us = new SimpleStringProperty(u);
        this.err = new SimpleStringProperty(e);
    }

    /**
     * @return the us
     */
    public String getUs() {
        return us.get();
    }

    /**
     * @return the err
     */
    public String getErr() {
        return err.get();
    }
}
