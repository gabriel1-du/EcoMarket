package com.ecomarket.ApiNotificaciones.Service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ecomarket.ApiNotificaciones.Model.Boleta;
import com.ecomarket.ApiNotificaciones.Model.Notificacion;
import com.ecomarket.ApiNotificaciones.Repository.BoletaRepository;
import com.ecomarket.ApiNotificaciones.Repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private  NotificacionRepository notificacionRepository;
    
    @Autowired
    private BoletaRepository boletaRepository;

    public void generarDesdeBoletasNuevas() {
        List<Boleta> boletas = boletaRepository.findAll();

        for (Boleta b : boletas) {
            if (!notificacionRepository.existsByBoletaId(b.getBoletaId())) {
                Notificacion n = new Notificacion();
                n.setUsuarioId(b.getUsuarioId());
                n.setTipo("boleta");
                n.setTitulo("Compra realizada");
                n.setMensaje("Tu compra con total $" + b.getTotal() + " fue procesada exitosamente.");
                n.setEnviadaEn(LocalDateTime.now());
                n.setEstado("no leída");
                n.setBoletaId(b.getBoletaId());
                notificacionRepository.save(n);
            }
        }
    }

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> porUsuario(Integer usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }
}