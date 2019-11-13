/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Estructuras.MatrizDispersa;


/**
 *
 * @author Douglas
 */
public class Usuario {
    public String usuario;
    public String password;
    public String hexdigest;
    public int indice;
    public String timestamp;
    public MatrizDispersa matrix;
    
    public Usuario() {
        this.usuario = "";
        this.password = "";
        this.indice = -1;
        this.timestamp = "";
        this.matrix = new MatrizDispersa("Default");
        this.matrix.insertar("/", "/");
    }
    
    public Usuario(String n, String p, int i) {
        this.usuario = n;
        this.password = p;
        this.indice = i;
        try {
            this.hexdigest = hexdigest(sha256(p));
        } catch(Exception e) {
            this.hexdigest = "001122334455667788991234567890";
            System.err.println("Exception foe incorrect algorithm : "+ e);
        }
        this.timestamp = timeStamp();
        this.matrix = new MatrizDispersa(n);
    }
    
    private byte[] sha256(String p) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(p.getBytes(StandardCharsets.UTF_8));
    }
    
    private String hexdigest(byte[] hash) {
        BigInteger num = new BigInteger(1, hash);
        StringBuilder hex = new StringBuilder(num.toString(16));
        while(hex.length() < 32) {
            hex.insert(0, '0');
        }
        
        return hex.toString();
    }
    
    private String timeStamp() {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        
        String ts = form.format(date);
        return ts;
    }
}
