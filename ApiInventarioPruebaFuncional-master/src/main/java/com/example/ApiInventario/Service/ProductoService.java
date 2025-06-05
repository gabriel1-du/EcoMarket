package com.example.ApiInventario.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiInventario.Model.Producto;
import com.example.ApiInventario.Repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> getAll() {
        return productoRepository.findAll();

    }

    public Producto getById(Integer id){
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    public Producto add(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto update(Integer id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id); // Aseguramos que se use el mismo ID
            return productoRepository.save(producto); // Guarda los cambios
        }
        return null; // No se encontro el producto
    }
    // Eliminar una persona por ID
    public Producto delete(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            productoRepository.deleteById(id); // Elimina el producto
            return producto.get(); // Retorna el producto eliminado
        }
        return null; // No existe el producto
    }
}
