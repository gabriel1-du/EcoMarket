package com.example.CarritoApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarritoApi.Model.Usuario;

@Repository
public interface usuarioRepository extends JpaRepository <Usuario, Integer>{

}
