package com.tfg.yourney.models;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Lista {

    @Id
    private String tituloLista;
    private List<ElementoLista> elementos;
    
    public Lista(String tituloLista, List<ElementoLista> elementos) {
        this.tituloLista = tituloLista;
        this.elementos = elementos;
    }

    public Lista() {
    }

    public String getTituloLista() {
        return tituloLista;
    }

    public void setTituloLista(String tituloLista) {
        this.tituloLista = tituloLista;
    }

    public List<ElementoLista> getElementos() {
        return elementos;
    }

    public void setElementos(List<ElementoLista> elementos) {
        this.elementos = elementos;
    }
    

    
}
