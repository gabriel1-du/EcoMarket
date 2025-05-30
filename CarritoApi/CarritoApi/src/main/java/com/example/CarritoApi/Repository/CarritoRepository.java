package com.example.CarritoApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarritoApi.Model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer>{

    Carrito findByUsuarioIdAndActivoTrue(Integer usuarioId);

}
