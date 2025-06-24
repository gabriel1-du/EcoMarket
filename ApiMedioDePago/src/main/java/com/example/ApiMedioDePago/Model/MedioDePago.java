package com.example.ApiMedioDePago.Model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "mediopago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MedioDePago extends RepresentationModel<MedioDePago>{

    //-----
    //atributos
    //------

    @Id
    @Column(name = "metodo_pago_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_medio_de_pago;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "nombre_proveedor", nullable = false)
    private String nombre_proveedor;

    @Column(name = "detalles", nullable = false)
    private String detalles;

    @Column(name = "activo", nullable = false)
    private boolean activo;
}
