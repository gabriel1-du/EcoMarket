package com.ecomarket.ApiInventarioV2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket.ApiInventarioV2.Model.Inventario;
import com.ecomarket.ApiInventarioV2.Repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    public Inventario obtenerPorId(Integer id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    public List<Inventario> obtenerPorProductoId(Integer productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

     public Inventario crearInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario actualizarInventario(Integer id, Inventario nuevo) {
        Inventario actual = inventarioRepository.findById(id).orElse(null);
        if (actual == null) return null;

        actual.setProducto(nuevo.getProducto());
        actual.setStockDisponible(nuevo.getStockDisponible());
        actual.setUbicacionBodega(nuevo.getUbicacionBodega());

        return inventarioRepository.save(actual);

        
    }

    public void eliminarInventario(Integer id) {
        inventarioRepository.deleteById(id);
    }

    
        

    
        

}
