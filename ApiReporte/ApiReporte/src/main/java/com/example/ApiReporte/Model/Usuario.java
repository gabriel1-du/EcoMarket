package com.example.ApiReporte.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Usuario")
@Entity
public class Usuario {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 15)
    private String apellido;

    @Column(name = "email", nullable = false, length = 70, unique = true)
    private String email;


    //Relaciones con otras Entidades/Clases

    // Relación con Reportes
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reporte> reportes;

}
