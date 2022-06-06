package com.tfg.yourney.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Amigo {

    @Id
    private String correoAmigo;
    private String nombreAmigo;
    
    public Amigo(String correoAmigo, String nombreAmigo) {
        this.correoAmigo = correoAmigo;
        this.nombreAmigo = nombreAmigo;
    }

    public Amigo() {
    }

    public String getCorreoAmigo() {
        return correoAmigo;
    }

    public void setCorreoAmigo(String correoAmigo) {
        this.correoAmigo = correoAmigo;
    }

    public String getNombreAmigo() {
        return nombreAmigo;
    }

    public void setNombreAmigo(String nombreAmigo) {
        this.nombreAmigo = nombreAmigo;
    }

    
    
}
