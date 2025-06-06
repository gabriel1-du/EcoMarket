package com.gestion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {
    
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administradorr")
    private Integer idAdministrador;
    
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    //Atributos
    private String nombreCompleto; 
}