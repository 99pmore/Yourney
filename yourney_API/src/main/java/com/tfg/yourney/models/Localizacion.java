package com.tfg.yourney.models;

import org.springframework.data.annotation.Id;

public class Localizacion {
    
    @Id
    private String ciudad;
    private String pais;

    public Localizacion(String ciudad, String pais) {
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public Localizacion() {
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
}
