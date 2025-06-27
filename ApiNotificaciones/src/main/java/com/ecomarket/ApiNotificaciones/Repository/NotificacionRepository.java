package com.ecomarket.ApiNotificaciones.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomarket.ApiNotificaciones.Model.Notificacion;
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    boolean existsByBoletaId(Integer boletaId);
    List<Notificacion> findByUsuarioId(Integer usuarioId);
}
