package com.ecomarket.ApiNotificaciones.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



import com.ecomarket.ApiNotificaciones.Model.Notificacion;
import com.ecomarket.ApiNotificaciones.Service.NotificacionService;
@RestController
@RequestMapping("/api/notificaciones")

public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/generar-desde-boletas")
    public ResponseEntity<String> generar() {
        notificacionService.generarDesdeBoletasNuevas();
        return ResponseEntity.ok("Notificaciones generadas con éxito.");
    }

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.listar();
    }

    @GetMapping("/usuario/{id}")
    public List<Notificacion> porUsuario(@PathVariable Integer id) {
        return notificacionService.porUsuario(id);
    }

     //METODOS HATEOAS

    //METODO HATEOAS para buscar por ID
    @GetMapping("/hateoas/{id}")
    public Notificacion obtenerHATEOAS(@PathVariable Integer id) {
        List<Notificacion> notificaciones = notificacionService.porUsuario(id);
            if (notificaciones.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró ninguna notificación para el usuario con ID " + id);
}
            Notificacion not = notificaciones.get(0);
        
        //link HATEOAS para API Gateway "A mano"
        not.add(Link.of("http://localhost:8888/api/proxy/notificaciones" + not.getId()).withSelfRel());
        not.add(Link.of("http://localhost:8888/api/proxy/notificaciones" + not.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        not.add(Link.of("http://localhost:8888/api/proxy/notificaciones" + not.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return not;
    }

    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<Notificacion> obtenerTodosHATEOAS() {
        List<Notificacion> lista = notificacionService.listar();

        for (Notificacion not : lista) {
            //link url de la misma API

            //link HATEOAS para API Gateway "A mano"
            not.add(Link.of("http://localhost:8888/api/proxy/notificaciones").withRel("Get todos HATEOAS"));
            not.add(Link.of("http://localhost:8888/api/proxy/notificaciones" + not.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
}
