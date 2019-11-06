/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class NodoPila {
    private String fecha;
    private String hora;
    private String operacion;
    private String usuario;
    private NodoPila sig;
    
    public NodoPila() {
        this.fecha = "";
        this.hora = "";
        this.operacion = "";
        this.usuario = "";
        this.sig = null;
    }
    
    public NodoPila(String op, String u) {
        this.fecha = Fecha();
        this.hora = Hora();
        this.operacion = op;
        this.usuario = u;
        this.sig = null;
    }
    
    private String Fecha() {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        
        String fecha = form.format(date);
        return fecha;
    }
    
    private String Hora() {
        SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        
        String hora = form.format(date);
        return hora;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the sig
     */
    public NodoPila getSig() {
        return sig;
    }

    /**
     * @param sig the sig to set
     */
    public void setSig(NodoPila sig) {
        this.sig = sig;
    }
}
