package com.ecomarket.ApiNotificaciones.Model;

import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boleta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boleta {

    @Id
    @Column(name = "boleta_id")
    private Integer boletaId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "total")
    private Integer total;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;
}