package com.tfg.yourney.services;

import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;



    // U S U A R I O S

    // GET - Lista de usuarios de la app
    public Usuarios getUsuarios() {
        Usuarios usuarios = new Usuarios(usuarioRepository.findAll());
        return usuarios;
    }

    // GET - Datos de un usuario
    public Usuario getUsuario(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    // GET - Lista de usuarios de un viaje
    public List<String> getUsuariosViaje(int id) {
        return viajeRepository.findById(id).get().getUsuarios();
    }

    // POST - Anadir un usuario a la app
    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // POST - Anadir un usuario a un viaje
    public void addUsuarioViaje(int id, String correo) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<String> users = viaje.getUsuarios(); // Lista de usuarios del viaje "titulo"
        users.add(correo);
        viaje.setUsuarios(users);
        viajeRepository.save(viaje);
    }

    // DELETE - Eliminar un viaje de la lista de viajes de un usuario
    public void deleteViajeUsuario(String correo, int id) {
        Usuario user = usuarioRepository.findById(correo).get();
        List<Integer> travels = user.getViajes();

        for (int i = 0; i < travels.size(); i++) {
            if (travels.get(i) == id) {
                travels.remove(i);
            }
        }

        deleteUsuarioViaje(id, correo);
        user.setViajes(travels);
        usuarioRepository.save(user);
    }

    // PATCH - Editar nombre de un usuario
    public void updateUsuarioNombre(String correo, Usuario usuario) {
        Usuario user = usuarioRepository.findById(usuario.getCorreo()).get();

        if (user.getNombre() != null) {
            user.setNombre(usuario.getNombre());
            user.setApellidos(user.getApellidos());

        } else {

        }

        usuarioRepository.save(user);
    }

    // PATCH - Editar los datos de un usuario
    public void updateUsuarioApellidos(String correo, Usuario usuario) {
        Usuario user = usuarioRepository.findById(usuario.getCorreo()).get();

        if (user.getApellidos() != null) {
            user.setNombre(user.getNombre());
            user.setApellidos(usuario.getApellidos());

        } else {

        }

        usuarioRepository.save(user);
    }

    // PATCH - Anadir id de un viaje a la lista de viajes de un usuario
    public void addIdUsuario(String correo, Usuario usuario) {
        Usuario user = usuarioRepository.findById(correo).get();
        List<Integer> viajes = user.getViajes();
        List<Integer> newViajes = new ArrayList<Integer>();

        for (int i = 0; i < viajes.size(); i++) {
            newViajes.add(viajes.get(i));
        }
        
        List<Integer> usuarioViajes = usuario.getViajes();
        for (int i = 0; i < usuarioViajes.size(); i++) {
            if (!(newViajes.contains(usuarioViajes.get(i)))) {
                newViajes.add(usuarioViajes.get(i));
            }
        }

        user.setViajes(newViajes);
        usuarioRepository.save(user);
    }

    // GET - Lista de viajes de un usuario
    public Viajes getViajesUsuario(String correo) {
        List<Integer> viajesUser = usuarioRepository.findById(correo).get().getViajes();

        List<Viaje> viajes = new ArrayList<Viaje>();
        Viajes travels = new Viajes(viajes);

        for (int i = 0; i < viajesUser.size(); i++) {

            Viaje travel = getViaje(viajesUser.get(i)).get();
            viajes.add(travel);
            travels.setViajes(viajes);
        }

        return travels;
    }



    // A M I G O S

    // GET - Amigos de un usuario
    public List<Amigo> getAmigosUsuario(String correo) {
        return usuarioRepository.findById(correo).get().getAmigos();
    }

    // POST - Anadir un amigo
    public void addAmigo(String correo, Amigo amigo) {
        Usuario user = usuarioRepository.findById(correo).get();
        List<Amigo> friends = user.getAmigos();
        List<String> correos = new ArrayList<String>();

        for (int i = 0; i < friends.size(); i++) {
            correos.add(friends.get(i).getCorreoAmigo());
        }

        if (!(correos.contains(amigo.getCorreoAmigo())) && !(amigo.getCorreoAmigo().equals(user.getCorreo()))) {
            friends.add(amigo);
        }

        user.setAmigos(friends);
        usuarioRepository.save(user);
    }

    // DELETE - Eliminar un amigo
    public void deleteAmigo(String correo, String correoAmigo) {
        Usuario user = usuarioRepository.findById(correo).get();
        List<Amigo> friends = user.getAmigos();

        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).getCorreoAmigo().equals(correoAmigo)) {
                friends.remove(i);
            }
        }

        user.setAmigos(friends);
        usuarioRepository.save(user);
    }

    // GET - Datos de un viaje
    public Optional<Viaje> getViaje(int id) {
        return viajeRepository.findById(id);
    }

    // POST - Anadir un viaje
    public Viaje addViaje(Viaje viaje) {
        int id = (int)(Math.random() * 999999);
        if (viajeRepository.findById(id) != null) { // Si existe en la base
            id = (int)(Math.random() * 999999);
        }

        viaje.setId(id);
        return viajeRepository.save(viaje);
    }

    // POST - Anadir un viaje con correo
    public Viaje addViajeMail(String correo, Viaje viaje) {
        int id = (int)(Math.random() * 999999);
        if (viajeRepository.findById(id) != null) { // Si existe en la base
            id = (int)(Math.random() * 999999);
        }

        viaje.setId(id);
        addId(correo, id);
        return viajeRepository.save(viaje);
    }

    // AUX POST - Id de un viaje en lista de viajes
    public void addId(String correo, int id) {
        Usuario user = usuarioRepository.findById(correo).get();
        List<Integer> travels = user.getViajes();
        travels.add(id);
        user.setViajes(travels);
        usuarioRepository.save(user);
    }

    // AUX DELETE - Eliminar un usuario de un viaje
    public void deleteUsuarioViaje(int id, String correo) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<String> users = viaje.getUsuarios();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(correo)) {
                users.remove(users.get(i));
            }
        }

        viaje.setUsuarios(users);
        viajeRepository.save(viaje);
    }

    // PATCH - Editar los datos de un viaje
    public void updateViaje(String titulo, Viaje viaje) {
        Viaje travel = viajeRepository.findByTitulo(titulo);

        if (viaje.getFecha_ini() != null && viaje.getFecha_fin() != null) {
            travel.setFecha_ini(viaje.getFecha_ini());
            travel.setFecha_fin(viaje.getFecha_fin());

        } else if (viaje.getFecha_fin() == null) {
            travel.setFecha_ini(viaje.getFecha_ini());
            travel.setFecha_fin(travel.getFecha_fin());

        } else if (viaje.getFecha_ini() == null) {
            travel.setFecha_ini(travel.getFecha_ini());
            travel.setFecha_fin(viaje.getFecha_fin());

        } else {

        }

        viajeRepository.save(travel);
    }

    // PATCH - Anadir usuario a la lista de usuarios de un viaje
    public void addUserViaje(int id, Viaje viaje) {
        Viaje travel = viajeRepository.findById(id).get();
        List<String> usuarios = travel.getUsuarios();
        List<String> newUsuarios = new ArrayList<String>();

        for (int i = 0; i < usuarios.size(); i++) {
            newUsuarios.add(usuarios.get(i));
        }
        
        List<String> viajeUsuarios = viaje.getUsuarios();
        for (int i = 0; i < viajeUsuarios.size(); i++) {
            if (!(newUsuarios.contains(viajeUsuarios.get(i)))) {
                newUsuarios.add(viajeUsuarios.get(i));
            }
        }

        travel.setUsuarios(newUsuarios);
        viajeRepository.save(travel);
    }



    // G A S T O S

    // GET - Lista de gastos de un viaje
    public List<Gasto> getGastosViaje(int id) {
        return viajeRepository.findById(id).get().getGastos();
    }

    // POST - Anadir un gasto a un viaje
    public void addGasto(int id, Gasto gasto) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Gasto> expenses = viaje.getGastos();
        expenses.add(gasto);
        viaje.setGastos(expenses);
        viajeRepository.save(viaje);
    }

    // DELETE - Eliminar un gasto
    public void deleteGasto(int id, String concepto) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Gasto> expenses = viaje.getGastos();
        
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getConcepto().equals(concepto)) {
                expenses.remove(i);
            }
        }

        viaje.setGastos(expenses);
        viajeRepository.save(viaje);
    }

    // PATCH - Editar un gasto de un viaje
    public void updateGasto(int id, String concepto, Gasto gasto) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Gasto> expenses = viaje.getGastos();

        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getConcepto().equals(concepto)) {
                
                if (expenses.get(i) != null) {
                    expenses.get(i).setImporte(gasto.getImporte());

                } else {
    
                }
            }
        }

        viaje.setGastos(expenses);
        viajeRepository.save(viaje);
    }



    // L I S T A S

    // GET - Lista de listas de un viaje
    public List<Lista> getListasViaje(int id) {
        return viajeRepository.findById(id).get().getListas();
    }

    // GET - Lista de un viaje
    public Lista getListaViaje(int id, String tituloLista) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Lista> lists = viaje.getListas();
        Lista list = null;

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getTituloLista().equals(tituloLista)) {
                list = lists.get(i);
            }
        }

        return list;
    }

    // POST - Anadir una lista a un viaje
    public void addLista(int id, Lista lista) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Lista> lists = viaje.getListas();
        lists.add(lista);
        viaje.setListas(lists);
        viajeRepository.save(viaje);
    }

    // DELETE - Eliminar una lista
    public void deleteLista(int id, String tituloLista) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Lista> lists = viaje.getListas();

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getTituloLista().equals(tituloLista)) {
                lists.remove(i);
                deleteElementosLista(id, tituloLista);
            }
        }

        viaje.setListas(lists);
        viajeRepository.save(viaje);
    }

    

    // E L E M E N T O S   L I S T A

    // POST - Anadir un elemento a una lista
    public void addElementoLista(int id, String tituloLista, ElementoLista elementoLista) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Lista> lists = viaje.getListas();
        Lista list = null;

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getTituloLista().equals(tituloLista)) {
                list = lists.get(i);
            }
        }

        List<ElementoLista> elements = list.getElementos();
        elements.add(elementoLista);
        list.setElementos(elements);
        viajeRepository.save(viaje);     
    }

    // DELETE - Eliminar un elemento de una lista
    public void deleteElementoLista(int id, String tituloLista, String elemento) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Lista> lists = viaje.getListas();
        Lista list = null;

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getTituloLista().equals(tituloLista)) {
                list = lists.get(i);
            }
        }

        List<ElementoLista> elements = list.getElementos();
        
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getElemento().equals(elemento)) {
                elements.remove(i);
            }
        }

        list.setElementos(elements);
        viajeRepository.save(viaje);
    }

    // AUX DELETE - Eliminar todos los elementos de una lista
    public void deleteElementosLista(int id, String tituloLista) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Lista> lists = viaje.getListas();
        Lista list = null;

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getTituloLista().equals(tituloLista)) {
                list = lists.get(i);
            }
        }

        List<ElementoLista> elements = list.getElementos();
        
        for (int i = 0; i < elements.size(); i++) {
            elements.remove(i);
        }

        list.setElementos(elements);
        viajeRepository.save(viaje);
    }



    // R U T A S

    // GET - Ruta de un viaje
    public List<Localizacion> getRutaViaje(int id) {
        return viajeRepository.findById(id).get().getRuta();
    }



    // L O C A L I Z A C I O N E S 

    // POST - Anadir una localizacion a la ruta
    public void addLocalizacion(int id, Localizacion destino) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Localizacion> locations = viaje.getRuta();
        locations.add(destino);
        viaje.setRuta(locations);
        viajeRepository.save(viaje);
    }



    // DELETE - Eliminar una localizacion de la ruta
    public void deleteLocalizacion(int id, String ciudad) {
        Viaje viaje = viajeRepository.findById(id).get();
        List<Localizacion> locations = viaje.getRuta();
        
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getCiudad().equals(ciudad)) {
                locations.remove(i);
            }
        }

        viaje.setRuta(locations);
        viajeRepository.save(viaje);
    }



    // N O T I F I C A C I O N E S
    
    // GET - Obtener notificaciones
    public List<Notificacion> getNotificaciones(String correo) {
        return usuarioRepository.findById(correo).get().getNotificaciones();
    }

    // POST - Anadir notificacion
    public void addNotificacion(String correo, Notificacion notificacion) {
        Usuario user = usuarioRepository.findById(correo).get();
        List<Notificacion> notifications = user.getNotificaciones();
        notifications.add(notificacion);
        user.setNotificaciones(notifications);
        usuarioRepository.save(user);
    }

    // DELETE - Eliminar una notificacion
    public void deleteNotificacion(String correo, String idViaje) {
        Usuario user = usuarioRepository.findById(correo).get();
        List<Notificacion> notifications = user.getNotificaciones();

        for (int i = 0; i < notifications.size(); i++) {
            if (notifications.get(i).getIdViaje().equals(idViaje)) {
                notifications.remove(i);
            }
        }

        user.setNotificaciones(notifications);
        usuarioRepository.save(user);
    }


 

    /*** AUX PARA MI  ***/

    // public void addU() {

    //     List<String> users = new ArrayList<String>();

    //     int id = (int)(Math.random() * 999999);

    //     List<Integer> travels1 = new ArrayList<Integer>();
    //     List<Integer> travels2 = new ArrayList<Integer>();

    //     List<Amigo> friends1 = new ArrayList<Amigo>();
    //     List<Amigo> friends2 = new ArrayList<Amigo>();

    //     List<Notificacion> notis1 = new ArrayList<Notificacion>();
    //     List<Notificacion> notis2 = new ArrayList<Notificacion>();

    //     // String idString = String.valueOf(id);
    //     // Notificacion noti1 = new Notificacion(idString, "Vuelta al mundo", "pablom@gmail.com");
    //     // notis1.add(noti1);

    //     List<Gasto> expenses = new ArrayList<Gasto>();

    //     List<Lista> lists = new ArrayList<Lista>();
    //     List<ElementoLista> elements = new ArrayList<ElementoLista>();

    //     List<Localizacion> destinations = new ArrayList<Localizacion>();

    //     Usuario user1 = new Usuario("pablom@gmail.com", "Pablo", "Moreno Martin", friends1, travels1, notis1);
    //     Usuario user2 = new Usuario("martarp@gmail.com", "Marta", "Ruiz Pérez", friends2, travels2, notis2);

    //     Gasto expense1 = new Gasto("Vuelo", 113.13, "pablom@gmail.com");
    //     Gasto expense2 = new Gasto("Cena", 19.60, "martarp@gmail.com");

    //     ElementoLista element1 = new ElementoLista("Arroz", true);
    //     ElementoLista element2 = new ElementoLista("Coca-Cola", false);
    //     ElementoLista element3 = new ElementoLista("Pan", false);
    //     ElementoLista element4 = new ElementoLista("Pollo", false);

    //     elements.add(element1);
    //     elements.add(element2);
    //     elements.add(element3);
    //     elements.add(element4);

    //     Lista list1 = new Lista("Compra", elements);

    //     Localizacion origin = new Localizacion("Madrid", "España");
    //     Localizacion destination1 = new Localizacion("El Cairo", "Egipto");
    //     Localizacion destination2 = new Localizacion("Moscu", "Rusia");
    //     Localizacion destination3 = new Localizacion("Sidney", "Australia");
    //     Localizacion destination4 = new Localizacion("Buenos Aires", "Argentina");

    //     users.add(user1.getCorreo());
    //     users.add(user2.getCorreo());

    //     Amigo friend1 = new Amigo("martarp@gmail.com", "Marta");
    //     friends1 = user1.getAmigos();
    //     friends1.add(friend1);
    //     user1.setAmigos(friends1);
        
    //     Amigo friend2 = new Amigo("pablom@gmail.com", "Pablo");
    //     friends2 = user2.getAmigos();
    //     friends2.add(friend2);
    //     user2.setAmigos(friends2);
        
    //     expenses.add(expense1);
    //     expenses.add(expense2);

    //     lists.add(list1);

    //     destinations.add(origin);
    //     destinations.add(destination1);
    //     destinations.add(destination2);
    //     destinations.add(destination3);
    //     destinations.add(destination4);

    //     Viaje viaje = new Viaje(id, "Vuelta al mundo", "01/11/2021", "01/02/2022", users, expenses, lists, destinations);

    //     travels1 = user1.getViajes();
    //     travels1.add(viaje.getId());
    //     user1.setViajes(travels1);

    //     travels2 = user2.getViajes();
    //     travels2.add(viaje.getId());
    //     user2.setViajes(travels2);

    //     List<Usuario> usuarios = new ArrayList<Usuario>();
    //     usuarios.add(user1);
    //     usuarios.add(user2);

    //     usuarioRepository.save(user1);
    //     usuarioRepository.save(user2);

    //     viajeRepository.save(viaje);

    // }


}
