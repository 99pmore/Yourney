package com.tfg.yourney.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {
    
	@Id
    private String correo;
    private String nombre;
    private String apellidos;
	private List<Amigo> amigos;
	private List<Integer> viajes;
	private List<Notificacion> notificaciones;

	public Usuario(String correo, String nombre, String apellidos, List<Amigo> amigos, List<Integer> viajes,
			List<Notificacion> notificaciones) {
		this.correo = correo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.amigos = amigos;
		this.viajes = viajes;
		this.notificaciones = notificaciones;
	}

	public Usuario() {
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<Amigo> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Amigo> amigos) {
		this.amigos = amigos;
	}

	public List<Integer> getViajes() {
		return viajes;
	}

	public void setViajes(List<Integer> viajes) {
		this.viajes = viajes;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}
	
	

}
