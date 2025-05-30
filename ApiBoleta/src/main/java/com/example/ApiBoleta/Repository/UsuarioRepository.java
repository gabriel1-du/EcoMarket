package com.example.ApiBoleta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiBoleta.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
