package com.example.CarritoApi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarritoApi.Model.ItemCarrito;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Integer>{

    //Declaracion de metodos para la clase 
    Optional<ItemCarrito> findByCarritoIdAndProductoId(Integer carritoId, Integer productoId);
    List<ItemCarrito> findByCarritoId(Integer carritoId);
}
