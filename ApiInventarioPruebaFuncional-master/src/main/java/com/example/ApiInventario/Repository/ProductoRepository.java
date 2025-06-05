package com.example.ApiInventario.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ApiInventario.Model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    @Query("SELECT p FROM Producto p WHERE p.categoria.categoria_id = :categoriaId")
    List<Producto> findByCategoriaId(@Param("categoriaId") Integer categoriaId);

}
