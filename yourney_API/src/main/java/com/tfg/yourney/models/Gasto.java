package com.tfg.yourney.models;

import org.springframework.data.annotation.Id;

public class Gasto {

    @Id
    private String concepto;
    private double importe;
    private String username;

    public Gasto(String concepto, double importe, String username) {
        this.concepto = concepto;
        this.importe = importe;
        this.username = username;
    }

    public Gasto() {
    }

    public String getConcepto() {
        return concepto;
    }
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    

}
