package com.tfg.yourney.models;

import org.springframework.data.annotation.Id;

public class ElementoLista {
    
    @Id
    private String elemento;

    public ElementoLista(String elemento, boolean checked) {
        this.elemento = elemento;
    }

    public ElementoLista() {
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

}
