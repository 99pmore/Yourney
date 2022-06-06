package com.tfg.yourney.models;

import java.util.List;

public class Usuarios {
    
    private List<Usuario> usuarios;

    public Usuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
}
