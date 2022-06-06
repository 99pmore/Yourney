package com.tfg.yourney.models;

import java.util.List;

public class Viajes {

    private List<Viaje> viajes;

    public Viajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public Viajes() {
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    
}
