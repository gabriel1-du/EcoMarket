package com.example.ApiBoleta.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boleta")
public class Boleta {


    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boleta_id")
    private Integer boletaId;

    @Column(name = "subtotal", nullable = false)
    private Integer subtotal;

    @Column(name = "impuesto", nullable = false)
    private Integer impuesto;

    @Column(name = "total", nullable = false)
    private Integer total;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;



    //Relacion con otras clases/entidades
    //Relacion con la clase pedido
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    //Relacion con la clase usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore //Para evitar el bucle
    private Usuario usuario;

 
    


}
