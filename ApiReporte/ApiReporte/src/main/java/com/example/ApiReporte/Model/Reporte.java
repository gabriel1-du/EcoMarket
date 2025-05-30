package com.example.ApiReporte.Model;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
//Ahora esta clase esta basada en la reciente entidad llamada "reporte"
public class Reporte {

    @Id //Cambio en el nombre da la columna
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reporte_id")
    private Integer reporteId;

    //Cambio de nombre a nombre_reporte para coincidir con la bda
    @Column(name = "nombre_reporte",nullable = false, length = 150)
    private String nombre_reporte;

    @Column(name = "descripcion",columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "query_base", columnDefinition = "TEXT")
    private String query_base;

    @Column(name = "fecha_creacion_repo", columnDefinition = "timestamp")
    private Timestamp fecha_creacion_repo; //Nuevo atributo creado

    //Relacion con otra tabla

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_reporte_id", nullable = false)
    private TipoReporte tipoReporte;


}
