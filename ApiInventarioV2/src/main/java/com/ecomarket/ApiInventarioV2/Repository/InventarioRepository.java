package com.ecomarket.ApiInventarioV2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarket.ApiInventarioV2.Model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    List<Inventario> findByProductoId(Integer productoId);
}
