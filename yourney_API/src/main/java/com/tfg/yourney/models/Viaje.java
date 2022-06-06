package com.tfg.yourney.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Viaje {
    
    @Id
    private int id;
    private String titulo;
    private String fecha_ini;
    private String fecha_fin;
    private List<String> usuarios;
    private List<Gasto> gastos;
    private List<Lista> listas;
    private List<Localizacion> ruta;
    
    public Viaje(int id, String titulo, String fecha_ini, String fecha_fin, List<String> usuarios, List<Gasto> gastos,
            List<Lista> listas, List<Localizacion> ruta) {
        this.id = id;
        this.titulo = titulo;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.usuarios = usuarios;
        this.gastos = gastos;
        this.listas = listas;
        this.ruta = ruta;
    }

    public Viaje() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public List<String> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<String> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

    public List<Localizacion> getRuta() {
        return ruta;
    }

    public void setRuta(List<Localizacion> ruta) {
        this.ruta = ruta;
    }
    
    
}
