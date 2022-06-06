package com.tfg.yourney.services;

import com.tfg.yourney.models.Viaje;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ViajeRepository extends MongoRepository<Viaje, Integer>{
    
    public Viaje findByTitulo(String titulo);
}
