package com.tfg.yourney.controllers;

import java.util.List;
import java.util.Optional;

import com.tfg.yourney.models.Amigo;
import com.tfg.yourney.models.ElementoLista;
import com.tfg.yourney.models.Gasto;
import com.tfg.yourney.models.Lista;
import com.tfg.yourney.models.Localizacion;
import com.tfg.yourney.models.Notificacion;
import com.tfg.yourney.models.Usuario;
import com.tfg.yourney.models.Usuarios;
import com.tfg.yourney.models.Viaje;
import com.tfg.yourney.models.Viajes;
import com.tfg.yourney.services.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path="/api/yourney")
public class Controlador {
    
    private final Servicio servicio;

    @Autowired
    public Controlador(Servicio servicio) {
        this.servicio = servicio;
    }



    // U S U A R I O S

    // GET - Lista de usuarios de la app
    @GetMapping(path="usuarios")
    public Usuarios getUsuarios() {
        return servicio.getUsuarios();
    }

    // GET - Datos de un usuario
    @GetMapping(path="usuarios/{correo}")
    public Usuario getUsuario(@PathVariable String correo) {
        return servicio.getUsuario(correo);
    }

    // GET - Lista de usuarios de un viaje
    @GetMapping(path="viajes/{id}/usuarios")
    public List<String> getUsuariosViaje(@PathVariable int id) {
        return servicio.getUsuariosViaje(id);
    }

    // POST - Anadir un usuario
    @PostMapping(path="usuarios")
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return servicio.addUsuario(usuario);
    }

    // POST - Anadir un usuario a un viaje
    @PostMapping(path="viajes/{id}/usuarios")
    public void addUsuarioViaje(@PathVariable int id, @RequestBody String correo) {
        servicio.addUsuarioViaje(id, correo);
    }

    // DELETE - Eliminar un viaje de la lista de viajes de un usuario
    @DeleteMapping(path="usuarios/{correo}/viajes/{id}")
    public void deleteViajeUsuario(@PathVariable String correo, @PathVariable int id) {
        servicio.deleteViajeUsuario(correo, id);
    }

    // PATCH - Editar el nombre de un usuario
    @PatchMapping(path="usuarios/{correo}/nombre")
    public void updateUsuarioNombre(@PathVariable String correo, @RequestBody Usuario usuario) {
        servicio.updateUsuarioNombre(correo, usuario);
    }

    // PATCH - Editar los apellidos de un usuario
    @PatchMapping(path="usuarios/{correo}/apellidos")
    public void updateUsuarioApellidos(@PathVariable String correo, @RequestBody Usuario usuario) {
        servicio.updateUsuarioApellidos(correo, usuario);
    }

    // PATCH - Anadir id de un viaje a la lista de viajes de un usuario
    @PatchMapping(path="usuarios/{correo}/viajes")
    public void addIdUsuario(@PathVariable String correo, @RequestBody Usuario usuario) {
        servicio.addIdUsuario(correo, usuario);
    }

    // GET - Lista de viajes de un usuario
    @GetMapping(path="usuarios/{correo}/viajes")
    public Viajes getViajesUsuario(@PathVariable String correo) {
        return servicio.getViajesUsuario(correo);
    }



    // A M I G O S

    // GET - Amigos de un usuario
    @GetMapping(path="usuarios/{correo}/amigos")
    public List<Amigo> getAmigosUsuario(@PathVariable String correo) {
        return servicio.getAmigosUsuario(correo);
    }

    // POST - Anadir un amigo
    @PostMapping(path="usuarios/{correo}/amigos")
    public void addAmigo(@PathVariable String correo, @RequestBody Amigo amigo) {
        servicio.addAmigo(correo, amigo);
    }

    // DELETE - Eliminar un amigo
    @DeleteMapping(path="usuarios/{correo}/amigos/{correoAmigo}")
    public void deleteAmigo(@PathVariable String correo, @PathVariable String correoAmigo) {
        servicio.deleteAmigo(correo, correoAmigo);
    }



    // V I A J E S

    // GET - Datos de un viaje
    @GetMapping(path="viajes/{id}")
    public Optional<Viaje> getViaje(@PathVariable int id) {
        return servicio.getViaje(id);
    }

    // POST - Anadir un viaje
    @PostMapping(path="viajes")
    public Viaje addViaje(@RequestBody Viaje viaje) {
        return servicio.addViaje(viaje);
    }

    // POST - Anadir un viaje con correo
    @PostMapping(path="viajes/{correo}")
    public Viaje addViaje(@PathVariable String correo, @RequestBody Viaje viaje) {
        return servicio.addViajeMail(correo, viaje);
    }

    // PATCH - Editar los datos de un viaje
    @PatchMapping(path="viajes/{titulo}")
    public void updateViaje(@PathVariable String titulo, @RequestBody Viaje viaje) {
        servicio.updateViaje(titulo, viaje);
    }

    // PATCH - Anadir usuario a la lista de usuarios de un viaje
    @PatchMapping(path="viajes/{id}/usuarios")
    public void addUserViaje(@PathVariable int id, @RequestBody Viaje viaje) {
        servicio.addUserViaje(id, viaje);
    }

    

    // G A S T O S

    // GET - Lista de gastos de un viaje
    @GetMapping(path="viajes/{id}/gastos")
    public List<Gasto> getGastosViaje(@PathVariable int id) {
        return servicio.getGastosViaje(id);
    }

    // POST - Anadir un gasto a un viaje
    @PostMapping(path="viajes/{id}/gastos")
    public void addGasto(@PathVariable int id, @RequestBody Gasto gasto) {
        servicio.addGasto(id, gasto);
    }

    // DELETE - Eliminar un gasto
    @DeleteMapping(path="viajes/{id}/gastos/{concepto}")
    public void deleteGasto(@PathVariable int id, @PathVariable String concepto) {
        servicio.deleteGasto(id, concepto);
    }

    // PATCH - Editar un gasto de un viaje
    @PatchMapping(path="viajes/{id}/gastos/{concepto}")
    public void updateGasto(@PathVariable int id, @PathVariable String concepto, @RequestBody Gasto gasto) {
        servicio.updateGasto(id, concepto, gasto);
    }


    // L I S T A S

    // GET - Lista de listas de un viaje
    @GetMapping(path="viajes/{id}/listas")
    public List<Lista> getListasViaje(@PathVariable int id) {
        return servicio.getListasViaje(id);
    }

    // GET - Lista de un viaje
    @GetMapping(path="viajes/{id}/listas/{tituloLista}")
    public Lista getListaViaje(@PathVariable int id, @PathVariable String tituloLista) {
        return servicio.getListaViaje(id, tituloLista);
    }

    // POST - Anadir una lista a un viaje
    @PostMapping(path="viajes/{id}/listas")
    public void addLista(@PathVariable int id, @RequestBody Lista lista) {
        servicio.addLista(id, lista);
    }

    // DELETE - Eliminar una lista
    @DeleteMapping(path="viajes/{id}/listas/{tituloLista}")
    public void deleteLista(@PathVariable int id, @PathVariable String tituloLista) {
        servicio.deleteLista(id, tituloLista);
    }



    // E L E M E N T O S   L I S T A

    // POST - Anadir un elemento a una lista
    @PostMapping(path="viajes/{id}/listas/{tituloLista}/elementos")
    public void addElementoLista(@PathVariable int id, @PathVariable String tituloLista, @RequestBody ElementoLista elementoLista) {
        servicio.addElementoLista(id, tituloLista, elementoLista);
    }

    // DELETE - Eliminar un elemento de una lista
    @DeleteMapping(path="viajes/{id}/listas/{tituloLista}/elementos/{elemento}")
    public void deleteElementoLista(@PathVariable int id, @PathVariable String tituloLista, @PathVariable String elemento) {
        servicio.deleteElementoLista(id, tituloLista, elemento);
    }



    // R U T A S

    // GET - Ruta de un viaje
    @GetMapping(path="viajes/{id}/ruta")
    public List<Localizacion> getRutaViaje(@PathVariable int id) {
        return servicio.getRutaViaje(id);
    }



    // L O C A L I Z A C I O N E S

    // POST - Anadir una localizacion a la ruta
    @PostMapping(path="viajes/{id}/ruta")
    public void addLocalizacion(@PathVariable int id, @RequestBody Localizacion destino) {
        servicio.addLocalizacion(id, destino);
    }

    // DELETE - Eliminar una localizacion de la ruta
    @DeleteMapping(path="viajes/{id}/ruta/{ciudad}")
    public void deleteLocalizacion(@PathVariable int id, @PathVariable String ciudad) {
        servicio.deleteLocalizacion(id, ciudad);
    }



    // N O T I F I C A C I O N E S
    
    // GET - Obtener notificaciones
    @GetMapping(path="usuarios/{correo}/notificaciones")
    public List<Notificacion> getNotificaciones(@PathVariable String correo) {
        return servicio.getNotificaciones(correo);
    }

    // POST - Anadir notificacion
    @PostMapping(path="usuarios/{correo}/notificaciones")
    public void addNotificacion(@PathVariable String correo, @RequestBody Notificacion notificacion) {
        servicio.addNotificacion(correo, notificacion);
    }

    // DELETE - Eliminar una notificacion
    @DeleteMapping(path="usuarios/{correo}/notificaciones/{idViaje}")
    public void deleteNotificacion(@PathVariable String correo, @PathVariable String idViaje) {
        servicio.deleteNotificacion(correo, idViaje);
    }




    /*** AUX PARA MI  ***/

    // @GetMapping(path="add")
    // public void addU(){
    //     servicio.addU();
    // }
}
