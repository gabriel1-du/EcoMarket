package com.gestion.models;

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
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id") // <== ¡ESTO ES CLAVE!
    private Integer idUsuario;

    @Column(name = "nombre")
    private String nombreUsuario;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "email")
    private String email;
    
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "rol")
    private String rol;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "estado")
    private String estado;

    @Column(name = "direccion")
    private String direccion;

}
