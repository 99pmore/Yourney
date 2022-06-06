package com.tfg.yourney.services;

import com.tfg.yourney.models.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    public Usuario findByCorreo(String correo);
}
