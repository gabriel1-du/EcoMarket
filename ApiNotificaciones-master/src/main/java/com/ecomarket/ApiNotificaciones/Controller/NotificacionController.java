package com.ecomarket.ApiNotificaciones.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
