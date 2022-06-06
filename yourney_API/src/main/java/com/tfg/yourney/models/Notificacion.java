package com.tfg.yourney.models;

public class Notificacion {
    
    private String idViaje;
    private String tituloViaje;
    private String correoEmisor;
    
    public Notificacion(String idViaje, String tituloViaje, String correoEmisor) {
        this.idViaje = idViaje;
        this.tituloViaje = tituloViaje;
        this.correoEmisor = correoEmisor;
    }

    public Notificacion() {
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getTituloViaje() {
        return tituloViaje;
    }

    public void setTituloViaje(String tituloViaje) {
        this.tituloViaje = tituloViaje;
    }

    public String getCorreoEmisor() {
        return correoEmisor;
    }

    public void setCorreoEmisor(String correoEmisor) {
        this.correoEmisor = correoEmisor;
    }
    
}
