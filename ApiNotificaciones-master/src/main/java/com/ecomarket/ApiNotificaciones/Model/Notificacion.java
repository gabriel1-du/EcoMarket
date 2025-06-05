package com.ecomarket.ApiNotificaciones.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacion_id")
    private Integer id;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    private String tipo;
    private String titulo;
    private String mensaje;

    @Column(name = "enviada_en")
    private LocalDateTime enviadaEn;

    @Column(name = "leida_en")
    private LocalDateTime leidaEn;

    private String estado;

    @Column(name = "boleta_id")
    private Integer boletaId;
}