package com.tfg.yourney.models;

import java.util.List;

public class Ruta {
    
    //private String origen;
    private List<Localizacion> destinos;

    public Ruta(List<Localizacion> destinos) {
        this.destinos = destinos;
    }

    public Ruta() {
    }

    public List<Localizacion> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<Localizacion> destinos) {
        this.destinos = destinos;
    }
    
    
}
